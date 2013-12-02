package com.angel.shared;

import java.util.List;

import com.angel.server.Alumno;
import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Alumno.class)
public interface AlumnoRequest extends RequestContext {

//	Request<Long> countContacts();

	//Request<List<AlumnoProxy>> findAll();

	Request<AlumnoProxy> findAlumno(int id);
	Request<List<AlumnoProxy>> findAll(String filtro, Integer start, Integer end);
	Request<Integer> count(String filtro);
	Request<Integer> remove(List<Integer> contacts);
	//Request<Integer> remove(Set<AlumnoProxy> contacts);
	// Request<Void> removeAll(List<Contact> ids);
	
//	Request<List<PersonaProxy>> findContactEntries(int firstResult,	int maxResults);

	InstanceRequest<AlumnoProxy, Void> persist();

	InstanceRequest<AlumnoProxy, Void> remove();
	
	InstanceRequest<AlumnoProxy, Void> update();
   
}
