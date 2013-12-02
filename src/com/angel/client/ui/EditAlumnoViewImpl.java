package com.angel.client.ui;

import com.angel.client.activity.EditAlumnoActivity;
import com.angel.shared.AlumnoProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.ui.client.ValueBoxEditorDecorator;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.LongBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;

public class EditAlumnoViewImpl extends Composite implements
		EditAlumnoActivity.View, Editor<AlumnoProxy> {

	private static EditAlumnoViewImplUiBinder uiBinder = GWT
			.create(EditAlumnoViewImplUiBinder.class);

	interface EditAlumnoViewImplUiBinder extends
			UiBinder<Widget, EditAlumnoViewImpl> {
	}

	public EditAlumnoViewImpl() {
		System.out.println("contructor de view edit");
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
	TextBox nombre;
	@UiField
	TextBox apellidos;
	@UiField
	TextBox nif;

	EditAlumnoActivity presenter;
	
/*	public EditAlumnoViewImpl(String firstName) {
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
	public TextBox getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}

	@Override
	public TextBox getApellidos() {
		// TODO Auto-generated method stub
		return apellidos;
	}

	@Override
	public TextBox getNif() {
		// TODO Auto-generated method stub
		return nif;
	}

	/*public Widget asWidget() {
		return this;
	}*/
	
	@Override
	public void setPresenter(EditAlumnoActivity presenter) {
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
	public EditAlumnoViewImpl getObjeto() {
		// TODO Auto-generated method stub
		return this;
	}*/

}
