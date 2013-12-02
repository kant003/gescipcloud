package com.angel.client.presenter;

import java.util.List;

import com.angel.client.ui.PersonaView.Presenter;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

public interface APresenter {
	
	void fetch();
	void deleteSelected();
	void addNew();
	
	 public interface Display /*extends HasValue<List<String>>*/ {
		    HasClickHandlers getAddButton();
		    HasClickHandlers getDeleteButton();
		    HasClickHandlers getList();
		    void setData(List<String> data);
		    int getClickedRow(ClickEvent event);
		    List<Integer> getSelectedRows();
		    Widget asWidget();
		    void setPresenter(APresenter presenter);
		  }
}
