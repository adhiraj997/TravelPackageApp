package com.nymble.application.controller;

import com.nymble.application.dto.ActivitySignUpInfo;
import com.nymble.application.dto.AvailableActivityResponse;
import com.nymble.application.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/fetch/available/activities")
    public ResponseEntity<List<AvailableActivityResponse>> getAvailableActivities() {
        List<AvailableActivityResponse> availableActivities = activityService.getAvailableActivities();
        return ResponseEntity.ok(availableActivities);
    }
}
