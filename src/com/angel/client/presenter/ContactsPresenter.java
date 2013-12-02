package com.angel.client.presenter;


	/*import com.google.gwt.sample.contacts.client.ContactsServiceAsync;
	import com.google.gwt.sample.contacts.client.event.AddContactEvent;
	import com.google.gwt.sample.contacts.client.event.EditContactEvent;
	import com.google.gwt.sample.contacts.shared.ContactDetails;
*/
	import java.util.ArrayList;
import java.util.List;

import com.angel.client.event.AddContactEvent;
import com.angel.client.event.EditContactEvent;
import com.angel.shared.ContactDetailsProxy;
import com.angel.shared.ContactProxy;
import com.angel.shared.GescipRequestFactory;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

	public class ContactsPresenter implements Presenter { 

	  private List<ContactDetailsProxy> contactDetails;

	  public interface Display {
	    HasClickHandlers getAddButton();
	    HasClickHandlers getDeleteButton();
	    HasClickHandlers getList();
	    void setData(List<String> data);
	    int getClickedRow(ClickEvent event);
	    List<Integer> getSelectedRows();
	    Widget asWidget();
	  }
	  
	//  private final ContactsServiceAsync rpcService;
	  private final GescipRequestFactory rf;
	  private final HandlerManager eventBus;
	  private final Display display;
	  
	  public ContactsPresenter(GescipRequestFactory rf,/*ContactsServiceAsync rpcService, */HandlerManager eventBus, Display view) {
	   // this.rpcService = rpcService;
	    this.rf = rf;
		  this.eventBus = eventBus;
	    this.display = view;
	  }
	  
	  public void bind() {
	    display.getAddButton().addClickHandler(new ClickHandler() {   
	      public void onClick(ClickEvent event) {
	        eventBus.fireEvent(new AddContactEvent());
	      }
	    });

	    display.getDeleteButton().addClickHandler(new ClickHandler() {   
	      public void onClick(ClickEvent event) {
	        deleteSelectedContacts();
	      }
	    });
	    
	    display.getList().addClickHandler(new ClickHandler() {
	      public void onClick(ClickEvent event) {
	        int selectedRow = display.getClickedRow(event);
	        
	     /*   if (selectedRow >= 0) {
	        	String id = contactDetails.get(selectedRow).getId();
	          eventBus.fireEvent(new EditContactEvent(id));
	        }*/
	      }
	    });
	  }
	  
	  public void go(final HasWidgets container) {
	    bind();
	    container.clear();
	    container.add(display.asWidget());
	    fetchContactDetails();
	  }

	  public void sortContactDetails() {
	    
	    // Yes, we could use a more optimized method of sorting, but the 
	    //  point is to create a test case that helps illustrate the higher
	    //  level concepts used when creating MVP-based applications. 
	    //
	    for (int i = 0; i < contactDetails.size(); ++i) {
	      for (int j = 0; j < contactDetails.size() - 1; ++j) {
	        if (contactDetails.get(j).getDisplayName().compareToIgnoreCase(contactDetails.get(j + 1).getDisplayName()) >= 0) {
	        	ContactDetailsProxy tmp = contactDetails.get(j);
	          contactDetails.set(j, contactDetails.get(j + 1));
	          contactDetails.set(j + 1, tmp);
	        }
	      }
	    }
	  }

	  public void setContactDetails(List<ContactDetailsProxy> contactDetails) {
	    this.contactDetails = contactDetails;
	  }
	  
	  public ContactDetailsProxy getContactDetail(int index) {
	    return contactDetails.get(index);
	  }
	  
	  private void fetchContactDetails() {
		
				rf.contactDetailsRequest()
						.findAllContactsDetails()
						.fire(new Receiver<List<ContactDetailsProxy>>() {

							@Override
							public void onSuccess(List<ContactDetailsProxy> response) {
								contactDetails = response;
								sortContactDetails();
								List<String> data = new ArrayList<String>();
								  for (int i = 0; i < response.size(); ++i) {
							            data.add(contactDetails.get(i).getDisplayName());
							      }
							          
							      display.setData(data);
							}
							@Override
							public void onFailure(ServerFailure error) {
								// TODO Auto-generated method stub
								super.onFailure(error);
								  Window.alert("Error fetching contact details");
							}
						});
			
		/*  requestFactory.employeeRequest().findEmployee(employeeId).fire(
				    new Receiver<EmployeeProxy>() {
				      @Override
				      public void onSuccess(EmployeeProxy employee) {
				      ...
				      }
				    });*/
		  
	    /*rpcService.getContactDetails(new AsyncCallback<ArrayList<ContactDetails>>() {
	      public void onSuccess(ArrayList<ContactDetails> result) {
	          contactDetails = result;
	          sortContactDetails();
	          List<String> data = new ArrayList<String>();

	          for (int i = 0; i < result.size(); ++i) {
	            data.add(contactDetails.get(i).getDisplayName());
	          }
	          
	          display.setData(data);
	      }
	      
	      public void onFailure(Throwable caught) {
	        Window.alert("Error fetching contact details");
	      }
	    });*/
	  }

	  private void deleteSelectedContacts() {
	    List<Integer> selectedRows = display.getSelectedRows();
	    ArrayList<String> ids = new ArrayList<String>();
	    
	  /*  for (int i = 0; i < selectedRows.size(); ++i) {
	      ids.add(contactDetails.get(selectedRows.get(i)).getId());
	      
	      
	      
	      rf.contactRequest()
			.findContact(contactDetails.get(selectedRows.get(i)).getId())
			.fire(new Receiver<ContactProxy>() {

				@Override
				public void onSuccess(ContactProxy response) {
					
					  Request<Void> updateReq = rf.contactRequest().remove().using(response);
				       
				  
				}
				@Override
				public void onFailure(ServerFailure error) {
					// TODO Auto-generated method stub
					super.onFailure(error);
					  Window.alert("Error borrando  contact ");
				}
			});
	      
	   
	      
	    }*/
	  
	 
	  }
	}
