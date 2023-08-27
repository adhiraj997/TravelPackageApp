package com.nymble.application.repository;

import com.nymble.application.beans.Activity;
import com.nymble.application.beans.Destination;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {
    List<Activity> findByDestination(Destination destination);

    @Aggregation(pipeline = {
            "{$project: {_id: 1, name: 1, destination: '$destination', cost: 1, capacity: 1, passengers: 1, availableSpaces: {$subtract: ['$capacity', {$size: '$passengers'}]}}}",
            "{$match: {availableSpaces: {$gt: 0}}}"
    })
    List<Activity> findByPassengersLessThanCapacity();
}
