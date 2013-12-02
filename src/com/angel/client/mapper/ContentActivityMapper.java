package com.angel.client.mapper;


import com.angel.client.ClientFactory;
import com.angel.client.activity.*;
import com.angel.client.place.*;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class ContentActivityMapper implements ActivityMapper {
    private ClientFactory clientFactory;

    public ContentActivityMapper(ClientFactory clientFactory) {
        super();
        this.clientFactory = clientFactory;
    }

    @Override
    public Activity getActivity(Place place) {
        if (place instanceof HelloPlace)
            return new HelloActivity((HelloPlace) place, clientFactory);
        else if (place instanceof AlumnoPlace)
            return new ListaAlumnoActivity((AlumnoPlace) place, clientFactory);
        else if (place instanceof SeleccionAlumnoPlace)
            return new ListaObservacionesAlumnoActivity((SeleccionAlumnoPlace) place, clientFactory);
        //	return ListaAlumnoActivity.getInstance((ListaAlumnoPlace) place, clientFactory);
        else if (place instanceof EditAlumnoPlace)
            return new EditAlumnoActivity((EditAlumnoPlace) place, clientFactory);
        else if (place instanceof EditObservacionAlumnoPlace)
            return new EditObservacionAlumnoActivity((EditObservacionAlumnoPlace) place, clientFactory);
        	//return EditAlumnoActivity.getInstance((EditAlumnoPlace) place, clientFactory);
        return null;
    }
}