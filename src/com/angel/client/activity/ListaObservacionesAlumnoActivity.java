package com.angel.client.activity;

import java.util.ArrayList;
import java.util.List;

import com.angel.client.ClientFactory;
import com.angel.client.place.EditObservacionAlumnoPlace;
import com.angel.client.place.SeleccionAlumnoPlace;
import com.angel.client.place.SeleccionMatriculaPlace;
import com.angel.shared.ObservacionAlumnoProxy;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class ListaObservacionesAlumnoActivity extends ListaGenericaActivity<ListaGenericaActivity.View<ObservacionAlumnoProxy>,ObservacionAlumnoProxy>{
//	private String filtro;
	private String foreignkey;
	
	public ListaObservacionesAlumnoActivity(SeleccionAlumnoPlace place, ClientFactory clientFactory) {
		super(place,clientFactory,clientFactory.getObservacionesAlumnoView());
	
		System.out.println("contructor observ alum:"+place.getIdAlumno());
		
		this.foreignkey = place.getIdAlumno();
	
		
	}

	
	public String mayStop() {
		//System.out.println("presentacion maystop");
		return null;
	}
	
	@Override
	public void deleteSelected() {
		//System.out.println("presentacion deleteSelected lista generica:"+display.getSelectionModel().getSelectedSet());
		List<Integer> a = new ArrayList<Integer>();
		for(ObservacionAlumnoProxy c : display.getSelectionModel().getSelectedSet()){
			//System.out.println("c:"+c.getId());
			a.add(c.getId());
		}
		//display.getSelectionModel().getSelectedSet()
			clientFactory.getRequestFactory()
			.observacionAlumnoRequest()
			.remove( a )
			.fire(new Receiver<Integer>() {
				@Override
				public void onSuccess(Integer response) {
					Window.alert("ok borrados:"+response);
					
					onRangeChanged(display.getDataGrid());
					display.getDataGrid().redraw();
				}
			});	
		
	}


	@Override
	public void addNew() {
	
			goTo(new EditObservacionAlumnoPlace(""));
	}


	@Override
	public void updateSelected() {
		if(display.getSelectionModel().getSelectedSet()!=null && !display.getSelectionModel().getSelectedSet().isEmpty()){
			ObservacionAlumnoProxy c = display.getSelectionModel().getSelectedSet().iterator().next();
			goTo(new EditObservacionAlumnoPlace(String.valueOf(c.getId())));
		}
	}


	@Override
	public void onRangeChanged(HasData<ObservacionAlumnoProxy> m) {
		final Range range = m.getVisibleRange();
		final int start = range.getStart();
		final int end = start + range.getLength();

		clientFactory.getRequestFactory()
		.observacionAlumnoRequest()
		.findAll(Integer.parseInt(foreignkey),display.getFiltroTexto().getValue(),start,end)
		.fire(new Receiver<List<ObservacionAlumnoProxy>>() {

			@Override
			public void onSuccess(final List<ObservacionAlumnoProxy> response) {
				// contactDetails = response;

				List<String> data = new ArrayList<String>();
				if (response == null || response.size() == 0){
					//System.out.println("presentacion fetch: esta vacio");
					display.setData(0, 0, response);
				}else {
			

					
					
					clientFactory.getRequestFactory().observacionAlumnoRequest().count(Integer.parseInt(foreignkey),display.getFiltroTexto().getValue())
					.fire(new Receiver<Integer>() {

						@Override
						public void onSuccess(Integer count) {
							// contactDetails = response;

							display.setData(count, start, response);
							
						}

						@Override
						public void onFailure(ServerFailure error) {
							
							Window.alert("Error count contact details:"
									+ error.getMessage());
						}
					});
					
					
				}
			}

			@Override
			public void onFailure(ServerFailure error) {
				// TODO Auto-generated method stub
				// super.onFailure(error);
				Window.alert("Error fetching contact details:"
						+ error.getMessage());
			}
		});
		
	}
	
	@Override
	public void ver(int idMatricula){
		
	}
}
