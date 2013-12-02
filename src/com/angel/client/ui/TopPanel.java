package com.angel.client.ui;

import com.angel.client.ClientFactory;
import com.angel.client.place.AlumnoPlace;
import com.angel.shared.ContactRequest;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class TopPanel extends Composite {
        private static TopPanelUiBinder uiBinder = GWT.create(TopPanelUiBinder.class);
        @UiField Button butViewContacts;
        @UiField Button butEditContact;
        @UiField Button butAlum;
        @UiField Button butObser;
        private ClientFactory clientFactory;

        interface TopPanelUiBinder extends UiBinder<Widget, TopPanel> { }

        public TopPanel() {
                initWidget(uiBinder.createAndBindUi(this));
        }
        
        public void setClientFactory(ClientFactory cf) {
                clientFactory = cf;
        }
        
        @UiHandler("butViewContacts")
        void onButViewContactsClick(ClickEvent event) {
                if(clientFactory != null) {
                     //   clientFactory.getPlaceController().goTo(new ListaAlumnoPlace("2"));
                }
        }
        
        @UiHandler("butEditContact")
        void onButEditContactClick(ClickEvent event) {
         //       clientFactory.getPlaceController().goTo(new FormularioContactPlace(clientFactory.getSelectedContactId()));
        }
        
        @UiHandler("butAlum")
        void onButAlumClick(ClickEvent event) {
           //     clientFactory.getPlaceController().goTo(new ViewAlumnosPlace());
        }
        
        @UiHandler("butObser")
        void onButObserClick(ClickEvent event) {
        	// clientFactory.getPlaceController().goTo(new SeleccionAlumnoPlace("222"));
        }
        
        @UiHandler("butDeleteContact")
        void onButDeleteContactClick(ClickEvent event) {
 ContactRequest request = clientFactory.getRequestFactory().contactRequest();
             
             // Create editable contact
           //  ContactProxy editableContact = request.edit(contact);
             
             // Save contact
 //ContactProxy newEmployee = request.create(ContactProxy.class);

         //    editableContact.setName(nameEditor.getText());
          //   editableContact.setAddress(addressEditor.getText());
          /*   request.remove().using(clientFactory.getSelectedContact());
             request.fire(
                             new Receiver<Void>() {
                                     @Override
                                     public void onSuccess(Void response) {
                                    	 Window.alert("ok borrado");
                                             // Move to ViewContactsPlace
                                             //clientFactory.getPlaceController().goTo(new ViewContactsPlace());
                                     }
                             }
             );*/
                //clientFactory.getPlaceController().goTo(new EditContactPlace(clientFactory.getSelectedContactId()));
        }
}
