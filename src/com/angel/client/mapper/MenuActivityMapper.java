package com.angel.client.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
public class MenuActivityMapper implements ActivityMapper {

    private Activity currentActivity;
    
    @Override
    public Activity getActivity(Place place) {
     /*   if (place instanceof MainMenuPlace){
            return new MainMenuActivity();
        } else if (place instanceof SubMenuPlace) {
            return new SubMenuActivity();
        } else {
            //Some other content Place that we aren't interested in
            return currentActivity;
        } */
          return currentActivity;
    }

}  