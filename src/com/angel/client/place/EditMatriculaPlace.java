package com.angel.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EditMatriculaPlace extends Place {
	private String id;

	public EditMatriculaPlace(String token) {
		this.id = token;
	}

	public String getId() {
		return id;
	}

	public static class Tokenizer implements PlaceTokenizer<EditMatriculaPlace> {
		@Override
		public String getToken(EditMatriculaPlace place) {
			return place.getId();
		}

		@Override
		public EditMatriculaPlace getPlace(String token) {
			return new EditMatriculaPlace(token);
		}
	}
}