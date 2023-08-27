package com.nymble.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelPackageItinerary {
    private String packageName;
    private List<DestinationItinerary> destinations;
}
