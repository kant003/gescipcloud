package com.angel.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ObservacionesAlumnoPlace extends Place {
	private String filtro;

	public ObservacionesAlumnoPlace(String token) {
		this.filtro = token;
	}

	public String getFiltro() {
		return filtro;
	}

	public static class Tokenizer implements PlaceTokenizer<ObservacionesAlumnoPlace> {
		@Override
		public String getToken(ObservacionesAlumnoPlace place) {
			return place.getFiltro();
		}

		@Override
		public ObservacionesAlumnoPlace getPlace(String token) {
			return new ObservacionesAlumnoPlace(token);
		}
	}
}