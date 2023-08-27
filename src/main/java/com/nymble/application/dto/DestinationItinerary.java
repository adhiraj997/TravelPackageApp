package com.nymble.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DestinationItinerary {
    private String destinationName;
    private List<ActivityDetails> activities;
}
