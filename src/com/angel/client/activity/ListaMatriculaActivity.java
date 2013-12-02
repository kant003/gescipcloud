package com.angel.client.activity;

import java.util.ArrayList;
import java.util.List;

import com.angel.client.ClientFactory;
import com.angel.client.place.EditMatriculaPlace;
import com.angel.client.place.SeleccionAlumnoPlace;
import com.angel.client.place.SeleccionMatriculaPlace;
import com.angel.shared.MatriculaProxy;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class ListaMatriculaActivity extends ListaGenericaActivity<ListaGenericaActivity.View<MatriculaProxy>,MatriculaProxy>{
//	private String filtro;
	private String foreignkey;
	
	public ListaMatriculaActivity(SeleccionAlumnoPlace place, ClientFactory clientFactory) {
		super(place,clientFactory,clientFactory.getMatriculaView());
	//	super.display = clientFactory.getMatriculaView();
//		super(place, clientFactory);
	/*	String prefijo = "observacionesAlumno-";
		if(place.getFiltro().startsWith(prefijo)){
			this.filtro = place.getFiltro().substring(prefijo.length());	
		}*/
		System.out.println("contructor matricula:"+place.getIdAlumno());
		
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
		for(MatriculaProxy c : display.getSelectionModel().getSelectedSet()){
			//System.out.println("c:"+c.getId());
			a.add(c.getId());
		}
		//display.getSelectionModel().getSelectedSet()
			clientFactory.getRequestFactory()
			.matriculaRequest()
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
	
			goTo(new EditMatriculaPlace(""));
	}


	@Override
	public void updateSelected() {
		if(display.getSelectionModel().getSelectedSet()!=null && !display.getSelectionModel().getSelectedSet().isEmpty()){
			MatriculaProxy c = display.getSelectionModel().getSelectedSet().iterator().next();
			goTo(new EditMatriculaPlace(String.valueOf(c.getId())));
		}
	}


	@Override
	public void onRangeChanged(HasData<MatriculaProxy> m) {
		final Range range = m.getVisibleRange();
		final int start = range.getStart();
		final int end = start + range.getLength();

		clientFactory.getRequestFactory()
		.matriculaRequest()
		.findAll(Integer.parseInt(foreignkey),display.getFiltroTexto().getValue(),start,end)
		.fire(new Receiver<List<MatriculaProxy>>() {

			@Override
			public void onSuccess(final List<MatriculaProxy> response) {
				// contactDetails = response;

				List<String> data = new ArrayList<String>();
				if (response == null || response.size() == 0){
					//System.out.println("presentacion fetch: esta vacio");
					display.setData(0, 0, response);
				}else {
			

					
					
					clientFactory.getRequestFactory().matriculaRequest().count(Integer.parseInt(foreignkey),display.getFiltroTexto().getValue())
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
	
	public void ver(int idMatricula){
		String ids = ""+idMatricula;
		clientFactory.getPlaceController().goTo(new SeleccionMatriculaPlace(foreignkey,ids));
	}
	
}
