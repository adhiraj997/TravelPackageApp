package com.nymble.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDetailsResponse {
    private String passengerName;
    private int passengerNumber;
    private Double balance;
    private List<ActivitySignUpInfo> signedUpActivities;
}
