package com.angel.shared.temp;

import java.util.List;

import com.angel.server.GenericaEntity;
import com.angel.server.ObservacionAlumno;
import com.google.web.bindery.requestfactory.shared.Locator;

public class GenericLocator extends Locator<GenericaEntity, Long> {  
	  
	  @Override  
	  public GenericaEntity create(Class<? extends GenericaEntity> clazz) {  
	    try {  
	      return clazz.newInstance();  
	    } catch (InstantiationException e) {  
	      throw new RuntimeException(e);  
	    } catch (IllegalAccessException e) {  
	      throw new RuntimeException(e);  
	    }  
	  }  
	  
	  @Override  
	  public GenericaEntity find(Class<? extends GenericaEntity> clazz, Long id) {  
	    // TODO : create your entity manager !  
		 
	   // return entityManager.find(clazz, id);
		  //return new GenericaEntity();
		  return null;
	  }  
	  
	  /** 
	   * it's never called 
	   */  
	  @Override  
	  public Class<GenericaEntity> getDomainType() {  
	    throw new UnsupportedOperationException();  
	    // or return null;  
	  }  
	  
	  @Override  
	  public Long getId(GenericaEntity domainObject) {  
	    return (long) domainObject.getId();  
	  }  
	  
	  @Override  
	  public Class<Long> getIdType() {  
	    return Long.class;  
	  }  
	  
	  @Override  
	  public Object getVersion(GenericaEntity domainObject) {  
	    return domainObject.getVersion();  
	  }  
	  
	  public List<ObservacionAlumno> findAll(int idAlumno, String filtro, Integer start, Integer end){
		  return null;
	  }
	}  