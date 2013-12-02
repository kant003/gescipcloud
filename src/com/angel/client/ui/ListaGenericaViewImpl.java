package com.angel.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.angel.client.activity.ListaGenericaActivity;
import com.angel.client.activity.ListaObservacionesAlumnoActivity;
import com.angel.client.place.AlumnoPlace;
import com.angel.shared.AlumnoProxy;
import com.angel.shared.GenericProxy;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.web.bindery.requestfactory.shared.EntityProxy;

public abstract class ListaGenericaViewImpl<P extends GenericProxy> extends Composite implements ListaGenericaActivity.View<P>{

	private static ListaGenericaUiBinder uiBinder = GWT
			.create(ListaGenericaUiBinder.class);

	interface ListaGenericaUiBinder extends
			UiBinder<Widget, ListaGenericaViewImpl<?>> {
	}

	


	@UiField
	Button addButton;
	@UiField
	Button updateButton;
	@UiField
	Button deleteButton;
	@UiField
	TextBox filtroTexto;
	
	@UiField(provided = true)
	public DataGrid<P> dataGrid = new DataGrid<P>();
	
	@UiField
	SimplePager pager;
	
	public AsyncDataProvider<P> dataProviderAsync;
	
	
	 final MultiSelectionModel<P> selectionModel = new MultiSelectionModel<P>();
	 //public SingleSelectionModel<P> selectionModel = new SingleSelectionModel<P>();
	
	ListaGenericaActivity presenter;
	
	
	public ListaGenericaViewImpl() {
		System.out.println("contructor de view Lista generica");
		initWidget(uiBinder.createAndBindUi(this));
		preparaColumnas();
		dataGrid.setPageSize(5);
		
		// dataProvider.addDataDisplay(dataGrid);
		dataProviderAsync = new AsyncDataProvider<P>() {
			@Override
			protected void onRangeChanged(HasData<P> display) {
				if (presenter != null)
					presenter.onRangeChanged(display);
			}
		};

	//	presenter.preparaColumnas();

		dataProviderAsync.addDataDisplay(dataGrid);
		// pager.setPageSize(4);
		// paginador.setDisplay(dataGrid);
		// dataGrid.setVisibleRange(0, 5);
		dataGrid.setEmptyTableWidget(new HTML("No Data to Display"));
		pager.setDisplay(dataGrid);
		// pager.setPageSize(5);
		// refresco();

		dataGrid.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						if(selectionModel.getSelectedSet().size()==0){
							deleteButton.setEnabled(false);
						}else{
							deleteButton.setEnabled(true);
						}
						if(selectionModel.getSelectedSet().size() == 1){
							updateButton.setEnabled(true);
						}else{
							updateButton.setEnabled(false);
						}
						
						/*if (presenter != null) 
							presenter.onSelectionChange(event);*/
						
					}
					
				});
		dataGrid.setSelectionModel(selectionModel, DefaultSelectionEventManager.<P> createCheckboxManager());
		
		//dataProviderAsync.addDataDisplay(dataGrid);	
	
		deleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				presenter.deleteSelected();
				
			}
		});
		
		addButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				presenter.addNew();
				
			}
		});
		updateButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				presenter.updateSelected();
				
			}
		});
		
		filtroTexto.addKeyPressHandler(new KeyPressHandler() {
			
			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == 13) {
					presenter.refresca();
				}					
			}
		});
		
	}

	
	
	@Override
	public void setData(int count, int start, List<P> data) {
		dataProviderAsync.updateRowCount(
				count, true);
		dataProviderAsync.updateRowData(start,
				data);
		
	}

	@Override
	public int getClickedRow(ClickEvent event) {
		int selectedRow = -1;
	/*	HTMLTable.Cell cell = alumnoTable.getCellForEvent(event);

		if (cell != null) {
			// Suppress clicks if the user is actually selecting the
			// check box
			//
			if (cell.getCellIndex() > 0) {
				selectedRow = cell.getRowIndex();
			}
		}*/

		return selectedRow;

	}

	@Override
	public List<Integer> getSelectedRows() {
		
		List<Integer> selectedRows = new ArrayList<Integer>();
/*
		for (int i = 0; i < dataGrid.getv.getRowCount(); ++i) {
			CheckBox checkBox = (CheckBox) alumnoTable.getWidget(i, 0);
			if (checkBox.getValue()) {
				selectedRows.add(i);
			}
		}
*/
		return selectedRows;

	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getAddButton() {
		// TODO Auto-generated method stub
		return addButton;
	}


	@Override
	public HasClickHandlers getUpdateButton() {
		// TODO Auto-generated method stub
		return updateButton;
	}

	@Override
	public HasClickHandlers getDeleteButton() {
		// TODO Auto-generated method stub
		return deleteButton;
	}

	@Override
	public HasValue<String> getFiltroTexto() {
		// TODO Auto-generated method stub
		return filtroTexto;
	}


	@Override
	public HasClickHandlers getList() {
		return null;
	}

	

	@Override
	public  MultiSelectionModel<P> getSelectionModel(){
		 return selectionModel;
	 }
	
	@Override
	public void setPresenter(ListaGenericaActivity presenter) {
		this.presenter = presenter;
		
	}

	@Override
	public DataGrid<P> getDataGrid(){
		return dataGrid;
	}
	
	
	public abstract void preparaColumnas();
	/*{
		for(int i=0;i<dataGrid.getColumnCount();i++){
			dataGrid.removeColumn(i);
		}
		System.out.println("preparando columnas lista generica");
		
	
		CheckboxCell checkCell = new CheckboxCell();
		
		Column<P, Boolean> checkColumn = new Column<P, Boolean>(
				checkCell) {
			@Override
			public Boolean getValue(P object) {
				return selectionModel.isSelected(object);
				// return display.getSelectionModel().isSelected(object);
			}
			
			
		};
		dataGrid.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
		
		
		
			    
		TextColumn<P> idColumn = new TextColumn<P>() {
			@Override
			public String getValue(P object) {
				if (object == null)
					return "--";
				return String.valueOf(  object.getId()  );
			}
		};
		
		dataGrid.addColumn(idColumn, "ID");
		
		
		TextColumn<P> idAlunoColumn = new TextColumn<P>() {
			@Override
			public String getValue(P object) {
				if (object == null)
					return "--";
				return String.valueOf(  object.getIdAlumno()  );
			}
		};
		
		dataGrid.addColumn(idAlunoColumn, "IDALUMNO");
		
		
		
		TextColumn<P> textoColumn = new TextColumn<P>() {
			@Override
			public String getValue(P object) {
				if (object == null)
					return "--";
				return object.getTexto();
			}
		};
		
		dataGrid.addColumn(textoColumn, "Texto");
		
			
	

		ButtonCell botonCell = new ButtonCell();
		Column<P, String> botonColumn = new Column<P, String>(
				botonCell) {

			@Override
			public String getValue(P contact) {
				if(contact == null) return "vacio";
				return "Click " + contact.getId();
			}

		};

		botonColumn.setFieldUpdater(new FieldUpdater<P, String>() {
			@Override
			public void update(int index, P object, String value) {
				Window.alert("You clicked " + object.getId());
			}

		});
		dataGrid.addColumn(botonColumn, "BTO");

		
	}
*/
}
