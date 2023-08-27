package com.nymble.application.repository;

import com.nymble.application.beans.passenger.GoldPassenger;
import com.nymble.application.beans.passenger.StandardPassenger;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoldPassengerRepository extends MongoRepository<GoldPassenger, Integer> {
    Optional<GoldPassenger> findByNumber(int number);
}
