package com.nymble.application.beans;

import com.nymble.application.beans.passenger.Passenger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "activities")
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    @Id
    private String id;
    private String name;
    private String description;
    private double cost;
    private int capacity;
    private Destination destination;
    private List<Passenger> passengers = new ArrayList<>();

    public void addPassenger(Passenger passenger) {
        if (passengers.size() < capacity) {
            passengers.add(passenger);
        }
    }

    public boolean hasSpace() {
        return passengers.size() < capacity;
    }
}
