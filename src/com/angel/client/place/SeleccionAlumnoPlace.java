package com.angel.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class SeleccionAlumnoPlace extends Place {
	private String filtro;
	private String foreignkey;


	public SeleccionAlumnoPlace(String filtro, String foreignkey) {
		this.filtro = filtro;
		this.foreignkey = foreignkey;
	}

	public String getFiltro() {
		return filtro;
	}
	public String getForeignkey() {
		return foreignkey;
	}

	public static class Tokenizer implements PlaceTokenizer<SeleccionAlumnoPlace> {
		@Override
		public String getToken(SeleccionAlumnoPlace place) {
			return place.getFiltro()+";"+place.getForeignkey();
		}

		@Override
		public SeleccionAlumnoPlace getPlace(String token) {
			 String[] states = token.split(";");
			 String filtro = states[0];
			 String foreignkey = states[1];
			return new SeleccionAlumnoPlace(filtro,foreignkey);
		}
	}
}