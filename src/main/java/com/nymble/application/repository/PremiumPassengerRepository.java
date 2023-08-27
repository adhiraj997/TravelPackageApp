package com.nymble.application.repository;

import com.nymble.application.beans.passenger.PremiumPassenger;
import com.nymble.application.beans.passenger.StandardPassenger;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PremiumPassengerRepository extends MongoRepository<PremiumPassenger, Integer> {
    Optional<PremiumPassenger> findByNumber(int number);
}
