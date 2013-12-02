package com.angel.client.ui;

import com.angel.shared.ObservacionAlumnoProxy;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;

public class ListaMatriculaViewImpl extends ListaGenericaViewImpl<ObservacionAlumnoProxy> {

	
	public void preparaColumnas() {
		for(int i=0;i<dataGrid.getColumnCount();i++){
			dataGrid.removeColumn(i);
		}
		System.out.println("preparando columnas lista generica");
		
	
		CheckboxCell checkCell = new CheckboxCell();
		
		Column<ObservacionAlumnoProxy, Boolean> checkColumn = new Column<ObservacionAlumnoProxy, Boolean>(
				checkCell) {
			@Override
			public Boolean getValue(ObservacionAlumnoProxy object) {
				return selectionModel.isSelected(object);
				// return display.getSelectionModel().isSelected(object);
			}
			
			
		};
		dataGrid.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
		
		
		
			    
		TextColumn<ObservacionAlumnoProxy> idColumn = new TextColumn<ObservacionAlumnoProxy>() {
			@Override
			public String getValue(ObservacionAlumnoProxy object) {
				if (object == null)
					return "--";
				return String.valueOf(  object.getId()  );
			}
		};
		
		dataGrid.addColumn(idColumn, "ID");
		
		
		TextColumn<ObservacionAlumnoProxy> idAlunoColumn = new TextColumn<ObservacionAlumnoProxy>() {
			@Override
			public String getValue(ObservacionAlumnoProxy object) {
				if (object == null)
					return "--";
				return String.valueOf(  object.getIdAlumno()  );
			}
		};
		
		dataGrid.addColumn(idAlunoColumn, "IDALUMNO");
		
		
		
		TextColumn<ObservacionAlumnoProxy> textoColumn = new TextColumn<ObservacionAlumnoProxy>() {
			@Override
			public String getValue(ObservacionAlumnoProxy object) {
				if (object == null)
					return "--";
				return object.getTexto();
			}
		};
		
		dataGrid.addColumn(textoColumn, "Texto");
		
			
	

		ButtonCell botonCell = new ButtonCell();
		Column<ObservacionAlumnoProxy, String> botonColumn = new Column<ObservacionAlumnoProxy, String>(
				botonCell) {

			@Override
			public String getValue(ObservacionAlumnoProxy contact) {
				if(contact == null) return "vacio";
				return "Click " + contact.getId();
			}

		};

		botonColumn.setFieldUpdater(new FieldUpdater<ObservacionAlumnoProxy, String>() {
			@Override
			public void update(int index, ObservacionAlumnoProxy object, String value) {
				Window.alert("You clicked " + object.getId());
				
			}

		});
		dataGrid.addColumn(botonColumn, "BTO");

		
	}
}
