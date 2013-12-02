package com.angel.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.angel.client.activity.ListaObservacionesAlumnoActivity;
import com.angel.shared.ObservacionAlumnoProxy;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
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
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;

public class BackupListaObservacionesAlumnoViewImpl{
	
}
/*

public class BackupListaObservacionesAlumnoViewImpl extends Composite implements ListaObservacionesAlumnoActivity.View{

	private static ListaObservacionesAlumnoUiBinder uiBinder = GWT
			.create(ListaObservacionesAlumnoUiBinder.class);

	interface ListaObservacionesAlumnoUiBinder extends
			UiBinder<Widget, ListaObservacionesAlumnoViewImpl> {
	}

	


	@UiField
	Button addButton;
	@UiField
	Button updateButton;
	@UiField
	Button deleteButton;

	@UiField(provided = true)
	public DataGrid<ObservacionAlumnoProxy> dataGrid = new DataGrid<ObservacionAlumnoProxy>();
	
	@UiField
	SimplePager pager;
	
	public AsyncDataProvider<ObservacionAlumnoProxy> dataProviderAsync;
	
	
	 final MultiSelectionModel<ObservacionAlumnoProxy> selectionModel = new MultiSelectionModel<ObservacionAlumnoProxy>();
	 //public SingleSelectionModel<ObservacionAlumnoProxy> selectionModel = new SingleSelectionModel<ObservacionAlumnoProxy>();
	
	ListaObservacionesAlumnoActivity presenter;
	
	
	public BackupListaObservacionesAlumnoViewImpl() {
		System.out.println("contructor de view Lista observ");
		initWidget(uiBinder.createAndBindUi(this));
		preparaColumnas();
		dataGrid.setPageSize(5);
		
		// dataProvider.addDataDisplay(dataGrid);
		dataProviderAsync = new AsyncDataProvider<ObservacionAlumnoProxy>() {
			@Override
			protected void onRangeChanged(HasData<ObservacionAlumnoProxy> display) {
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
						
					
						
					}
					
				});
		dataGrid.setSelectionModel(selectionModel, DefaultSelectionEventManager.<ObservacionAlumnoProxy> createCheckboxManager());
		
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
	}

	
	
	@Override
	public void setData(int count, int start, List<ObservacionAlumnoProxy> data) {
		dataProviderAsync.updateRowCount(
				count, true);
		dataProviderAsync.updateRowData(start,
				data);
		
	}

	@Override
	public int getClickedRow(ClickEvent event) {
		int selectedRow = -1;
	

		return selectedRow;

	}

	@Override
	public List<Integer> getSelectedRows() {
		
		List<Integer> selectedRows = new ArrayList<Integer>();

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
	public HasClickHandlers getList() {
		return null;
	}

	

	@Override
	public MultiSelectionModel<ObservacionAlumnoProxy> getSelectionModel(){
		 return selectionModel;
	 }
	
	@Override
	public void setPresenter(ListaObservacionesAlumnoActivity presenter) {
		this.presenter = presenter;
		
	}

	@Override
	public DataGrid<ObservacionAlumnoProxy> getDataGrid(){
		return dataGrid;
	}
	
	
	public void preparaColumnas() {
		for(int i=0;i<dataGrid.getColumnCount();i++){
			dataGrid.removeColumn(i);
		}
		System.out.println("preparando columnas lista observ");
		
	
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

}*/
