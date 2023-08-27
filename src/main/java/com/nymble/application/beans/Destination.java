package com.nymble.application.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Destination {
    private String name;
    private List<Activity> activities;

    public void addActivity(Activity activity) {
        activities.add(activity);
    }
}
