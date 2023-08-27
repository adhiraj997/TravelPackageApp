package com.nymble.application.service;

import com.nymble.application.beans.Destination;
import com.nymble.application.beans.TravelPackage;
import com.nymble.application.dto.PassengerListResponse;
import com.nymble.application.dto.TravelPackageItinerary;
import com.nymble.application.dto.TravelPackageRequest;
import com.nymble.application.exceptions.TravelPackageNotFoundException;
import com.nymble.application.repository.TravelPackageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class TravelPackageServiceImplTest {

    @InjectMocks
    private TravelPackageServiceImpl travelPackageService;

    @Mock
    private TravelPackageRepository travelPackageRepository;

    @Test
    public void testGetTravelPackageItinerary_ExistingPackage() {
        String packageId = "samplePackageId";
        TravelPackage samplePackage = new TravelPackage();
        samplePackage.setName("samplePackage");

        when(travelPackageRepository.findById(eq(packageId))).thenReturn(Optional.of(samplePackage));

        TravelPackageItinerary travelPackageItinerary = travelPackageService.getTravelPackageItinerary(packageId);

        assertNotNull(travelPackageItinerary);
        assertEquals(samplePackage.getName(), travelPackageItinerary.getPackageName());
    }

    @Test
    public void testGetTravelPackageItinerary_NonExistingPackage() {
        when(travelPackageRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(TravelPackageNotFoundException.class, () -> travelPackageService.getTravelPackageItinerary("nonExistingId"));
    }

    @Test
    public void testGetPassengerList_ExistingPackage() {
        String packageId = "samplePackageId";
        TravelPackage samplePackage = new TravelPackage();
        samplePackage.setName("samplePackage");

        when(travelPackageRepository.findById(eq(packageId))).thenReturn(Optional.of(samplePackage));

        PassengerListResponse passengerListResponse = travelPackageService.getPassengerList(packageId);

        assertNotNull(passengerListResponse);
        assertEquals(passengerListResponse.getPackageName(), samplePackage.getName());
    }

    @Test
    public void testGetPassengerList_NonExistingPackage() {
        when(travelPackageRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(TravelPackageNotFoundException.class, () -> travelPackageService.getPassengerList("nonExistingId"));
    }
}
