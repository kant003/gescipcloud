package com.angel.shared;

import java.util.List;

import com.angel.server.Persona;
import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Persona.class)
public interface PersonaRequest extends RequestContext {

//	Request<Long> countPersona();

	Request<List<PersonaProxy>> findAllPersonas();

	Request<PersonaProxy> findPersona(Long id);

//	Request<List<PersonaProxy>> findPersonaEntries(int firstResult,	int maxResults);

	InstanceRequest<PersonaProxy, Void> persist();

	InstanceRequest<PersonaProxy, Void> remove();

}
