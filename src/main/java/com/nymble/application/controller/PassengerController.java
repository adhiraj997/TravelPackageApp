package com.nymble.application.controller;

import com.nymble.application.beans.passenger.Passenger;
import com.nymble.application.dto.PassengerDetailsResponse;
import com.nymble.application.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passengers")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @PostMapping("/{passengerNumber}/activities")
    public ResponseEntity<Passenger> signUpForActivities(
            @PathVariable int passengerNumber,
            @RequestBody List<String> activityIds) {
        Passenger signedUpPassenger = passengerService.signUpForActivities(passengerNumber, activityIds);
        return ResponseEntity.ok(signedUpPassenger);
    }

    @GetMapping("/{passengerNumber}")
    public ResponseEntity<PassengerDetailsResponse> getPassengerDetails(@PathVariable int passengerNumber) {
        PassengerDetailsResponse passengerDetails = passengerService.getPassengerDetails(passengerNumber);
        return ResponseEntity.ok(passengerDetails);
    }
}
