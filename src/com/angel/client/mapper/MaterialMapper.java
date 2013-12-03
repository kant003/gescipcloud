package com.angel.client.mapper;

import com.angel.client.ClientFactory;
import com.angel.client.activity.ListaAlumnoActivity;
import com.angel.client.activity.ListaMaterialActivity;
import com.angel.client.activity.ListaObservacionesAlumnoActivity;
import com.angel.client.place.AlumnoPlace;
import com.angel.client.place.SeleccionAlumnoPlace;
import com.angel.client.place.SeleccionMatriculaPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class MaterialMapper implements ActivityMapper {
        private ClientFactory clientFactory;
        
        public MaterialMapper(ClientFactory clientFactory) {
                super();
                this.clientFactory = clientFactory;
        }

        @Override
        public Activity getActivity(Place place) {
       
        	/*   if (place instanceof AlumnoPlace)
               		return new ListaAlumnoActivity((AlumnoPlace) place, clientFactory);
        	  */
        	
        	    if (place instanceof SeleccionMatriculaPlace)
              		return new ListaMaterialActivity((SeleccionMatriculaPlace) place, clientFactory);
             
           
                return null;
        }

}