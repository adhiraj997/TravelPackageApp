package com.nymble.application.service;

import com.nymble.application.beans.Activity;
import com.nymble.application.beans.Destination;
import com.nymble.application.beans.TravelPackage;
import com.nymble.application.beans.passenger.Passenger;
import com.nymble.application.dto.*;
import com.nymble.application.exceptions.TravelPackageNotFoundException;
import com.nymble.application.repository.TravelPackageRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
@Service
@NoArgsConstructor
public class TravelPackageServiceImpl implements TravelPackageService {

    @Autowired
    private TravelPackageRepository travelPackageRepository;
    @Override
    public TravelPackageItinerary getTravelPackageItinerary(String packageId) {
        if (nonNull(packageId)) {
            TravelPackage travelPackage = findTravelPackageById(packageId);
            return mapToTravelPackageItinerary(travelPackage);
        }
        return null;
    }

    @Override
    public PassengerListResponse getPassengerList(String packageId) {
        if (nonNull(packageId)) {
            TravelPackage travelPackage = findTravelPackageById(packageId);
            return mapToPassengerListResponse(travelPackage);
        }
        return null;
    }
    private PassengerListResponse mapToPassengerListResponse(TravelPackage travelPackage) {
        if (nonNull(travelPackage)) {
            PassengerListResponse response = new PassengerListResponse();
            response.setPackageName(travelPackage.getName());
            response.setPassengerCapacity(travelPackage.getPassengerCapacity());
            if (nonNull(travelPackage.getPassengers())) {
                response.setEnrolledPassengerCount(travelPackage.getPassengers().size());
            }
            response.setPassengers(mapToPassengerInfoList(travelPackage.getPassengers()));
            return response;
        }
        return null;
    }

    private List<PassengerInfo> mapToPassengerInfoList(List<Passenger> passengers) {
        if (nonNull(passengers)) {
            List<PassengerInfo> passengerInfoList = new ArrayList<>();
            for (Passenger passenger : passengers) {
                if (nonNull(passenger)) {
                    PassengerInfo passengerInfo = new PassengerInfo();
                    passengerInfo.setPassengerName(passenger.getName());
                    passengerInfo.setPassengerNumber(passenger.getNumber());
                    passengerInfoList.add(passengerInfo);
                }
            }
            return passengerInfoList;
        }
        return null;
    }

    private TravelPackage findTravelPackageById(String packageId) {
        if (nonNull(packageId)) {
            return travelPackageRepository.findById(packageId)
                    .orElseThrow(() -> new TravelPackageNotFoundException("Travel package not found with ID: " + packageId));
        }
        return null;
    }

    private TravelPackageItinerary mapToTravelPackageItinerary(TravelPackage travelPackage) {
        if (nonNull(travelPackage)) {
            TravelPackageItinerary itinerary = new TravelPackageItinerary();
            itinerary.setPackageName(travelPackage.getName());
            itinerary.setDestinations(new ArrayList<>());

            if (nonNull(travelPackage.getDestinations())) {
                for (Destination destination : travelPackage.getDestinations()) {
                    if (nonNull(destination)) {
                        DestinationItinerary destinationItinerary = new DestinationItinerary();
                        destinationItinerary.setDestinationName(destination.getName());
                        destinationItinerary.setActivities(mapToActivityDetailsList(destination.getActivities()));
                        itinerary.getDestinations().add(destinationItinerary);
                    }
                }
            }
            return itinerary;
        }
        return null;
    }

    private List<ActivityDetails> mapToActivityDetailsList(List<Activity> activities) {
        if (nonNull(activities)) {
            List<ActivityDetails> activityDetailsList = new ArrayList<>();
            for (Activity activity : activities) {
                if (nonNull(activity)) {
                    ActivityDetails activityDetails = new ActivityDetails();
                    activityDetails.setActivityName(activity.getName());
                    activityDetails.setActivityCost(activity.getCost());
                    activityDetails.setActivityCapacity(activity.getCapacity());
                    activityDetails.setActivityDescription(activity.getDescription());
                    activityDetailsList.add(activityDetails);
                }
            }
            return activityDetailsList;
        }
        return null;
    }
}
