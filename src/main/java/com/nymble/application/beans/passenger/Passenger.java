package com.nymble.application.beans.passenger;

import com.nymble.application.beans.Activity;

import java.util.List;

public interface Passenger {

    String getName();

    int getNumber();
    void signUpForActivity(Activity activity);
    boolean hasSufficientBalance(double amount);
    List<Activity> getSignedUpActivities();
}
