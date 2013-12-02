package com.angel.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EditObservacionAlumnoPlace extends Place {
	private String id;

	public EditObservacionAlumnoPlace(String token) {
		this.id = token;
	}

	public String getId() {
		return id;
	}

	public static class Tokenizer implements PlaceTokenizer<EditObservacionAlumnoPlace> {
		@Override
		public String getToken(EditObservacionAlumnoPlace place) {
			return place.getId();
		}

		@Override
		public EditObservacionAlumnoPlace getPlace(String token) {
			return new EditObservacionAlumnoPlace(token);
		}
	}
}