package com.nymble.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerListResponse {
    private String packageName;
    private int passengerCapacity;
    private int enrolledPassengerCount;
    private List<PassengerInfo> passengers;
}
