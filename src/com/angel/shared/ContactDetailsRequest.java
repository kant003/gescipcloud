package com.angel.shared;

import java.util.List;

import com.angel.server.ContactDetails;
import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(ContactDetails.class)
public interface ContactDetailsRequest extends RequestContext {

//	Request<Long> countContacts();

	Request<List<ContactDetailsProxy>> findAllContactsDetails();

	Request<ContactDetailsProxy> findContactDetails(int id);

//	Request<List<PersonaProxy>> findContactEntries(int firstResult,	int maxResults);

	InstanceRequest<ContactDetailsProxy, Void> persist();

	InstanceRequest<ContactDetailsProxy, Void> remove();

}
