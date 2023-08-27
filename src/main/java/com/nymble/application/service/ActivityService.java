package com.nymble.application.service;

import com.nymble.application.beans.Activity;
import com.nymble.application.beans.passenger.Passenger;
import com.nymble.application.dto.AvailableActivityResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityService {
    Activity signUpPassenger(Passenger passenger, String activityId);

    Activity findActivityById(String activityId);

    Activity saveActivity(Activity activity);

    List<AvailableActivityResponse> getAvailableActivities();

}
