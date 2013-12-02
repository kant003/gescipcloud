package com.angel.client.activity;

import java.util.ArrayList;
import java.util.List;

import com.angel.client.ClientFactory;
import com.angel.client.place.EditAlumnoPlace;
import com.angel.client.place.AlumnoPlace;
import com.angel.client.place.SeleccionAlumnoPlace;
import com.angel.shared.AlumnoProxy;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class ListaAlumnoActivity extends AbstractActivity {
	// Used to obtain views, eventBus, placeController Alternatively, could be injected via GIN
	private ClientFactory clientFactory;
	
	private final View display;


	public interface View  {
	    HasClickHandlers getAddButton();
	    HasClickHandlers getUpdateButton();
	    HasClickHandlers getDeleteButton();
	    HasValue<String> getFiltroTexto();
	    HasClickHandlers getList();
	    DataGrid<AlumnoProxy> getDataGrid();
	    void setData(int count, int start, List<AlumnoProxy> data);
	    int getClickedRow(ClickEvent event);
	    List<Integer> getSelectedRows();
	    Widget asWidget();
	    void setPresenter(ListaAlumnoActivity presenter);
	  
	    MultiSelectionModel<AlumnoProxy> getSelectionModel();
	   
	  }
	
	
	
	public ListaAlumnoActivity(SeleccionAlumnoPlace place,
			ClientFactory clientFactory) {
		System.out.println("presentacion constructor lista alumno");
	/*	String prefijo = "alumno-";
		if(place.getFiltro().startsWith(prefijo)){
			this.filtro = place.getFiltro().substring(prefijo.length());	
		}
		*/
		this.clientFactory = clientFactory;
		this.display = clientFactory.getAlumnoView();
	//	eventBinder.bindEventHandlers(this, clientFactory.getEventBus());
	
	}
	public ListaAlumnoActivity(AlumnoPlace place,
			ClientFactory clientFactory) {
		System.out.println("presentacion constructor lista alumno");
		/*String prefijo = "alumno-";
		if(place.getFiltro().startsWith(prefijo)){
			this.filtro = place.getFiltro().substring(prefijo.length());	
		}*/
		
		this.clientFactory = clientFactory;
		this.display = clientFactory.getAlumnoView();
	//	eventBinder.bindEventHandlers(this, clientFactory.getEventBus());
	
	}

	
	
		
	/**
	 * Invoked by the ActivityManager to start a new Activity
	 */
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		System.out.println("presentacion start lista alumno");
		
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
		System.out.println("presentacion deleteSelected lista alumno");
		List<Integer> a = new ArrayList<Integer>();
		for(AlumnoProxy c : display.getSelectionModel().getSelectedSet()){
			a.add(c.getId());
		}
		//display.getSelectionModel().getSelectedSet()
			clientFactory.getRequestFactory().
			alumnoRequest().remove( a ).
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
		goTo(new EditAlumnoPlace(""));
	}

	
	
	public void updateSelected(){
		
		if(display.getSelectionModel().getSelectedSet()!=null && !display.getSelectionModel().getSelectedSet().isEmpty()){
			AlumnoProxy c = display.getSelectionModel().getSelectedSet().iterator().next();
			goTo(new EditAlumnoPlace(String.valueOf(c.getId())));
		}
	}
	
	
		

	public void onRangeChanged(HasData<AlumnoProxy> m) {
		System.out
		.println("onRancheChanged alumno lista");
	//	System.out.println("rango cambio");
		final Range range = m.getVisibleRange();
		final int start = range.getStart();
		final int end = start + range.getLength();

		clientFactory.getRequestFactory().alumnoRequest().findAll(display.getFiltroTexto().getValue(),start,end)
		.fire(new Receiver<List<AlumnoProxy>>() {

			@Override
			public void onSuccess(final List<AlumnoProxy> response) {
				// contactDetails = response;
				System.out.println("listado correcto");
				List<String> data = new ArrayList<String>();
				if (response == null || response.size() == 0){
					System.out.println("presentacion fetch: esta vacio");
					display.setData(0, 0, response);
				}else {
			
					System.out.println("contando");
					
					clientFactory.getRequestFactory().alumnoRequest().count(display.getFiltroTexto().getValue())
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
	
	
	public void ver(int id){
		String ids = ""+id;
		clientFactory.getPlaceController().goTo(new SeleccionAlumnoPlace(null,ids));
	}
	

	public void refresca(){
	//	esto no está bien!!!!
	//	goTo(new SeleccionAlumnoPlace("observacionesAlumno-"+display.getFiltroTexto().getValue(),foreignkey));
	
		onRangeChanged(display.getDataGrid());
		display.getDataGrid().redraw();
	}
}