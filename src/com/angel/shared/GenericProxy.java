package com.angel.shared;

import com.angel.server.GenericaEntity;
import com.angel.shared.temp.GenericLocator;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;


@ProxyFor(value = GenericaEntity.class, locator = GenericLocator.class)
public interface GenericProxy extends EntityProxy {
	
	int getId();
	void setId(int id);
	
	 int getVersion();
	/* String getTexto();
	 int getIdAlumno();
	
	 int getVersion();
	 void setId(int id);
	 void setTexto(String s);
	 void setIdAlumno(int s);*/
	
	//int getClavePrimaria();
		
}
