package com.angel.shared;

import java.util.List;

import com.angel.server.ObservacionAlumno;
import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(ObservacionAlumno.class)
public interface ObservacionAlumnoRequest extends RequestContext {

	Request<ObservacionAlumnoProxy> findObservacionAlumno(int id);
	Request<List<ObservacionAlumnoProxy>> findAll(int foreignkey, String filtro, Integer start, Integer end);
	Request<Integer> count(int foreignkey, String filtro);
	Request<Integer> remove(List<Integer> contacts);

	InstanceRequest<ObservacionAlumnoProxy, Void> persist();
	InstanceRequest<ObservacionAlumnoProxy, Void> remove();
	InstanceRequest<ObservacionAlumnoProxy, Void> update();
}
