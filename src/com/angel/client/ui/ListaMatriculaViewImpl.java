package com.angel.client.ui;

import com.angel.shared.MatriculaProxy;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;

public class ListaMatriculaViewImpl extends ListaGenericaViewImpl<MatriculaProxy> {

	
	public void preparaColumnas() {
		for(int i=0;i<dataGrid.getColumnCount();i++){
			dataGrid.removeColumn(i);
		}
		System.out.println("preparando columnas lista generica");
		
	
		CheckboxCell checkCell = new CheckboxCell();
		
		Column<MatriculaProxy, Boolean> checkColumn = new Column<MatriculaProxy, Boolean>(
				checkCell) {
			@Override
			public Boolean getValue(MatriculaProxy object) {
				return selectionModel.isSelected(object);
				// return display.getSelectionModel().isSelected(object);
			}
			
			
		};
		dataGrid.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
		
		
		
			    
		TextColumn<MatriculaProxy> idColumn = new TextColumn<MatriculaProxy>() {
			@Override
			public String getValue(MatriculaProxy object) {
				if (object == null)
					return "--";
				return String.valueOf(  object.getId()  );
			}
		};
		
		dataGrid.addColumn(idColumn, "ID matri");
		
		
		TextColumn<MatriculaProxy> idAlunoColumn = new TextColumn<MatriculaProxy>() {
			@Override
			public String getValue(MatriculaProxy object) {
				if (object == null)
					return "--";
				return String.valueOf(  object.getIdAlumno()  );
			}
		};
		
		dataGrid.addColumn(idAlunoColumn, "IDALUMNO");
		
		
		
		TextColumn<MatriculaProxy> textoColumn = new TextColumn<MatriculaProxy>() {
			@Override
			public String getValue(MatriculaProxy object) {
				if (object == null)
					return "--";
				return object.getTexto();
			}
		};
		
		dataGrid.addColumn(textoColumn, "Texto");
		
			
	

		ButtonCell botonCell = new ButtonCell();
		Column<MatriculaProxy, String> botonColumn = new Column<MatriculaProxy, String>(
				botonCell) {

			@Override
			public String getValue(MatriculaProxy contact) {
				if(contact == null) return "vacio";
				return "Click " + contact.getId();
			}

		};

		botonColumn.setFieldUpdater(new FieldUpdater<MatriculaProxy, String>() {
			@Override
			public void update(int index, MatriculaProxy object, String value) {
				Window.alert("You clicked " + object.getId());
				presenter.ver(object.getId());
			}

		});
		dataGrid.addColumn(botonColumn, "BTO");

		
	}
}
