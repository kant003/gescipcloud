package com.angel.client;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.angel.client.activity.EditAlumnoActivity;
import com.angel.client.activity.EditMatriculaActivity;
import com.angel.client.activity.EditObservacionAlumnoActivity;
import com.angel.client.activity.ListaAlumnoActivity;
import com.angel.client.activity.ListaGenericaActivity;
import com.angel.client.activity.ListaObservacionesAlumnoActivity;
import com.angel.client.activity.MainActivity;
import com.angel.client.presenter.ContactsPresenter;
import com.angel.client.presenter.ContactsPresenter.Display;
import com.angel.client.ui.ContactsViewImpl;
import com.angel.client.ui.EditAlumnoViewImpl;
import com.angel.client.ui.EditMatriculaViewImpl;
import com.angel.client.ui.EditObservacionAlumnoViewImpl;
import com.angel.client.ui.ListaAlumnoViewImpl;
import com.angel.client.ui.ListaMaterialViewImpl;
import com.angel.client.ui.ListaMatriculaViewImpl;
import com.angel.client.ui.ListaObservacionesAlumnoViewImpl;
import com.angel.client.ui.MainViewImpl;
import com.angel.shared.GescipRequestFactory;
import com.angel.shared.MaterialProxy;
import com.angel.shared.MatriculaProxy;
import com.angel.shared.ObservacionAlumnoProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.logging.client.HasWidgetsLogHandler;
import com.google.gwt.logging.client.LoggingPopup;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.PopupPanel;

public class ClientFactoryImpl implements ClientFactory {

	private final EventBus eventBus = new SimpleEventBus();
	private final PlaceController placeController = new PlaceController(
			eventBus);
	private static final GescipRequestFactory requestFactory = GWT
			.create(GescipRequestFactory.class);

	
	private final ContactsPresenter.Display contactView = new ContactsViewImpl();
	private final MainActivity.View mainView = new MainViewImpl();

	private final ListaAlumnoActivity.View alumnoView = new ListaAlumnoViewImpl();
	//private final ListaObservacionesAlumnoActivity.View observacionesAlumnoView = new ListaObservacionesAlumnoViewImpl();
	private final ListaGenericaActivity.View<ObservacionAlumnoProxy> observacionesAlumnoView = new ListaObservacionesAlumnoViewImpl();
	private final ListaGenericaActivity.View<MatriculaProxy> matriculaView = new ListaMatriculaViewImpl();
	private final ListaGenericaActivity.View<MaterialProxy> materialView = new ListaMaterialViewImpl();
	
	private final EditAlumnoActivity.View editAlumnoView = new EditAlumnoViewImpl();
	private final EditObservacionAlumnoActivity.View editObservacionAlumnoView = new EditObservacionAlumnoViewImpl();
	private final EditMatriculaActivity.View<MatriculaProxy> editMatriculaView = new EditMatriculaViewImpl();

	private static final Logger logger = Logger.getLogger("");

	static {
	    for (Handler handler : logger.getHandlers()) {
	        handler.setFormatter(new LogFormatter());
	        handler.setLevel(Level.ALL);
	    }
	    
	  
	}
	
	
	
	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public GescipRequestFactory getRequestFactory() {
		return requestFactory;
	}

	@Override
	public Display getContactView() {
		return contactView;
	}

	@Override
	public ListaAlumnoActivity.View getAlumnoView() {
		return alumnoView;
	}

	@Override
	public EditAlumnoActivity.View getEditAlumnoView() {
		return editAlumnoView;
	}
	
	@Override
	public EditObservacionAlumnoActivity.View getEditObservacionAlumnoView() {
		return editObservacionAlumnoView;
	}

	@Override
	public EditMatriculaActivity.View<MatriculaProxy> getEditMatriculaView() {
		return editMatriculaView;
	}
	

	
	@Override
	public ListaGenericaActivity.View<ObservacionAlumnoProxy> getObservacionesAlumnoView() {
		return observacionesAlumnoView;
	}

	@Override
	public ListaGenericaActivity.View<MatriculaProxy> getMatriculaView() {
		return matriculaView;
	}
	
	@Override
	public ListaGenericaActivity.View<MaterialProxy> getMaterialView() {
		return materialView;
	}


	@Override
	public MainActivity.View getMainView() {
		// TODO Auto-generated method stub
		return mainView;
	}

	public  Logger getLogger(){
		return logger;
	}
}
