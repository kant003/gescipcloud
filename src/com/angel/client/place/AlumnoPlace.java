package com.angel.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AlumnoPlace extends Place {
	private String filtro;

	public AlumnoPlace(String token) {
		this.filtro = token;
	}

	public String getFiltro() {
		return filtro;
	}

	public static class Tokenizer implements PlaceTokenizer<AlumnoPlace> {
		@Override
		public String getToken(AlumnoPlace place) {
			return place.getFiltro();
		}

		@Override
		public AlumnoPlace getPlace(String token) {
			return new AlumnoPlace(token);
		}
	}
}