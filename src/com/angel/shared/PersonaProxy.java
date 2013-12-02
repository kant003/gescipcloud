package com.angel.shared;

import com.angel.server.Persona;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;


@ProxyFor(Persona.class)
public interface PersonaProxy extends EntityProxy {
	 Long getId();
	 String getNombre();
	 Integer getVersion();

}
