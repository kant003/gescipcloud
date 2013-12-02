package com.angel.client.activity;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.angel.client.ClientFactory;
import com.angel.client.event.AlumnoLoadedEvent;
import com.angel.client.event.ListaAlumnoRefrescarEvent;
import com.angel.client.place.EditObservacionAlumnoPlace;
import com.angel.client.place.AlumnoPlace;
import com.angel.client.place.SeleccionAlumnoPlace;
import com.angel.shared.ObservacionAlumnoProxy;
import com.angel.shared.ContactRequest;
import com.angel.shared.ObservacionAlumnoRequest;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.validation.client.impl.Validation;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.Receiver;

public class EditObservacionAlumnoActivity extends AbstractActivity{
	private ClientFactory clientFactory;
	private final View display;
	EditObservacionAlumnoPlace place;
	
	private ObservacionAlumnoProxy dato;
	
	/* private static EditObservacionAlumnoActivity instance = null;
	 public static synchronized EditObservacionAlumnoActivity getInstance(EditObservacionAlumnoPlace place,
 			ClientFactory clientFactory) {
             if (instance == null) {
                     instance = new EditObservacionAlumnoActivity (place,clientFactory);
             }
             return instance;
     }*/
	
	interface MyEventBinder extends EventBinder<EditObservacionAlumnoActivity> { }
	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	 public interface Driver extends RequestFactoryEditorDriver<ObservacionAlumnoProxy, EditObservacionAlumnoActivity.View> {}
	  Driver driverEditor = GWT.create(Driver.class);
	
	public interface View extends  Editor<ObservacionAlumnoProxy>{
	   
		public HasClickHandlers getSaveButton();
		public HasClickHandlers getCancelButton(); 

		@Path("id")
		public IntegerBox getId(); 
		@Path("idAlumno")
		public IntegerBox getIdAlumno(); 
		@Path("texto")
		public TextBox getTexto(); 
				
	    Widget asWidget();
	    void setPresenter(EditObservacionAlumnoActivity presenter);
	  }
	
	

	public EditObservacionAlumnoActivity(EditObservacionAlumnoPlace place,
			ClientFactory clientFactory) {
		System.out.println("presentacion edit constructor editor");
		
		this.clientFactory = clientFactory;
		this.display = clientFactory.getEditObservacionAlumnoView();
		this.place = place;
		eventBinder.bindEventHandlers(this, clientFactory.getEventBus());
		getDatos();
	}

	
	
	/**
	 * Invoked by the ActivityManager to start a new Activity
	 */
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		System.out.println("presentacion edit start");
		// display = clientFactory.getAlumnoView();
		display.setPresenter(this);

		containerWidget.setWidget(display.asWidget());

		//getContacto();
		
	
	/*	
		if(handlerCancel != null){
			handlerCancel.removeHandler();
		}
		
	handlerCancel =	display.getCancelButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				System.out.println("envento canc");
				cancel();
				
			}
		});
	
	
	if(handlerAdd != null){
		handlerAdd.removeHandler();
	}
	
		
			handlerAdd = display.getSaveButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				System.out.println("envento salvando");
				save();
				
			}
		});
		
		*/
	}

	
	@EventHandler
	void onAlumnoLoaded(AlumnoLoadedEvent event) {
		System.out.println("presentacion llamada al onAlumnoLoad");

	}

	
	/**
	 * Ask user before stopping this activity
	 */
	@Override
	public String mayStop() {
		System.out.println("presentacion maystop");
		return null;
	}

	/**
	 * Navigate to a new Place in the browser
	 */
	public void goTo(Place place) {
		System.out.println("presentacion edit goto");
		clientFactory.getPlaceController().goTo(place);
	}

public void cancel(){
	goTo(new SeleccionAlumnoPlace("",""));
}

	public void save(){
		System.out.println("guardando edit");
	//	if (valida() == false) return;
		
		String primaryKey = place.getId();
		if (primaryKey == null || primaryKey.isEmpty()){
		
			ObservacionAlumnoRequest request = (ObservacionAlumnoRequest) driverEditor.flush();
			
			request.persist().using(dato);
			request.fire(new Receiver<Void>() {
				@Override
				public void onSuccess(Void response) {
					//Window.alert("ok añadido!");
					clientFactory.getEventBus().fireEvent(new ListaAlumnoRefrescarEvent());
					goTo(new SeleccionAlumnoPlace("",display.getIdAlumno().getText()));
				}
			});

		} else {
			ObservacionAlumnoRequest context = (ObservacionAlumnoRequest) driverEditor.flush();
			ObservacionAlumnoProxy editable = context.edit(dato);
			
			context.update().using(editable);
			context.fire(new Receiver<Void>() {
				@Override
				public void onSuccess(Void response) {
					//Window.alert("ok updatado");
					clientFactory.getEventBus().fireEvent(new ListaAlumnoRefrescarEvent());
					goTo(new SeleccionAlumnoPlace("",display.getIdAlumno().getText()));
				}
			});

		}
			
		
		

		}
	
	private boolean valida() {

		ObservacionAlumnoRequest request = (ObservacionAlumnoRequest) driverEditor.flush();
		ObservacionAlumnoProxy validableContact = request.edit(dato);
		

		Validator validator = Validation.buildDefaultValidatorFactory()
				.getValidator();

		Set<ConstraintViolation<ObservacionAlumnoProxy>> violations = validator
				.validate(validableContact);

		Set<ConstraintViolation<?>> violations2 = (Set<ConstraintViolation<?>>) (Set) violations;
		if (!violations.isEmpty()) {
			driverEditor.setConstraintViolations(violations2);
		}
		if (!violations.isEmpty()) {

			for (ConstraintViolation<ObservacionAlumnoProxy> c : violations) {
				System.out.println("violación:"+c.getMessage());
			}

			return false;
			// client-side violation(s) occurred
		} else {
			Window.alert("sin errores");
			return true;
			// client-side validation passed so check server-side
			/*
			 * greetingService.serverSideValidate(person, new
			 * AsyncCallback<SafeHtml>() {
			 * 
			 * @Override public void onFailure(Throwable caught) { if (caught
			 * instanceof ConstraintViolationException) { // server-side
			 * violation } // some other issue }
			 * 
			 * @Override public void onSuccess(SafeHtml result) { // server-side
			 * validations passed } }
			 */
		}
	}
	
	public void getDatos() {
		
		driverEditor.initialize(clientFactory.getEventBus(),
				clientFactory.getRequestFactory(), display);
		String primaryKey = place.getId();
		System.out.println("get contacto edit primary:"+primaryKey);
		if (primaryKey == null || primaryKey.isEmpty()){
			ObservacionAlumnoRequest request = clientFactory.getRequestFactory()
					.observacionAlumnoRequest();
			ObservacionAlumnoProxy nuevo = request.create(ObservacionAlumnoProxy.class);
			dato = nuevo;
			driverEditor.edit(nuevo,request);
			return;
		}else {
			if (clientFactory != null) {
				System.out.println("buscando contacto");
				clientFactory.getRequestFactory().observacionAlumnoRequest()
						.findObservacionAlumno(  Integer.parseInt(primaryKey)  )
						.fire(new Receiver<ObservacionAlumnoProxy>() {
							@Override
							public void onSuccess(ObservacionAlumnoProxy response) {
								dato = response;
								
								driverEditor.edit(response, clientFactory
										.getRequestFactory().contactRequest());
								for(int i=0;i<driverEditor.getPaths().length;i++){
								System.out.println("ruta:"+driverEditor.getPaths()[i]);
								}
							}
						});
			}
		}
	}

}