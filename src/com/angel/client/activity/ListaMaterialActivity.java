package com.angel.client.activity;

import java.util.ArrayList;
import java.util.List;

import com.angel.client.ClientFactory;
import com.angel.client.place.SeleccionMatriculaPlace;
import com.angel.shared.MaterialProxy;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class ListaMaterialActivity extends ListaGenericaActivity<ListaGenericaActivity.View<MaterialProxy>,MaterialProxy>{
//	private String filtro;
	private String foreignkey;
	
	public ListaMaterialActivity(SeleccionMatriculaPlace place, ClientFactory clientFactory) {
		super(place,clientFactory,clientFactory.getMaterialView());
	
		System.out.println("contructor material:"+place.getIdMatricula());
		
		this.foreignkey = place.getIdMatricula();
	
	}

	
	public String mayStop() {
		//System.out.println("presentacion maystop");
		return null;
	}
	
	@Override
	public void deleteSelected() {
		//System.out.println("presentacion deleteSelected lista generica:"+display.getSelectionModel().getSelectedSet());
		List<Integer> a = new ArrayList<Integer>();
		for(MaterialProxy c : display.getSelectionModel().getSelectedSet()){
			//System.out.println("c:"+c.getId());
			a.add(c.getId());
		}
		//display.getSelectionModel().getSelectedSet()
			clientFactory.getRequestFactory()
			.materialRequest()
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
	
			//goTo(new EditObservacionAlumnoPlace(""));
	}


	@Override
	public void updateSelected() {
		if(display.getSelectionModel().getSelectedSet()!=null && !display.getSelectionModel().getSelectedSet().isEmpty()){
			MaterialProxy c = display.getSelectionModel().getSelectedSet().iterator().next();
			//goTo(new EditObservacionAlumnoPlace(String.valueOf(c.getId())));
		}
	}


	@Override
	public void onRangeChanged(HasData<MaterialProxy> m) {
		final Range range = m.getVisibleRange();
		final int start = range.getStart();
		final int end = start + range.getLength();

		clientFactory.getRequestFactory()
		.materialRequest()
		.findAll(Integer.parseInt(foreignkey),display.getFiltroTexto().getValue(),start,end)
		.fire(new Receiver<List<MaterialProxy>>() {

			@Override
			public void onSuccess(final List<MaterialProxy> response) {
				// contactDetails = response;

				List<String> data = new ArrayList<String>();
				if (response == null || response.size() == 0){
					//System.out.println("presentacion fetch: esta vacio");
					display.setData(0, 0, response);
				}else {
			

					
					
					clientFactory.getRequestFactory().materialRequest().count(Integer.parseInt(foreignkey),display.getFiltroTexto().getValue())
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
