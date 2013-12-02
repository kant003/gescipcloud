package com.angel.client.mapper;

import com.angel.client.ClientFactory;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class MatriculaPanelActivityMapper implements ActivityMapper {
        private ClientFactory clientFactory;
        
        public MatriculaPanelActivityMapper(ClientFactory clientFactory) {
                super();
                this.clientFactory = clientFactory;
        }

        @Override
        public Activity getActivity(Place place) {
               
        /*        if (place instanceof ViewAlumnosPlace)
               		return null;
                else  if (place instanceof SeleccionAlumnoPlace)
               		return new MatriculaViewActivity((SeleccionAlumnoPlace)place, clientFactory);
            */
                return null;
        }

}