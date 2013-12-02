package com.angel.shared;


import java.util.List;

import com.angel.server.Matricula;
import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Matricula.class)
public interface MatriculaRequest extends RequestContext {

	Request<MatriculaProxy> findMatricula(int id);
	Request<List<MatriculaProxy>> findAll(int foreignkey, String filtro, Integer start, Integer end);
	Request<Integer> count(int foreignkey, String filtro);
	Request<Integer> remove(List<Integer> contacts);

	InstanceRequest<MatriculaProxy, Void> persist();
	InstanceRequest<MatriculaProxy, Void> remove();
	InstanceRequest<MatriculaProxy, Void> update();
}
