package com.angel.shared;

import com.angel.server.Material;
import com.google.web.bindery.requestfactory.shared.ProxyFor;


@ProxyFor(Material.class)
public interface MaterialProxy extends GenericProxy{
	
	 String getTexto();
	 int getIdMatricula();
	 void setTexto(String s);
	 void setIdMatricula(int s);
			
}
