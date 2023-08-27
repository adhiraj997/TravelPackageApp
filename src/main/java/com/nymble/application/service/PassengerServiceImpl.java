package com.nymble.application.service;

import com.nymble.application.beans.Activity;
import com.nymble.application.beans.passenger.GoldPassenger;
import com.nymble.application.beans.passenger.Passenger;
import com.nymble.application.beans.passenger.PremiumPassenger;
import com.nymble.application.beans.passenger.StandardPassenger;
import com.nymble.application.dto.ActivitySignUpInfo;
import com.nymble.application.dto.PassengerDetailsResponse;
import com.nymble.application.exceptions.PassengerNotFoundException;
import com.nymble.application.repository.ActivityRepository;
import com.nymble.application.repository.GoldPassengerRepository;
import com.nymble.application.repository.PremiumPassengerRepository;
import com.nymble.application.repository.StandardPassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class PassengerServiceImpl implements PassengerService  {

    @Autowired
    private StandardPassengerRepository standardPassengerRepository;

    @Autowired
    private PremiumPassengerRepository premiumPassengerRepository;

    @Autowired
    private GoldPassengerRepository goldPassengerRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityService activityService;

    @Override
    public Passenger signUpForActivities(int passengerNumber, List<String> activityIds) {
        if (nonNull(activityIds)) {
            Passenger passenger = findPassengerByNumber(passengerNumber);
            List<Activity> activitiesToSignUp = new ArrayList<>();
            activityRepository.findAllById(activityIds).forEach(activitiesToSignUp::add);
            for (Activity activity : activitiesToSignUp) {
                if (nonNull(activity) && activity.hasSpace()) {
                    passenger.signUpForActivity(activity);
                    activity.addPassenger(passenger);
                    Activity savedActivity = activityService.saveActivity(activity);
                    passenger.getSignedUpActivities().add(activity);
                }
            }
            savePassenger(passenger);
            return passenger;
        }
        return null;
    }

    @Override
    public Passenger signUpForActivity(int passengerNumber, String activityId) {
        if (nonNull(activityId)) {
            // Find passenger by number
            Passenger passenger = findPassengerByNumber(passengerNumber);

            // Find activity by ID
            Activity activity = activityService.findActivityById(activityId);

            // Sign up the passenger for the activity
            passenger.signUpForActivity(activity);

            // Save passenger and activity updates
            savePassenger(passenger);
            activityService.saveActivity(activity);

            return passenger;
        }
        return null;
    }

    @Override
    public List<Activity> getSignedUpActivities(int passengerNumber) {
        // Find passenger by number
        Passenger passenger = findPassengerByNumber(passengerNumber);

        return passenger.getSignedUpActivities();
    }

    @Override
    public PassengerDetailsResponse getPassengerDetails(int passengerNumber) {
        Passenger passenger = findPassengerByNumber(passengerNumber);
        return mapToPassengerDetailsResponse(passenger);
    }

    private PassengerDetailsResponse mapToPassengerDetailsResponse(Passenger passenger) {
        if (nonNull(passenger)) {
            PassengerDetailsResponse response = new PassengerDetailsResponse();
            response.setPassengerName(passenger.getName());
            response.setPassengerNumber(passenger.getNumber());

            if (passenger instanceof StandardPassenger) {
                response.setBalance(((StandardPassenger) passenger).getBalance());
            }

            response.setSignedUpActivities(mapToActivitySignUpInfoList(passenger.getSignedUpActivities()));
            return response;
        }
        return null;
    }

    private List<ActivitySignUpInfo> mapToActivitySignUpInfoList(List<Activity> activities) {
        if (nonNull(activities)) {
            List<ActivitySignUpInfo> activitySignUpInfoList = new ArrayList<>();
            for (Activity activity : activities) {
                if (nonNull(activity)) {
                    ActivitySignUpInfo activitySignUpInfo = new ActivitySignUpInfo();
                    activitySignUpInfo.setActivityName(activity.getName());
                    activitySignUpInfo.setDestinationName(activity.getDestination().getName());
                    activitySignUpInfo.setActivityPrice(activity.getCost());
                    activitySignUpInfoList.add(activitySignUpInfo);
                }
            }
            return activitySignUpInfoList;
        }
        return null;
    }

    private Passenger findPassengerByNumber(int passengerNumber) {
        Optional<StandardPassenger> standardPassenger = standardPassengerRepository.findByNumber(passengerNumber);
        if (standardPassenger.isPresent()) {
            return standardPassenger.get();
        }

        Optional<GoldPassenger> goldPassenger = goldPassengerRepository.findByNumber(passengerNumber);
        if (goldPassenger.isPresent()) {
            return goldPassenger.get();
        }

        Optional<PremiumPassenger> premiumPassenger = premiumPassengerRepository.findByNumber(passengerNumber);
        if (premiumPassenger.isPresent()) {
            return premiumPassenger.get();
        }
        throw new PassengerNotFoundException("Passenger with number " + passengerNumber + " not found.");
    }

    private void savePassenger(Passenger passenger) {
        if (nonNull(passenger)) {
            if (passenger instanceof StandardPassenger) {
                standardPassengerRepository.save((StandardPassenger) passenger);
            } else if (passenger instanceof GoldPassenger) {
                goldPassengerRepository.save((GoldPassenger) passenger);
            } else if (passenger instanceof PremiumPassenger) {
                premiumPassengerRepository.save((PremiumPassenger) passenger);
            }
        }
    }
}
