package com.nymble.application.controller;

import com.nymble.application.beans.TravelPackage;
import com.nymble.application.dto.PassengerListResponse;
import com.nymble.application.dto.TravelPackageItinerary;
import com.nymble.application.dto.TravelPackageRequest;
import com.nymble.application.service.TravelPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/travel-packages")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TravelPackageController {
  @Autowired
  private TravelPackageService travelPackageService;

  @GetMapping("/{packageId}/itinerary")
  public ResponseEntity<TravelPackageItinerary> getItinerary(@PathVariable String packageId) {
    return ResponseEntity.ok().body(travelPackageService.getTravelPackageItinerary(packageId));
  }

  @GetMapping("/{packageId}/passenger-list")
  public ResponseEntity<PassengerListResponse> getPassengerList(@PathVariable String packageId) {
    PassengerListResponse passengerList = travelPackageService.getPassengerList(packageId);
    return ResponseEntity.ok(passengerList);
  }
}
