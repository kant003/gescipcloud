package com.angel.client.mapper;

import com.angel.client.ClientFactory;
import com.angel.client.activity.BackupListaAlumnoActivity;
import com.angel.client.activity.EditMatriculaActivity;
import com.angel.client.activity.ListaAlumnoActivity;
import com.angel.client.activity.ListaMatriculaActivity;
import com.angel.client.activity.ListaObservacionesAlumnoActivity;
import com.angel.client.place.AlumnoPlace;
import com.angel.client.place.EditMatriculaPlace;
import com.angel.client.place.SeleccionAlumnoPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class SeleccionAlumnoMapper implements ActivityMapper {
        private ClientFactory clientFactory;
        
        public SeleccionAlumnoMapper(ClientFactory clientFactory) {
                super();
                this.clientFactory = clientFactory;
        }

        @Override
        public Activity getActivity(Place place) {
        	  if (place instanceof AlumnoPlace)
        		  return null;
        	  //else if (place instanceof ListaObservacionesAlumnoPlace)
        	  else if (place instanceof SeleccionAlumnoPlace)
             		//return new ListaObservacionesAlumnoActivity((SeleccionAlumnoPlace) place, clientFactory);
        		  return new ListaMatriculaActivity((SeleccionAlumnoPlace) place, clientFactory);

        	/*  
        	  else if (place instanceof EditMatriculaPlace)
            		return new EditMatriculaActivity((EditMatriculaPlace)place, clientFactory);
     */
        	  else if (place instanceof EditMatriculaPlace)
                  		return new EditMatriculaActivity((EditMatriculaPlace)place, clientFactory);
           
           /*     if (place instanceof ViewAlumnosPlace)
               		return null;
                else  if (place instanceof SeleccionAlumnoPlace)
               		return new ObservacionesalumnoViewActivity((SeleccionAlumnoPlace)place, clientFactory);
                else  if (place instanceof ViewMatriculaPlace)
               		return new MatriculaViewActivity((ViewMatriculaPlace)place, clientFactory);*/
                return null;
        }

}