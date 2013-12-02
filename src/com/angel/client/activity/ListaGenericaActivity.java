package com.angel.client.activity;

import java.util.List;
import java.util.logging.Level;

import com.angel.client.ClientFactory;
import com.angel.client.place.AlumnoPlace;
import com.angel.client.place.SeleccionMatriculaPlace;
import com.angel.shared.GenericProxy;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.MultiSelectionModel;

public abstract class ListaGenericaActivity<V extends ListaGenericaActivity.View<P>, P extends GenericProxy> extends AbstractActivity/* implements APresenter*/ {
	// Used to obtain views, eventBus, placeController Alternatively, could be injected via GIN
	protected ClientFactory clientFactory;

	public final View<P> display ;
	//private String foreignkey;
	
	public interface View<P extends GenericProxy>  {
	    HasClickHandlers getAddButton();
	    HasClickHandlers getUpdateButton();
	    HasClickHandlers getDeleteButton();
	    HasClickHandlers getRefreshButton();
	    HasValue<String> getFiltroTexto();
	    HasClickHandlers getList();
	    DataGrid<P> getDataGrid();
	    void setData(int count, int start, List<P> data);
	    int getClickedRow(ClickEvent event);
	    List<Integer> getSelectedRows();
	    Widget asWidget();
	    void setPresenter(ListaGenericaActivity presenter);
	    
	    MultiSelectionModel<P> getSelectionModel();
	   
	  }
	
	
	/* private static ListaObservacionesAlumnoActivity instance = null;
	 public static synchronized ListaObservacionesAlumnoActivity getInstance(ListaAlumnoPlace place,
				ClientFactory clientFactory) {
             if (instance == null) {
                     instance = new ListaObservacionesAlumnoActivity (place,clientFactory);
             }
             return instance;
     }*/
	
	public ListaGenericaActivity(Place place,ClientFactory clientFactory, View<P> display) {
		//System.out.println("presentacion constructor lista generica");
		this.display=display;
		this.clientFactory = clientFactory;
		//this.foreignkey = place.getForeignkey();
		//this.display = clientFactory.getObservacionesAlumnoView();
	//	eventBinder.bindEventHandlers(this, clientFactory.getEventBus());
	
	}

	
	
		
	/**
	 * Invoked by the ActivityManager to start a new Activity
	 */
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		//System.out.println(this.getClass()+" Activity start");
	//	clientFactory.getLogger().log(Level.INFO, "Activity start:"+this.getClass());
		display.setPresenter(this);
	//	display.getFiltroTexto().setValue("matric");
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
	public abstract String mayStop();
		

	/**
	 * Navigate to a new Place in the browser
	 */
	protected void goTo(Place place) {
		//System.out.println("presentacion goto");
		clientFactory.getPlaceController().goTo(place);
	}

	//@Override
	public abstract void deleteSelected();
		/*
			
		 
	}
*/
	//@Override
	public abstract void addNew();

	
	public abstract void updateSelected();
	
	public abstract void ver(int idMatricula);
		

	public abstract void onRangeChanged(HasData<P> m);
		
	
	public void refresca(){
		//	esto no está bien!!!!
		//	goTo(new SeleccionAlumnoPlace("observacionesAlumno-"+display.getFiltroTexto().getValue(),foreignkey));
		
			onRangeChanged(display.getDataGrid());
			display.getDataGrid().redraw();
		}
	
	//	System.out.println("rango cambio");
	/*	
		*/
		
		
	

	
}