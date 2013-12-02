package com.angel.shared;

import com.angel.server.Alumno;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;


@ProxyFor(Alumno.class)
public interface AlumnoProxy extends GenericProxy {
	
	//int getId();
	 String getNombre();
	 String getApellidos();
	 String getNif();
	// int getVersion();
	// void setId(int id);
	 void setNombre(String s);
	 void setApellidos(String s);
	 void setNif(String s);
	
}
