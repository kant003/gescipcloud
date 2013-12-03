package com.angel.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class SeleccionMatriculaPlace extends Place {
	private String idAlumno;
	private String idMatricula;


	public SeleccionMatriculaPlace(String idAlumno, String idMatricula) {
		this.idAlumno = idAlumno;
		this.idMatricula = idMatricula;
	}

	public String getIdAlumno() {
		return idAlumno;
	}
	public String getIdMatricula() {
		return idMatricula;
	}

	public static class Tokenizer implements PlaceTokenizer<SeleccionMatriculaPlace> {
		@Override
		public String getToken(SeleccionMatriculaPlace place) {
			return place.getIdAlumno()+";"+place.getIdMatricula();
		}

		@Override
		public SeleccionMatriculaPlace getPlace(String token) {
			 String[] states = token.split(";");
			 String idAlumno = states[0];
			 String idMatricula = states[1];
			return new SeleccionMatriculaPlace(idAlumno,idMatricula);
		}
	}
}