package com.angel.client.activity;

import com.angel.client.ClientFactory;
import com.angel.client.place.EditMatriculaPlace;
import com.angel.client.place.EditObservacionAlumnoPlace;
import com.angel.shared.MatriculaProxy;


public class EditMatriculaActivity extends EditGenericaActivity<EditGenericaActivity.View<MatriculaProxy>, MatriculaProxy>{

	public EditMatriculaActivity(
			EditMatriculaPlace place,
			ClientFactory clientFactory) {
		super(place, clientFactory, clientFactory.getEditMatriculaView());
		// TODO Auto-generated constructor stub
	}

	
}
