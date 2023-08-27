package com.nymble.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableActivityResponse {
    private String activityName;
    private String destinationName;
    private double activityPrice;
    private int availableSpaces;
}
