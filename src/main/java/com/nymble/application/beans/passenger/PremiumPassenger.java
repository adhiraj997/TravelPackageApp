package com.nymble.application.beans.passenger;

import com.nymble.application.beans.Activity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class PremiumPassenger implements Passenger {
    @Id
    private int id;
    private String name;
    private int number;
    private List<Activity> signedUpActivities = new ArrayList<>();
    @Override
    public void signUpForActivity(Activity activity) {
        activity.addPassenger(this);
    }

    @Override
    public boolean hasSufficientBalance(double amount) {
        return true;
    }
}
