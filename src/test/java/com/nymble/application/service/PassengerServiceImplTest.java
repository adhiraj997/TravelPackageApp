package com.nymble.application.service;

import com.nymble.application.beans.Activity;
import com.nymble.application.beans.passenger.Passenger;
import com.nymble.application.beans.passenger.StandardPassenger;
import com.nymble.application.repository.ActivityRepository;
import com.nymble.application.repository.StandardPassengerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class PassengerServiceImplTest {

    @Mock
    private StandardPassengerRepository standardPassengerRepository;

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private ActivityService activityService;

    @InjectMocks
    private PassengerServiceImpl passengerService;

    @Test
    public void testSignUpForActivities_ActivitiesWithSpace() {
        // Create a sample passenger and activity IDs
        int passengerNumber = 123;
        String activityId1 = "activityId1";
        String activityId2 = "activityId2";

        StandardPassenger samplePassenger = new StandardPassenger();
        samplePassenger.setNumber(passengerNumber);

        Activity activity1 = new Activity();
        activity1.setId(activityId1);
        activity1.setCapacity(10);

        Activity activity2 = new Activity();
        activity2.setId(activityId2);
        activity2.setCapacity(5);

        List<String> activityIds = Arrays.asList(activityId1, activityId2);

        when(standardPassengerRepository.findByNumber(eq(passengerNumber))).thenReturn(Optional.of(samplePassenger));

        when(activityRepository.findAllById(eq(activityIds))).thenReturn(Arrays.asList(activity1, activity2));

        Passenger signedUpPassenger = passengerService.signUpForActivities(passengerNumber, activityIds);

        assertNotNull(signedUpPassenger);
        assertEquals(2, signedUpPassenger.getSignedUpActivities().size());
        assertEquals(activityId1, signedUpPassenger.getSignedUpActivities().get(0).getId());
        assertEquals(activityId2, signedUpPassenger.getSignedUpActivities().get(1).getId());
    }

}
