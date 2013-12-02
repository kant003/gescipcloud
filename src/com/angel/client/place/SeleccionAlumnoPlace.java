package com.angel.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class SeleccionAlumnoPlace extends Place {
private String idAlumno;


	public SeleccionAlumnoPlace(String idAlumno) {
		
		this.idAlumno = idAlumno;
	}

	public String getIdAlumno() {
		return idAlumno;
	}
	

	public static class Tokenizer implements PlaceTokenizer<SeleccionAlumnoPlace> {
		@Override
		public String getToken(SeleccionAlumnoPlace place) {
			return place.getIdAlumno();
		}

		@Override
		public SeleccionAlumnoPlace getPlace(String token) {
			
			return new SeleccionAlumnoPlace(token);
		}
	}
}