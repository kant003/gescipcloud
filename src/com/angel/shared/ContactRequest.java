package com.angel.shared;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.angel.server.Contact;
import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Contact.class)
public interface ContactRequest extends RequestContext {

//	Request<Long> countContacts();

	Request<List<ContactProxy>> findAllContacts();

	Request<ContactProxy> findContact(int id);
	Request<List<ContactProxy>> findAllContacts(String filtro, Integer start, Integer end);
	Request<Integer> count(String filtro);
	Request<Integer> remove(List<Integer> contacts);
	//Request<Integer> remove(Set<ContactProxy> contacts);
	// Request<Void> removeAll(List<Contact> ids);
	
//	Request<List<PersonaProxy>> findContactEntries(int firstResult,	int maxResults);

	InstanceRequest<ContactProxy, Void> persist();

	InstanceRequest<ContactProxy, Void> remove();
	
	InstanceRequest<ContactProxy, Void> update();
   
}
