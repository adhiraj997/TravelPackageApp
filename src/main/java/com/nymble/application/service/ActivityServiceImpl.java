package com.nymble.application.service;

import com.nymble.application.beans.Activity;
import com.nymble.application.beans.passenger.Passenger;
import com.nymble.application.dto.AvailableActivityResponse;
import com.nymble.application.exceptions.ActivityNotFoundException;
import com.nymble.application.repository.ActivityRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
@NoArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public Activity signUpPassenger(Passenger passenger, String activityId) {
        return null;
    }

    @Override
    public Activity findActivityById(String activityId) {
        if (nonNull(activityId) && isNotBlank(activityId)) {
            Optional<Activity> activity = activityRepository.findById(activityId);
            if (activity.isPresent()) {
                return activity.get();
            } else {
                throw new ActivityNotFoundException("Activity with ID " + activityId + " not found.");
            }
        }
        return null;
    }

    @Override
    public Activity saveActivity(Activity activity) {
        if (nonNull(activity)) {
            return activityRepository.save(activity);
        }
        return null;
    }

    @Override
    public List<AvailableActivityResponse> getAvailableActivities() {
        List<Activity> availableActivities = findAvailableActivities();
        return mapToAvailableActivityResponseList(availableActivities);
    }

    private List<Activity> findAvailableActivities() {
        return activityRepository.findByPassengersLessThanCapacity();
    }

    private List<AvailableActivityResponse> mapToAvailableActivityResponseList(List<Activity> activities) {
        if (nonNull(activities)) {
            List<AvailableActivityResponse> availableActivityResponseList = new ArrayList<>();
            for (Activity activity : activities) {
                if (nonNull(activity)) {
                    AvailableActivityResponse response = new AvailableActivityResponse();
                    response.setActivityName(activity.getName());
                    if (nonNull(activity.getDestination())) {
                        response.setDestinationName(activity.getDestination().getName());
                    }
                    response.setActivityPrice(activity.getCost());
                    response.setAvailableSpaces(activity.getCapacity() - activity.getPassengers().size());
                    availableActivityResponseList.add(response);
                }
            }
            return availableActivityResponseList;
        }
        return null;
    }
}
