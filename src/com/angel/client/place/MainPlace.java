package com.angel.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MainPlace extends Place {
	private String id;

	public MainPlace(String token) {
		this.id = token;
	}

	public String getId() {
		return id;
	}

	public static class Tokenizer implements PlaceTokenizer<MainPlace> {
		@Override
		public String getToken(MainPlace place) {
			return place.getId();
		}

		@Override
		public MainPlace getPlace(String token) {
			return new MainPlace(token);
		}
	}
}