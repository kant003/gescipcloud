package com.angel.client.ui;

import com.angel.client.ClientFactory;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.angel.client.activity.*;
import com.angel.client.place.AlumnoPlace;

public class MainViewImpl extends Composite implements MainActivity.View{

	 @UiField TopPanel topPanel;
     @UiField public OneWidgetLayoutPanel alumnoPanel;
     @UiField public OneWidgetLayoutPanel observacionesalumnoPanel;
     @UiField public OneWidgetLayoutPanel matriculaPanel;
     @UiField public DecoratedTabPanel tabAlumno;
     @UiField public Button prueba;
     
 	MainActivity presenter;
 	
	private static MainViewImplUiBinder uiBinder = GWT
			.create(MainViewImplUiBinder.class);

	interface MainViewImplUiBinder extends UiBinder<Widget, MainViewImpl> {
	}

	public MainViewImpl() {
		System.out.println("contructor de view Main");
		initWidget(uiBinder.createAndBindUi(this));
		//tabAlumno.selectTab(1);
	}

	

	public TopPanel getTopPanel() {
		return topPanel;
	}

	

	public OneWidgetLayoutPanel getAlumnoPanel() {
		return alumnoPanel;
	}

	public OneWidgetLayoutPanel getObservacionesalumnoPanel() {
		return observacionesalumnoPanel;
	}
	
	public OneWidgetLayoutPanel getMatriculaPanel() {
		return matriculaPanel;
	}

	

	public DecoratedTabPanel getTabAlumno() {
		return tabAlumno;
	}

	@Override
	public void setPresenter(MainActivity presenter) {
		this.presenter = presenter;
		
	}

	
	
/*	@UiHandler("tabAlumno")
	void onTabAlumnoSelection(SelectionEvent<Integer> event) {
		System.out.println("tab sel:"+event.getSelectedItem());
		if((Integer)event.getSelectedItem() == 1){
			//clientFactory.getPlaceController().goTo(new ViewAlumnosPlace());
		}
	}*/
	
	
	
	 @UiHandler("prueba")
	 void onPruebaClick(ClickEvent event) {
     			
			//	 clientFactory.getPlaceController().goTo(new ListaAlumnoPlace("2"));
			
		}
	 
}
