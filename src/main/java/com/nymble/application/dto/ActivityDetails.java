package com.nymble.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDetails {
    private String activityName;
    private double activityCost;
    private int activityCapacity;
    private String activityDescription;
}
