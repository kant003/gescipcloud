package com.angel.client.ui;

import com.angel.client.activity.EditObservacionAlumnoActivity;
import com.angel.shared.ObservacionAlumnoProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
public class EditObservacionAlumnoViewImpl extends Composite implements
		EditObservacionAlumnoActivity.View, Editor<ObservacionAlumnoProxy> {

	private static EditObservacionAlumnoViewImplUiBinder uiBinder = GWT
			.create(EditObservacionAlumnoViewImplUiBinder.class);

	interface EditObservacionAlumnoViewImplUiBinder extends
			UiBinder<Widget, EditObservacionAlumnoViewImpl> {
	}

	public EditObservacionAlumnoViewImpl() {
		System.out.println("contructor de view edit observ");
		initWidget(uiBinder.createAndBindUi(this));
		
		cancelButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				presenter.cancel();
				
			}
		});
		saveButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				presenter.save();
				
			}
		});
	}
	
	

	@UiField
	Button saveButton;
	@UiField
	Button cancelButton;
	@UiField
	IntegerBox id;
	@UiField
	IntegerBox idAlumno;
	@UiField
	TextBox texto;
	

	EditObservacionAlumnoActivity presenter;
	
/*	public EditObservacionAlumnoViewImpl(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		
		
	}
*/
	/* IMPLEMENTACIÓN DE EditContactPresenter.Display  */

	@Override
	public HasClickHandlers getSaveButton() {
		// TODO Auto-generated method stub
		return saveButton;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		// TODO Auto-generated method stub
		return cancelButton;
	}

	@Override
	public IntegerBox getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public IntegerBox getIdAlumno() {
		// TODO Auto-generated method stub
		return idAlumno;
	}

	
	@Override
	public TextBox getTexto() {
		// TODO Auto-generated method stub
		return texto;
	}



	/*public Widget asWidget() {
		return this;
	}*/
	
	@Override
	public void setPresenter(EditObservacionAlumnoActivity presenter) {
		this.presenter = presenter;
		
	}

/*	@Override
	public Driver getDriverEditor() {
		// TODO Auto-generated method stub
		return driverEditor;
	}
*/
	/*@Ignore
	@Override
	public EditObservacionAlumnoViewImpl getObjeto() {
		// TODO Auto-generated method stub
		return this;
	}*/

}
