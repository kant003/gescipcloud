package com.angel.shared.temp;

import java.util.List;

import com.angel.server.Matricula;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;
 
@Service(value = Matricula.class, locator = MyServiceLocator.class)
public interface AbstractRequestContext<T>  {
 
    Request<List<T>> findAll();
 
}
 


