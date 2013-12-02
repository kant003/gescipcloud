package com.angel.shared;

import com.angel.server.Matricula;
import com.angel.server.Persona;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;


@ProxyFor(Matricula.class)
public interface MatriculaProxy extends GenericProxy {
	//int getId();
	 String getTexto();
	 int getIdAlumno();
	// int getVersion();
	 
	// void setId(int id);
	 void setTexto(String s);
	 void setIdAlumno(int s);
	
	//int getClavePrimaria();
}
