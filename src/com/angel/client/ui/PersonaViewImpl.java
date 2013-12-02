package com.angel.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;

public class PersonaViewImpl extends Composite implements PersonaView {

	private static PersonaViewUiBinder uiBinder = GWT
			.create(PersonaViewUiBinder.class);

	interface PersonaViewUiBinder extends UiBinder<Widget, PersonaViewImpl> {
	}

	public PersonaViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button button;
	@UiField Label nombre;

	public PersonaViewImpl(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		button.setText(firstName);
	}

	@UiHandler("button")
	void onClick(ClickEvent e) {
		Window.alert("Hello!");
	}

	public void setText(String text) {
		
	}

	public String getText() {
		return button.getText();
	}

	@Override
	public void setNombre(String n) {
		nombre.setText(n);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		// TODO Auto-generated method stub
		
	}
	

}
