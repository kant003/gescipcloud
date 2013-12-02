package com.angel.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;

public class SubMenuPlace {

	private String id;

	public SubMenuPlace(String token) {
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