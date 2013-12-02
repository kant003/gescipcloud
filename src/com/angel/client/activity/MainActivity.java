package com.angel.client.activity;


import com.angel.client.ClientFactory;
import com.angel.client.mapper.AlumnoMapper;
import com.angel.client.place.MainPlace;
import com.angel.client.ui.OneWidgetLayoutPanel;
import com.angel.client.ui.TopPanel;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainActivity extends AbstractActivity  {
	// Used to obtain views, eventBus, placeController
		// Alternatively, could be injected via GIN
		private ClientFactory clientFactory;
		// Name that will be appended to "Hello,"
		
		private final View display;


		public interface View  {
			public TopPanel getTopPanel();
			public OneWidgetLayoutPanel getAlumnoPanel();
			public OneWidgetLayoutPanel getObservacionesalumnoPanel();
			public OneWidgetLayoutPanel getMatriculaPanel();
			public DecoratedTabPanel getTabAlumno(); 
			
		    Widget asWidget();
		    void setPresenter(MainActivity presenter);
		  }
		
			
		public MainActivity(MainPlace place, ClientFactory clientFactory) {
			System.out.println("presentacion constructor main");

			this.clientFactory = clientFactory;
			this.display = clientFactory.getMainView();
		//	eventBinder.bindEventHandlers(this, clientFactory.getEventBus());
		
		}

		
		
			
		/**
		 * Invoked by the ActivityManager to start a new Activity
		 */
		@Override
		public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
			System.out.println("presentacion start main");
			
			display.setPresenter(this);
			
			//preparaColumnas();
			
			containerWidget.setWidget(display.asWidget());

			 display.getAlumnoPanel().add(clientFactory.getAlumnoView().asWidget());
			
		/*	 ActivityMapper alumnoPanelActivityMapper = new AlumnoPanelActivityMapper(clientFactory);
		        
				ActivityManager alumnoPanelActivityManager = new ActivityManager(
						alumnoPanelActivityMapper, eventBus);
				alumnoPanelActivityManager.setDisplay(display.getAlumnoPanel());*/
		}

		/**
		 * Ask user before stopping this activity
		 */
		@Override
		public String mayStop() {
			System.out.println("presentacion main maystop");
			return null;
		}

		/**
		 * Navigate to a new Place in the browser
		 */
		public void goTo(Place place) {
			System.out.println("presentacion main goto");
			clientFactory.getPlaceController().goTo(place);
		}

	
	
	}