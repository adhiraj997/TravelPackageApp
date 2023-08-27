package com.nymble.application.service;

import com.nymble.application.beans.TravelPackage;
import com.nymble.application.dto.PassengerListResponse;
import com.nymble.application.dto.TravelPackageItinerary;
import com.nymble.application.dto.TravelPackageRequest;
import org.springframework.stereotype.Service;

@Service
public interface TravelPackageService {
    TravelPackageItinerary getTravelPackageItinerary(String packageId);

    PassengerListResponse getPassengerList(String packageId);
}
