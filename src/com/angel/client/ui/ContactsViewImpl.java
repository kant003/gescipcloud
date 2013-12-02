package com.angel.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.angel.client.presenter.ContactsPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiHandler;

public class ContactsViewImpl extends Composite implements
		ContactsPresenter.Display {

	private static ContactsViewImplUiBinder uiBinder = GWT
			.create(ContactsViewImplUiBinder.class);

	interface ContactsViewImplUiBinder extends
			UiBinder<Widget, ContactsViewImpl> {
	}

	public ContactsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button addButton;
	@UiField
	Button deleteButton;
	@UiField
	FlexTable contactsTable;

	public ContactsViewImpl(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));

	}

	/* IMPLEMENTACIÓN DE ContactsPresenter.Display */

	@Override
	public HasClickHandlers getAddButton() {
		// TODO Auto-generated method stub
		return addButton;
	}

	@Override
	public HasClickHandlers getDeleteButton() {
		// TODO Auto-generated method stub
		return deleteButton;
	}

	@Override
	public HasClickHandlers getList() {
		// TODO Auto-generated method stub
		return contactsTable;
	}

	@Override
	public void setData(List<String> data) {
		contactsTable.removeAllRows();

		for (int i = 0; i < data.size(); ++i) {
			contactsTable.setWidget(i, 0, new CheckBox());
			contactsTable.setText(i, 1, data.get(i));
		}

	}

	@Override
	public int getClickedRow(ClickEvent event) {
		int selectedRow = -1;
		HTMLTable.Cell cell = contactsTable.getCellForEvent(event);

		if (cell != null) {
			// Suppress clicks if the user is actually selecting the
			// check box
			//
			if (cell.getCellIndex() > 0) {
				selectedRow = cell.getRowIndex();
			}
		}

		return selectedRow;

	}

	@Override
	public List<Integer> getSelectedRows() {
		List<Integer> selectedRows = new ArrayList<Integer>();

		for (int i = 0; i < contactsTable.getRowCount(); ++i) {
			CheckBox checkBox = (CheckBox) contactsTable.getWidget(i, 0);
			if (checkBox.getValue()) {
				selectedRows.add(i);
			}
		}

		return selectedRows;

	}

	public Widget asWidget() {
		return this;
	}

	
}
