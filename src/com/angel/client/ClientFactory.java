package com.angel.client;

import java.util.logging.Logger;

import com.angel.client.activity.EditAlumnoActivity;
import com.angel.client.activity.EditMatriculaActivity;
import com.angel.client.activity.EditObservacionAlumnoActivity;
import com.angel.client.activity.ListaAlumnoActivity;
import com.angel.client.activity.ListaGenericaActivity;
import com.angel.client.activity.ListaObservacionesAlumnoActivity;
import com.angel.client.activity.MainActivity;
import com.angel.client.presenter.ContactsPresenter;
import com.angel.shared.GescipRequestFactory;
import com.angel.shared.MaterialProxy;
import com.angel.shared.MatriculaProxy;
import com.angel.shared.ObservacionAlumnoProxy;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

public interface ClientFactory {
	EventBus getEventBus();

	PlaceController getPlaceController();

	GescipRequestFactory getRequestFactory();

	ContactsPresenter.Display getContactView();
	MainActivity.View getMainView();
	
	ListaAlumnoActivity.View getAlumnoView();

	//ListaObservacionesAlumnoActivity.View getObservacionesAlumnoView();
	ListaGenericaActivity.View<ObservacionAlumnoProxy> getObservacionesAlumnoView();
	ListaGenericaActivity.View<MatriculaProxy> getMatriculaView();
	ListaGenericaActivity.View<MaterialProxy> getMaterialView();
	
	EditAlumnoActivity.View getEditAlumnoView();
	EditObservacionAlumnoActivity.View getEditObservacionAlumnoView();
	EditMatriculaActivity.View<MatriculaProxy> getEditMatriculaView();
	
	 Logger getLogger();
}