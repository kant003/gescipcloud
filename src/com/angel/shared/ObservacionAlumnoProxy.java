package com.angel.shared;

import com.angel.server.ObservacionAlumno;
import com.google.web.bindery.requestfactory.shared.ProxyFor;


@ProxyFor(ObservacionAlumno.class)
public interface ObservacionAlumnoProxy extends GenericProxy{
	
	 String getTexto();
	 int getIdAlumno();
	 void setTexto(String s);
	 void setIdAlumno(int s);
			
}
