package com.angel.client.ui;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface PersonaView extends IsWidget {
	void setNombre(String nombre);

	void setPresenter(Presenter presenter);

	public interface Presenter {
		void goTo(Place place);
	}
}
