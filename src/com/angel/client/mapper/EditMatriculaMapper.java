package com.angel.client.mapper;

import com.angel.client.ClientFactory;
import com.angel.client.activity.EditMatriculaActivity;
import com.angel.client.activity.ListaAlumnoActivity;
import com.angel.client.place.AlumnoPlace;
import com.angel.client.place.EditMatriculaPlace;
import com.angel.client.place.SeleccionAlumnoPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class EditMatriculaMapper implements ActivityMapper {
        private ClientFactory clientFactory;
        
        public EditMatriculaMapper(ClientFactory clientFactory) {
                super();
                this.clientFactory = clientFactory;
        }

        @Override
        public Activity getActivity(Place place) {
        
        //	return new ListaAlumnoActivity((ListaAlumnoPlace) place, clientFactory);
        	/*  if (place instanceof MainPlace)
             		return new MainActivity((MainPlace) place, clientFactory);
        */
        	   if (place instanceof AlumnoPlace)
               		return new ListaAlumnoActivity((AlumnoPlace) place, clientFactory);
        	   else if (place instanceof SeleccionAlumnoPlace)
              		return new ListaAlumnoActivity((SeleccionAlumnoPlace) place, clientFactory);
        	   else if (place instanceof EditMatriculaPlace)
             		return new EditMatriculaActivity((EditMatriculaPlace) place, clientFactory);
             
             /*   if (place instanceof ListaAlumnoPlace)
               		return new ListaAlumnoActivity((ListaAlumnoPlace) place, clientFactory);*/
            /*    else if (place instanceof SeleccionAlumnoPlace)
               		return new ListaAlumnoActivity((SeleccionAlumnoPlace) place).getForeinKey(), clientFactory);
                else if (place instanceof FormularioAlumnoPlace)
               		return new ListaAlumnoActivity(((FormularioAlumnoPlace) place).getPrimaryKey(), clientFactory);
                return null;   */ 
                
                return null;
        }

}