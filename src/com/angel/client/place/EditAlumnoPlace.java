package com.angel.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EditAlumnoPlace extends Place {
	private String id;

	public EditAlumnoPlace(String token) {
		this.id = token;
	}

	public String getId() {
		return id;
	}

	public static class Tokenizer implements PlaceTokenizer<EditAlumnoPlace> {
		@Override
		public String getToken(EditAlumnoPlace place) {
			return place.getId();
		}

		@Override
		public EditAlumnoPlace getPlace(String token) {
			return new EditAlumnoPlace(token);
		}
	}
}