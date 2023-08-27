package com.nymble.application.service;

import com.nymble.application.beans.Activity;
import com.nymble.application.dto.AvailableActivityResponse;
import com.nymble.application.exceptions.ActivityNotFoundException;
import com.nymble.application.repository.ActivityRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class ActivityServiceImplTest {

    @Mock
    private ActivityRepository activityRepository;

    @InjectMocks
    private ActivityServiceImpl activityService;

    @Test
    public void testFindActivityById_ExistingActivity() {
        String activityId = "sampleActivityId";
        Activity sampleActivity = new Activity();
        sampleActivity.setId(activityId);

        when(activityRepository.findById(eq(activityId))).thenReturn(Optional.of(sampleActivity));
        Activity foundActivity = activityService.findActivityById(activityId);

        assertNotNull(foundActivity);
        assertEquals(activityId, foundActivity.getId());
    }

    @Test
    public void testFindActivityById_NonExistingActivity() {
        when(activityRepository.findById(anyString())).thenReturn(Optional.empty());
        assertThrows(ActivityNotFoundException.class, () -> activityService.findActivityById("nonExistingId"));
    }

    @Test
    public void testSaveActivity() {
        Activity sampleActivity = new Activity();
        activityService.saveActivity(sampleActivity);
        // Verify that activityRepository.save() was called once with the sampleActivity
        verify(activityRepository, times(1)).save(eq(sampleActivity));
    }

    @Test
    public void testGetAvailableActivities() {
        Activity activity1 = new Activity();
        activity1.setId("activityId1");
        activity1.setName("activity1");
        Activity activity2 = new Activity();
        activity2.setId("activityId2");
        activity2.setName("activity2");
        List<Activity> availableActivities = Arrays.asList(activity1, activity2);

        when(activityRepository.findByPassengersLessThanCapacity()).thenReturn(availableActivities);

        List<AvailableActivityResponse> availableActivityResponses = activityService.getAvailableActivities();

        assertNotNull(availableActivityResponses);
        assertEquals(2, availableActivityResponses.size());
        assertEquals("activity1", availableActivityResponses.get(0).getActivityName());
        assertEquals("activity2", availableActivityResponses.get(1).getActivityName());
    }
}
