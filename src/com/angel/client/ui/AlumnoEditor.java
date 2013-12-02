package com.angel.client.ui;

import com.angel.shared.ContactProxy;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.ui.client.ValueBoxEditorDecorator;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TextBox;

public class AlumnoEditor implements Editor<ContactProxy>{
	@UiField
	TextBox id;
	@UiField
	ValueBoxEditorDecorator<String> firstName;
	@UiField
	TextBox lastName;
	@UiField
	TextBox emailAddress;
}
