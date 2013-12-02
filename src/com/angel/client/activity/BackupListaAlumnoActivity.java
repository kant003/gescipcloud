package com.angel.client.activity;

import java.util.ArrayList;
import java.util.List;

import com.angel.client.ClientFactory;
import com.angel.client.event.AlumnoLoadedEvent;
import com.angel.client.event.ListaAlumnoRefrescarEvent;
import com.angel.client.place.EditAlumnoPlace;
import com.angel.client.place.AlumnoPlace;
import com.angel.shared.ContactProxy;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.place.shared.Place;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class BackupListaAlumnoActivity extends AbstractActivity/* implements APresenter*/ {
	// Used to obtain views, eventBus, placeController
	// Alternatively, could be injected via GIN
	private ClientFactory clientFactory;
	// Name that will be appended to "Hello,"
	private String filtro;
	private final View display = null;

	interface MyEventBinder extends EventBinder<BackupListaAlumnoActivity> {
	}

	public interface View  {
	    HasClickHandlers getAddButton();
	    HasClickHandlers getUpdateButton();
	    HasClickHandlers getDeleteButton();
	    HasClickHandlers getList();
	    void setData(List<String> data);
	    int getClickedRow(ClickEvent event);
	    List<Integer> getSelectedRows();
	    Widget asWidget();
	    void setPresenter(BackupListaAlumnoActivity presenter);
	  }
	
	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

/*	 private static ListaAlumnoActivity instance = null;
	 public static synchronized ListaAlumnoActivity getInstance(ListaAlumnoPlace place,
				ClientFactory clientFactory) {
             if (instance == null) {
                     instance = new ListaAlumnoActivity (place,clientFactory);
             }
             return instance;
     }*/
	
	public BackupListaAlumnoActivity(AlumnoPlace place,
			ClientFactory clientFactory) {
		System.out.println("presentacion constructor");
		this.filtro = place.getFiltro();
		this.clientFactory = clientFactory;
	//////	this.display = clientFactory.getAlumnoView();
		eventBinder.bindEventHandlers(this, clientFactory.getEventBus());
	
	}

	@EventHandler
	void onAlumnoLoaded(AlumnoLoadedEvent event) {
		System.out.println("presentacion llamada al onAlumnoLoad");
		// view.setSubject(email.getSubject());
		// view.setBody(email.getBody());
	}
	@EventHandler
	void onListaAlumnoRefrescar(ListaAlumnoRefrescarEvent event){
		fetch();
	}
	

	/**
	 * Invoked by the ActivityManager to start a new Activity
	 */
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		System.out.println("presentacion start");
		// display = clientFactory.getAlumnoView();
		display.setPresenter(this);
		
		containerWidget.setWidget(display.asWidget());

		fetch();
		/*
		 * HelloView helloView = clientFactory.getHelloView();
		 * helloView.setName(name); helloView.setPresenter(this);
		 * containerWidget.setWidget(helloView.asWidget());
		 */
		
		
		/*
		if(handleradd != null){
			handleradd.removeHandler();
		}
		
				
		
		handleradd = display.getAddButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				System.out.println("envento add nwe");
				addNew();
				
			}
		});
		
		
		if(handlerdel != null){
			handlerdel.removeHandler();
		}
		
			
		handlerdel = display.getDeleteButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				deleteSelected();
				
			}
		});
		
		
		if(handlerupda != null){
			handlerupda.removeHandler();
		}
		
			
		handlerupda = display.getUpdateButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				updateSelected();
				
			}
		});
		*/
	}

	/**
	 * Ask user before stopping this activity
	 */
/*	@Override
	public String mayStop() {
		System.out.println("presentacion maystop");
		return "Please hold on. This activity is stopping.";
	}*/

	/**
	 * Navigate to a new Place in the browser
	 */
	public void goTo(Place place) {
		System.out.println("presentacion goto");
		clientFactory.getPlaceController().goTo(place);
	}

	//@Override
	public void fetch() {
		// TODO Auto-generated method stub
		System.out.println("presentacion fetch:" + clientFactory + ":"
				+ clientFactory.getRequestFactory().contactRequest());

		clientFactory.getRequestFactory().contactRequest().findAllContacts()
				.fire(new Receiver<List<ContactProxy>>() {

					@Override
					public void onSuccess(List<ContactProxy> response) {
						// contactDetails = response;

						List<String> data = new ArrayList<String>();
						if (response == null || response.size() == 0)
							System.out
									.println("presentacion fetch: esta vacio");
						else {
							for (int i = 0; i < response.size(); ++i) {
								data.add(response.get(i).getFirstName());
								System.out.println("presentacion fetch dato:"
										+ response.get(i).getId() + ","
										+ response.get(i).getFirstName());
							}

							display.setData(data);
						}
					}

					@Override
					public void onFailure(ServerFailure error) {
						// TODO Auto-generated method stub
						// super.onFailure(error);
						Window.alert("Error fetching contact details:"
								+ error.getMessage());
					}
				});
	}

	//@Override
	public void deleteSelected() {
		// TODO Auto-generated method stub
		System.out.println("presentacion deleteSelected");
		List<Integer> selectedRows = display.getSelectedRows();
		
		
		for (int i = 0; i < selectedRows.size(); ++i) {
			System.out.println("presentacion se va a borrar:" + selectedRows.get(i));
		clientFactory.getRequestFactory().contactRequest()
		.findContact(  selectedRows.get(i)  )
		.fire(new Receiver<ContactProxy>() {
			@Override
			public void onSuccess(ContactProxy response) {
				System.out.println("encontrado el que vamos a borrar:" + response.getId()+";"+response.getFirstName());
				clientFactory.getRequestFactory().contactRequest().
				remove().using(response).
				fire(new Receiver<Void>() {
					@Override
					public void onSuccess(Void response) {
						Window.alert("ok borrado");
						fetch();
					}
				});
				
			}
		});
		}
		
		
		 
	}

	//@Override
	public void addNew() {
		goTo(new EditAlumnoPlace(""));
	}

	public void updateSelected(){
		List<Integer> selectedRows = display.getSelectedRows();
		if(selectedRows!=null && !selectedRows.isEmpty()){
			goTo(new EditAlumnoPlace(selectedRows.get(0).toString()));
		}
	}
	
}