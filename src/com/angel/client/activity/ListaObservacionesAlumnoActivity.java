package com.angel.client.activity;

import java.util.ArrayList;
import java.util.List;

import com.angel.client.ClientFactory;
import com.angel.client.place.EditObservacionAlumnoPlace;
import com.angel.client.place.SeleccionAlumnoPlace;
import com.angel.shared.ObservacionAlumnoProxy;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class ListaObservacionesAlumnoActivity extends AbstractActivity/* implements APresenter*/ {
	// Used to obtain views, eventBus, placeController Alternatively, could be injected via GIN
	private ClientFactory clientFactory;
	private String filtro;
	private String foreignkey;
	private final View display;


	public interface View  {
	    HasClickHandlers getAddButton();
	    HasClickHandlers getUpdateButton();
	    HasClickHandlers getDeleteButton();
	    HasClickHandlers getList();
	    DataGrid<ObservacionAlumnoProxy> getDataGrid();
	    void setData(int count, int start, List<ObservacionAlumnoProxy> data);
	    int getClickedRow(ClickEvent event);
	    List<Integer> getSelectedRows();
	    Widget asWidget();
	    void setPresenter(ListaObservacionesAlumnoActivity presenter);
	    
	    MultiSelectionModel<ObservacionAlumnoProxy> getSelectionModel();
	   
	  }
	
	
	/* private static ListaObservacionesAlumnoActivity instance = null;
	 public static synchronized ListaObservacionesAlumnoActivity getInstance(ListaAlumnoPlace place,
				ClientFactory clientFactory) {
             if (instance == null) {
                     instance = new ListaObservacionesAlumnoActivity (place,clientFactory);
             }
             return instance;
     }*/
	
	public ListaObservacionesAlumnoActivity(SeleccionAlumnoPlace place,
			ClientFactory clientFactory) {
		System.out.println("presentacion constructor lista observ");
		this.filtro = place.getFiltro();
		this.foreignkey = place.getForeignkey();
		this.clientFactory = clientFactory;
		this.display = clientFactory.getObservacionesAlumnoView();
	//	eventBinder.bindEventHandlers(this, clientFactory.getEventBus());
	
	}

	
	
		
	/**
	 * Invoked by the ActivityManager to start a new Activity
	 */
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		System.out.println("presentacion start lista observaciones");
		
		display.setPresenter(this);
		
		//preparaColumnas();
		
		containerWidget.setWidget(display.asWidget());

		

		//if (display.getSelectionModel().getSelectedObject() == null) {
			onRangeChanged(display.getDataGrid());
		
			display.getDataGrid().redraw();
		//}
		
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
		System.out.println("presentacion goto");
		clientFactory.getPlaceController().goTo(place);
	}

	//@Override
	public void deleteSelected() {
		System.out.println("presentacion deleteSelected lista observ");
		List<Integer> a = new ArrayList<Integer>();
		for(ObservacionAlumnoProxy c : display.getSelectionModel().getSelectedSet()){
			a.add(c.getId());
		}
		//display.getSelectionModel().getSelectedSet()
			clientFactory.getRequestFactory().
			contactRequest().remove( a ).
			fire(new Receiver<Integer>() {
				@Override
				public void onSuccess(Integer response) {
					Window.alert("ok borrados:"+response);
					
					onRangeChanged(display.getDataGrid());
					display.getDataGrid().redraw();
				}
			});	
		
				
		 
	}

	//@Override
	public void addNew() {
		goTo(new EditObservacionAlumnoPlace(""));
	}

	
	public void updateSelected(){
		
		if(display.getSelectionModel().getSelectedSet()!=null && !display.getSelectionModel().getSelectedSet().isEmpty()){
			ObservacionAlumnoProxy c = display.getSelectionModel().getSelectedSet().iterator().next();
			goTo(new EditObservacionAlumnoPlace(String.valueOf(c.getId())));
		}
	}
	
	
		

	public void onRangeChanged(HasData<ObservacionAlumnoProxy> m) {
		System.out
		.println("onRancheChanged observaciones lista");
	//	System.out.println("rango cambio");
		final Range range = m.getVisibleRange();
		final int start = range.getStart();
		final int end = start + range.getLength();

		clientFactory.getRequestFactory().observacionAlumnoRequest().findAll(Integer.parseInt(foreignkey),null,start,end)
		.fire(new Receiver<List<ObservacionAlumnoProxy>>() {

			@Override
			public void onSuccess(final List<ObservacionAlumnoProxy> response) {
				// contactDetails = response;

				List<String> data = new ArrayList<String>();
				if (response == null || response.size() == 0)
					System.out.println("presentacion fetch: esta vacio");
				else {
			

					
					
					clientFactory.getRequestFactory().observacionAlumnoRequest().count(Integer.parseInt(foreignkey),null)
					.fire(new Receiver<Integer>() {

						@Override
						public void onSuccess(Integer count) {
							// contactDetails = response;

							display.setData(count, start, response);
							
						}

						@Override
						public void onFailure(ServerFailure error) {
							
							Window.alert("Error count contact details:"
									+ error.getMessage());
						}
					});
					
					
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
	
	
}