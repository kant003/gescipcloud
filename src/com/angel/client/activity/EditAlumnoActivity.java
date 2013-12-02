package com.angel.client.activity;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.angel.client.ClientFactory;
import com.angel.client.event.AlumnoLoadedEvent;
import com.angel.client.event.ListaAlumnoRefrescarEvent;
import com.angel.client.place.EditAlumnoPlace;
import com.angel.client.place.AlumnoPlace;
import com.angel.shared.AlumnoProxy;
import com.angel.shared.AlumnoRequest;
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

public class EditAlumnoActivity extends AbstractActivity{
	private ClientFactory clientFactory;
	private final View display;
	EditAlumnoPlace place;
	
	private AlumnoProxy dato;
	
	/* private static EditAlumnoActivity instance = null;
	 public static synchronized EditAlumnoActivity getInstance(EditAlumnoPlace place,
 			ClientFactory clientFactory) {
             if (instance == null) {
                     instance = new EditAlumnoActivity (place,clientFactory);
             }
             return instance;
     }*/
	
	interface MyEventBinder extends EventBinder<EditAlumnoActivity> { }
	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	 public interface Driver extends RequestFactoryEditorDriver<AlumnoProxy, EditAlumnoActivity.View> {}
	  Driver driverEditor = GWT.create(Driver.class);
	
	public interface View extends  Editor<AlumnoProxy>{
	   
		public HasClickHandlers getSaveButton();
		public HasClickHandlers getCancelButton(); 

		@Path("id")
		public IntegerBox getId(); 
		@Path("nombre")
		public TextBox getNombre(); 
		@Path("apellidos")
		public TextBox getApellidos();
		@Path("nif")
		public TextBox getNif();
		
	    Widget asWidget();
	    void setPresenter(EditAlumnoActivity presenter);
	  }
	
	

	public EditAlumnoActivity(EditAlumnoPlace place,
			ClientFactory clientFactory) {
		//System.out.println("presentacion edit constructor editor");
		
		this.clientFactory = clientFactory;
		this.display = clientFactory.getEditAlumnoView();
		this.place = place;
		eventBinder.bindEventHandlers(this, clientFactory.getEventBus());
		getDatos();
	}

	
	
	/**
	 * Invoked by the ActivityManager to start a new Activity
	 */
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		//System.out.println("presentacion edit start");
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
		//System.out.println("presentacion llamada al onAlumnoLoad");

	}

	
	/**
	 * Ask user before stopping this activity
	 */
	@Override
	public String mayStop() {
		//System.out.println("presentacion maystop");
		return null;
	}

	/**
	 * Navigate to a new Place in the browser
	 */
	public void goTo(Place place) {
		//System.out.println("presentacion edit goto");
		clientFactory.getPlaceController().goTo(place);
	}

public void cancel(){
	goTo(new AlumnoPlace(""));
}

	public void save(){
		//System.out.println("guardando edit");
	//	if (valida() == false) return;
		
		String primaryKey = place.getId();
		if (primaryKey == null || primaryKey.isEmpty()){
		
			AlumnoRequest request = (AlumnoRequest) driverEditor.flush();
			
			request.persist().using(dato);
			request.fire(new Receiver<Void>() {
				@Override
				public void onSuccess(Void response) {
					//Window.alert("ok añadido!");
					clientFactory.getEventBus().fireEvent(new ListaAlumnoRefrescarEvent());
					goTo(new AlumnoPlace(""));
				}
			});

		} else {
			AlumnoRequest context = (AlumnoRequest) driverEditor.flush();
			AlumnoProxy editable = context.edit(dato);
			
			context.update().using(editable);
			context.fire(new Receiver<Void>() {
				@Override
				public void onSuccess(Void response) {
					//Window.alert("ok updatado");
					clientFactory.getEventBus().fireEvent(new ListaAlumnoRefrescarEvent());
					goTo(new AlumnoPlace(""));
				}
			});

		}
			
		
		

		}
	
	private boolean valida() {

		AlumnoRequest request = (AlumnoRequest) driverEditor.flush();
		AlumnoProxy validableContact = request.edit(dato);
		

		Validator validator = Validation.buildDefaultValidatorFactory()
				.getValidator();

		Set<ConstraintViolation<AlumnoProxy>> violations = validator
				.validate(validableContact);

		Set<ConstraintViolation<?>> violations2 = (Set<ConstraintViolation<?>>) (Set) violations;
		if (!violations.isEmpty()) {
			driverEditor.setConstraintViolations(violations2);
		}
		if (!violations.isEmpty()) {

			for (ConstraintViolation<AlumnoProxy> c : violations) {
				//System.out.println("violación:"+c.getMessage());
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
		//System.out.println("get contacto edit primary:"+primaryKey);
		if (primaryKey == null || primaryKey.isEmpty()){
			AlumnoRequest request = clientFactory.getRequestFactory()
					.alumnoRequest();
			AlumnoProxy nuevo = request.create(AlumnoProxy.class);
			dato = nuevo;
			driverEditor.edit(nuevo,request);
			return;
		}else {
			if (clientFactory != null) {
				//System.out.println("buscando contacto");
				clientFactory.getRequestFactory().alumnoRequest()
						.findAlumno(  Integer.parseInt(primaryKey)  )
						.fire(new Receiver<AlumnoProxy>() {
							@Override
							public void onSuccess(AlumnoProxy response) {
								dato = response;
								
								driverEditor.edit(response, clientFactory
										.getRequestFactory().alumnoRequest());
								for(int i=0;i<driverEditor.getPaths().length;i++){
								//System.out.println("ruta:"+driverEditor.getPaths()[i]);
								}
							}
						});
			}
		}
	}

}