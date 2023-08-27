package com.nymble.application.beans.passenger;

import com.nymble.application.beans.Activity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoldPassenger implements Passenger {
    @Id
    private int id;
    private String name;
    private int number;
    private double balance;
    private List<Activity> signedUpActivities = new ArrayList<>();
    @Override
    public void signUpForActivity(Activity activity) {
        double discountedAmount = activity.getCost() * 0.9;
        if (hasSufficientBalance(discountedAmount)) {
            balance -= discountedAmount;
            activity.addPassenger(this);
        }
    }

    @Override
    public boolean hasSufficientBalance(double amount) {
        return balance >= amount;
    }
}
