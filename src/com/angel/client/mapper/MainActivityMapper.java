package com.angel.client.mapper;

import com.angel.client.ClientFactory;
import com.angel.client.activity.ListaAlumnoActivity;
import com.angel.client.activity.MainActivity;
import com.angel.client.place.AlumnoPlace;
import com.angel.client.place.MainPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class MainActivityMapper implements ActivityMapper {
    private ClientFactory clientFactory;

    public MainActivityMapper(ClientFactory clientFactory) {
        super();
        this.clientFactory = clientFactory;
    }

    @Override
    public Activity getActivity(Place place) {
        if (place instanceof MainPlace)
            return new MainActivity((MainPlace) place, clientFactory);
        else if (place instanceof AlumnoPlace)
       		return new ListaAlumnoActivity((AlumnoPlace) place, clientFactory);
   
    	
  /*  	if (place instanceof ListaAlumnoPlace)
			return new MainActivity(null, clientFactory);*/
		//else if (place instanceof FormularioAlumnoPlace)
	//		return new MainPanelViewActivity(((FormularioAlumnoPlace) place).getPrimaryKey(), clientFactory);
		//else if (place instanceof ViewAlumnosPlace)
			//return new MainPanelViewActivity(clientFactory);
	//	else if (place instanceof SeleccionAlumnoPlace)
		//	return new MainPanelViewActivity(((SeleccionAlumnoPlace) place).getForeinKey(), clientFactory);
	//	else if (place instanceof SeleccionAlumnoPlace)
	//		return new MainPanelViewActivity(((SeleccionAlumnoPlace) place).getForeinKey(), clientFactory);
	//	return null;
        
        
        return null;
    }
}