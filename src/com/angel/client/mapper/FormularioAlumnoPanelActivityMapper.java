package com.angel.client.mapper;

import com.angel.client.ClientFactory;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class FormularioAlumnoPanelActivityMapper implements ActivityMapper {
        private ClientFactory clientFactory;
        
        public FormularioAlumnoPanelActivityMapper(ClientFactory clientFactory) {
                super();
                this.clientFactory = clientFactory;
        }

        @Override
        public Activity getActivity(Place place) {
               
          /*      if (place instanceof ViewAlumnosPlace)
               		return new AlumnoViewActivity(((ViewAlumnosPlace) place).getSeleccionado(), clientFactory);
                else if (place instanceof FormularioAlumnoPlace)
               		return new FormularioAlumnoActivity((FormularioAlumnoPlace)place, clientFactory);
                else if (place instanceof SeleccionAlumnoPlace)
               		return new AlumnoViewActivity(((SeleccionAlumnoPlace) place).getForeinKey(),clientFactory);
                */
                return null;
        }

}