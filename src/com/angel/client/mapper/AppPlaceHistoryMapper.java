package com.angel.client.mapper;

import com.angel.client.place.EditAlumnoPlace;
import com.angel.client.place.EditObservacionAlumnoPlace;
import com.angel.client.place.HelloPlace;
import com.angel.client.place.AlumnoPlace;
import com.angel.client.place.SeleccionAlumnoPlace;
import com.angel.client.place.MainPlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({HelloPlace.Tokenizer.class, 
	AlumnoPlace.Tokenizer.class,
	SeleccionAlumnoPlace.Tokenizer.class
	/*EditAlumnoPlace.Tokenizer.class,
	EditObservacionAlumnoPlace.Tokenizer.class,
	MainPlace.Tokenizer.class*/})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper
{
}