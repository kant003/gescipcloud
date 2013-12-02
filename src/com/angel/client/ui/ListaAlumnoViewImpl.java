package com.angel.client.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.james.mime4j.message.TextBody;

import com.angel.client.activity.ListaAlumnoActivity;
import com.angel.client.activity.ListaAlumnoActivity;
import com.angel.client.place.AlumnoPlace;
import com.angel.client.ui.ListaAlumnoViewImpl.ListaAlumnoUiBinder;
import com.angel.shared.AlumnoProxy;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;


	public class ListaAlumnoViewImpl extends Composite implements ListaAlumnoActivity.View{

		private static ListaAlumnoUiBinder uiBinder = GWT
				.create(ListaAlumnoUiBinder.class);

		interface ListaAlumnoUiBinder extends
				UiBinder<Widget, ListaAlumnoViewImpl> {
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
		public DataGrid<AlumnoProxy> dataGrid = new DataGrid<AlumnoProxy>();
		
		@UiField
		SimplePager pager;
		
		public AsyncDataProvider<AlumnoProxy> dataProviderAsync;
		
		
		 final MultiSelectionModel<AlumnoProxy> selectionModel = new MultiSelectionModel<AlumnoProxy>();
		 //public SingleSelectionModel<AlumnoProxy> selectionModel = new SingleSelectionModel<AlumnoProxy>();
		
		ListaAlumnoActivity presenter;
		
		
		public ListaAlumnoViewImpl() {
			System.out.println("contructor de view Lista alumno");
			initWidget(uiBinder.createAndBindUi(this));
			preparaColumnas();
			dataGrid.setPageSize(5);
			
			// dataProvider.addDataDisplay(dataGrid);
			dataProviderAsync = new AsyncDataProvider<AlumnoProxy>() {
				@Override
				protected void onRangeChanged(HasData<AlumnoProxy> display) {
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
			
			dataGrid.setSelectionModel(selectionModel, DefaultSelectionEventManager.<AlumnoProxy> createCheckboxManager());
			
			
						
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
						//presenter.goTo(new AlumnoPlace("alumno-"+filtroTexto.getText()));ç
						presenter.refresca();
					}					
				}
			});
			

		}

		
		
		@Override
		public void setData(int count, int start, List<AlumnoProxy> data) {
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
		public HasClickHandlers getList() {
			return null;
		}

		

		@Override
		public MultiSelectionModel<AlumnoProxy> getSelectionModel(){
			 return selectionModel;
		 }
		
		@Override
		public void setPresenter(ListaAlumnoActivity presenter) {
			this.presenter = presenter;
			
		}

		@Override
		public DataGrid<AlumnoProxy> getDataGrid(){
			return dataGrid;
		}
		
		
		public void preparaColumnas() {
			for(int i=0;i<dataGrid.getColumnCount();i++){
				dataGrid.removeColumn(i);
			}
			System.out.println("preparando columnas lista alunmo");
			
		
			CheckboxCell checkCell = new CheckboxCell();
			
			Column<AlumnoProxy, Boolean> checkColumn = new Column<AlumnoProxy, Boolean>(
					checkCell) {
				@Override
				public Boolean getValue(AlumnoProxy object) {
					return selectionModel.isSelected(object);
					// return display.getSelectionModel().isSelected(object);
				}
				
				
			};
			dataGrid.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
			
			
			
				    
			TextColumn<AlumnoProxy> idColumn = new TextColumn<AlumnoProxy>() {
				@Override
				public String getValue(AlumnoProxy object) {
					if (object == null)
						return "--";
					return String.valueOf(  object.getId()  );
				}
			};
			
			dataGrid.addColumn(idColumn, "ID");
			
			
			
			
			
			TextColumn<AlumnoProxy> nombreColumn = new TextColumn<AlumnoProxy>() {
				@Override
				public String getValue(AlumnoProxy object) {
					if (object == null)
						return "--";
					return object.getNombre();
				}
			};
			
			dataGrid.addColumn(nombreColumn, "Nombre");
			
			TextColumn<AlumnoProxy> apellidosColumn = new TextColumn<AlumnoProxy>() {
				@Override
				public String getValue(AlumnoProxy object) {
					if (object == null)
						return "--";
					return object.getApellidos();
				}
			};
			
			dataGrid.addColumn(apellidosColumn, "Apellidos");
			
			TextColumn<AlumnoProxy> nifColumn = new TextColumn<AlumnoProxy>() {
				@Override
				public String getValue(AlumnoProxy object) {
					if (object == null)
						return "--";
					return object.getNif();
				}
			};
			
			dataGrid.addColumn(nifColumn, "NIF");
		

			ButtonCell botonCell = new ButtonCell();
			Column<AlumnoProxy, String> botonColumn = new Column<AlumnoProxy, String>(
					botonCell) {

				@Override
				public String getValue(AlumnoProxy contact) {
					if(contact == null) return "vacio";
					return "Click " + contact.getId();
				}

			};

			botonColumn.setFieldUpdater(new FieldUpdater<AlumnoProxy, String>() {
				@Override
				public void update(int index, AlumnoProxy object, String value) {
					Window.alert("You clicked " + object.getId());
					presenter.ver(object.getId());
				}

			});
			dataGrid.addColumn(botonColumn, "BTO");

			
		}



		@Override
		public HasValue<String> getFiltroTexto() {
			// TODO Auto-generated method stub
			return filtroTexto;
		}

	}
