package com.angel.shared;

import com.angel.server.ObservacionAlumno;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.RequestContext;


@ProxyFor(ObservacionAlumno.class)
//@ProxyFor(value = ObservacionAlumno.class, locator = ObservacionAlumnoLocator.class)
public interface ObservacionAlumnoProxy extends GenericProxy{
	
	//int getId();
	 String getTexto();
	 int getIdAlumno();
	// int getVersion();
	 
	// void setId(int id);
	 void setTexto(String s);
	 void setIdAlumno(int s);
	
	//int getClavePrimaria();
		
}
