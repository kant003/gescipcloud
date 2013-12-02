package com.angel.shared.temp;

import java.util.List;

import com.angel.server.GenericaEntity;
import com.angel.shared.ObservacionAlumnoProxy;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

//@Service(GenericaEntity.class)
@Service(value = GenericaEntity.class, locator = MyServiceLocator.class )
public interface GenericRequest<E extends EntityProxy> extends RequestContext {

//	Request<Long> countContacts();

	//Request<List<E>> findAll(int idAlumno);

//	Request<ObservacionAlumnoProxy> findObservacionAlumno(int id);
	Request<List<ObservacionAlumnoProxy>> findAll(int idAlumno, String filtro, Integer start, Integer end);
	Request<Integer> count(int idAlumno, String filtro);
	Request<Integer> remove(List<Integer> contacts);
	//Request<Integer> remove(Set<AlumnoProxy> contacts);
	// Request<Void> removeAll(List<Contact> ids);
	
//	Request<List<PersonaProxy>> findContactEntries(int firstResult,	int maxResults);

	InstanceRequest<ObservacionAlumnoProxy, Void> persist();

	InstanceRequest<ObservacionAlumnoProxy, Void> remove();
	
	InstanceRequest<ObservacionAlumnoProxy, Void> update();
   
}
