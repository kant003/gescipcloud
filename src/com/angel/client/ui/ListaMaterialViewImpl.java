package com.angel.client.ui;

import com.angel.shared.MaterialProxy;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;

public class ListaMaterialViewImpl extends ListaGenericaViewImpl<MaterialProxy> {

	
	public void preparaColumnas() {
		for(int i=0;i<dataGrid.getColumnCount();i++){
			dataGrid.removeColumn(i);
		}
		System.out.println("preparando columnas lista generica");
		
	
		CheckboxCell checkCell = new CheckboxCell();
		
		Column<MaterialProxy, Boolean> checkColumn = new Column<MaterialProxy, Boolean>(
				checkCell) {
			@Override
			public Boolean getValue(MaterialProxy object) {
				return selectionModel.isSelected(object);
				// return display.getSelectionModel().isSelected(object);
			}
			
			
		};
		dataGrid.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
		
		
		
			    
		TextColumn<MaterialProxy> idColumn = new TextColumn<MaterialProxy>() {
			@Override
			public String getValue(MaterialProxy object) {
				if (object == null)
					return "--";
				return String.valueOf(  object.getId()  );
			}
		};
		
		dataGrid.addColumn(idColumn, "ID material");
		
		
		TextColumn<MaterialProxy> idAlunoColumn = new TextColumn<MaterialProxy>() {
			@Override
			public String getValue(MaterialProxy object) {
				if (object == null)
					return "--";
				return String.valueOf(  object.getIdMatricula()  );
			}
		};
		
		dataGrid.addColumn(idAlunoColumn, "IDMatricula");
		
		
		
		TextColumn<MaterialProxy> textoColumn = new TextColumn<MaterialProxy>() {
			@Override
			public String getValue(MaterialProxy object) {
				if (object == null)
					return "--";
				return object.getTexto();
			}
		};
		
		dataGrid.addColumn(textoColumn, "Texto");
		
			
	

		ButtonCell botonCell = new ButtonCell();
		Column<MaterialProxy, String> botonColumn = new Column<MaterialProxy, String>(
				botonCell) {

			@Override
			public String getValue(MaterialProxy contact) {
				if(contact == null) return "vacio";
				return "Click " + contact.getId();
			}

		};

		botonColumn.setFieldUpdater(new FieldUpdater<MaterialProxy, String>() {
			@Override
			public void update(int index, MaterialProxy object, String value) {
				Window.alert("You clicked " + object.getId());
				
			}

		});
		dataGrid.addColumn(botonColumn, "BTO");

		
	}
}
