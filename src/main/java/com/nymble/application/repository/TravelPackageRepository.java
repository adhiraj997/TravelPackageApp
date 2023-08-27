package com.nymble.application.repository;

import com.nymble.application.beans.TravelPackage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TravelPackageRepository extends MongoRepository<TravelPackage, String>  {
    Optional<TravelPackage> findById(String extId);
}
