package com.angel.client.ui;

import com.angel.client.presenter.EditContactPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class EditContactViewImpl extends Composite implements
		EditContactPresenter.Display {

	private static EditContactViewImplUiBinder uiBinder = GWT
			.create(EditContactViewImplUiBinder.class);

	interface EditContactViewImplUiBinder extends
			UiBinder<Widget, EditContactViewImpl> {
	}

	public EditContactViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button saveButton;
	@UiField
	Button cancelButton;
	@UiField
	TextBox firstName;
	@UiField
	TextBox lastName;
	@UiField
	TextBox emailAddress;

	public EditContactViewImpl(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));

	}

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
	public HasValue<String> getFirstName() {
		// TODO Auto-generated method stub
		return firstName;
	}

	@Override
	public HasValue<String> getLastName() {
		// TODO Auto-generated method stub
		return lastName;
	}

	@Override
	public HasValue<String> getEmailAddress() {
		// TODO Auto-generated method stub
		return emailAddress;
	}

	public Widget asWidget() {
		return this;
	}
}
