package com.nymble.application.service;

import com.nymble.application.beans.Activity;
import com.nymble.application.beans.passenger.Passenger;
import com.nymble.application.dto.PassengerDetailsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PassengerService {
    public Passenger signUpForActivities(int passengerNumber, List<String> activityIds);
    Passenger signUpForActivity(int passengerNumber, String activityId);
    List<Activity> getSignedUpActivities(int passengerNumber);
    PassengerDetailsResponse getPassengerDetails(int passengerNumber);


}
