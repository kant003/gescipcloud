package com.angel.shared.temp;

import com.angel.server.Matricula;
import com.google.web.bindery.requestfactory.shared.Locator;

public class MatriculaLocator extends Locator<Matricula, Long> {  
	  
	  @Override  
	  public Matricula create(Class<? extends Matricula> clazz) {  
	    try {  
	      return clazz.newInstance();  
	    } catch (InstantiationException e) {  
	      throw new RuntimeException(e);  
	    } catch (IllegalAccessException e) {  
	      throw new RuntimeException(e);  
	    }  
	  }  
	  
	  @Override  
	  public Matricula find(Class<? extends Matricula> clazz, Long id) {  
	    // TODO : create your entity manager !  
		 
	   // return entityManager.find(clazz, id);
		  return new Matricula();
	  }  
	  
	  /** 
	   * it's never called 
	   */  
	  @Override  
	  public Class<Matricula> getDomainType() {  
	    throw new UnsupportedOperationException();  
	    // or return null;  
	  }  
	  
	  @Override  
	  public Long getId(Matricula domainObject) {  
	    return (long) domainObject.getId();  
	  }  
	  
	  @Override  
	  public Class<Long> getIdType() {  
	    return Long.class;  
	  }  
	  
	  @Override  
	  public Object getVersion(Matricula domainObject) {  
	    return domainObject.getVersion();  
	  }  
	}  