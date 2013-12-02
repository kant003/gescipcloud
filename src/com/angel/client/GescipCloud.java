package com.angel.client;

import com.angel.client.mapper.AlumnoMapper;
import com.angel.client.mapper.AppPlaceHistoryMapper;
import com.angel.client.mapper.SeleccionAlumnoMapper;
import com.angel.client.place.AlumnoPlace;
import com.angel.client.place.SeleccionAlumnoPlace;
import com.angel.client.ui.OneWidgetLayoutPanel;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GescipCloud implements EntryPoint {
	
	
	interface Binder extends UiBinder<HTMLPanel, GescipCloud> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	HTMLPanel outer = binder.createAndBindUi(this);
	@UiField
	OneWidgetLayoutPanel alumnoPanel;
	@UiField
	OneWidgetLayoutPanel observacionesalumnoPanel;
	@UiField
	OneWidgetLayoutPanel matriculaPanel;
	@UiField
	Button prueba;
	@UiField
	Button prueba2;
	
	@UiField
	public DecoratedTabPanel tabAlumno;
	@UiField
	public DecoratedTabPanel tabDatosAlumno;
	private SimplePanel appWidget = new SimplePanel();
	// MainView mainView = new MainView();
	private Place defaultPlace = new AlumnoPlace("");
	
	//ClientFactory clientFactory;
	 ClientFactory clientFactory;
	public void onModuleLoad() {
		// Create app layout

		// Create ClientFactory using deferred binding so we can replace with
		// different
		// impls in gwt.xml
		 clientFactory = GWT.create(ClientFactory.class);
		//clientFactory.setMainView(this);
		
		EventBus eventBus = clientFactory.getEventBus();
		PlaceController placeController = clientFactory.getPlaceController();
		clientFactory.getRequestFactory().initialize(eventBus);
		
		
		// Initialize RequestFactory
		//clientFactory.initializeRequestFactory();
System.out.println("contruyendo mappers");
		// Start ActivityManager for each area with its ActivityMapper
		ActivityMapper alumnoMapper = new AlumnoMapper(
				clientFactory);
		ActivityMapper seleccionAlumnoMapper = new SeleccionAlumnoMapper(
				clientFactory);
	/*	ActivityMapper mainPanelActivityMapper = new MainPanelActivityMapper(
				clientFactory);
		ActivityMapper formularioAlumnoPanelActivityMapper = new FormularioAlumnoPanelActivityMapper(
				clientFactory);
		ActivityMapper alumnoPanelActivityMapper = new AlumnoPanelActivityMapper(
				clientFactory);
		ActivityMapper observacionesPanelActivityMapper = new ObservacionesPanelActivityMapper(
				clientFactory);
		ActivityMapper matriculaPanelActivityMapper = new MatriculaPanelActivityMapper(
				clientFactory);

		ActivityManager mainPanelActivityManager = new ActivityManager(
				mainPanelActivityMapper, eventBus);

		mainPanelActivityManager.setDisplay(appWidget);

		ActivityManager alumnoPanelActivityManager = new ActivityManager(
				alumnoPanelActivityMapper, eventBus);
		alumnoPanelActivityManager.setDisplay(alumnoPanel);

		ActivityManager formularioAlumnoPanelActivityManager = new ActivityManager(
				formularioAlumnoPanelActivityMapper, eventBus);
		formularioAlumnoPanelActivityManager.setDisplay(alumnoPanel);

		ActivityManager panelObservacionesActivityManager = new ActivityManager(
				observacionesPanelActivityMapper, eventBus);
		panelObservacionesActivityManager.setDisplay(observacionesalumnoPanel);

		ActivityManager panelMatriculaActivityManager = new ActivityManager(
				matriculaPanelActivityMapper, eventBus);
		panelMatriculaActivityManager.setDisplay(matriculaPanel);
*/
		tabAlumno.selectTab(1);
		ActivityManager alumnoManager = new ActivityManager(
				alumnoMapper, eventBus);
		alumnoManager.setDisplay(alumnoPanel);
		
		
		ActivityManager seleccionAlumnoManager = new ActivityManager(
				seleccionAlumnoMapper, eventBus);
		seleccionAlumnoManager.setDisplay(observacionesalumnoPanel);
		
		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper = GWT
				.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
				historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		// Add app layout to root layout
		RootLayoutPanel.get().add(outer);

	//	historyHandler.handleCurrentHistory();
		/*eventBus.addHandler(SeleccionAlumnoEvent.TYPE,
				new SeleccionAlumnoEventHandler() {
					@Override
					public void onSelecc(
							SeleccionAlumnoEvent authenticationEvent) {
						// authentication changed - do something
						System.out.println("tocado"
								+ clientFactory.getSelectedAlumnoNif());
						if (clientFactory.getSelectedAlumnoNif() == null)
							tabDatosAlumno.setVisible(false);
						else
							tabDatosAlumno.setVisible(true);
					}
				});*/

	
	}
	
	@UiHandler("prueba")
	 void onPruebaClick(ClickEvent event) {
    			
			
			 clientFactory.getPlaceController().goTo(new AlumnoPlace("2"));
			
		}
	 
	@UiHandler("prueba2")
	 void onPrueba2Click(ClickEvent event) {
   			
			
			 clientFactory.getPlaceController().goTo(new SeleccionAlumnoPlace("2","2"));
			
		}
	 
	/*
	
	private Place defaultPlace = new MainPlace("aa");
	
    private SimplePanel appWidget = new SimplePanel();
    private OneWidgetLayoutPanel appWidget2 = new OneWidgetLayoutPanel();
    
    public void onModuleLoad() {
    	
    	DockLayoutPanel appContainer = new DockLayoutPanel(Unit.PX);
    	ScrollPanel mainContent = new ScrollPanel();
    	ScrollPanel leftMenu = new ScrollPanel();
    	SimplePanel banner = new SimplePanel();

    	appContainer.addNorth(banner, 50);
    	appContainer.addWest(leftMenu, 300);
    	appContainer.add(mainContent);
    	
    	
    	
    	
    	
        ClientFactory clientFactory = GWT.create(ClientFactory.class);
        EventBus eventBus = clientFactory.getEventBus();
        PlaceController placeController = clientFactory.getPlaceController();

        clientFactory.getRequestFactory().initialize(eventBus);
        
        // Start ActivityManager for the main widget with our ActivityMapper
      

        System.out.println("contruyendo mappers");
        ActivityMapper mainActivityMapper = new MainActivityMapper(clientFactory);
       // ActivityMapper observacionesPanelActivityMapper = new ObservacionesPanelActivityMapper(	clientFactory);
		
	
		ActivityManager mainPanelActivityManager = new ActivityManager(
				mainActivityMapper, eventBus);
		mainPanelActivityManager.setDisplay(appWidget);

		

        
        // Start PlaceHistoryHandler with our PlaceHistoryMapper
        AppPlaceHistoryMapper historyMapper= GWT.create(AppPlaceHistoryMapper.class);
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, defaultPlace);
        
        

        RootPanel.get().add(appWidget);
        // Goes to the place represented on URL else default place
        historyHandler.handleCurrentHistory();
     //   appWidget2.add(clientFactory.getAlumnoView().asWidget());
    }*/
}
