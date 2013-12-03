package com.angel.shared;

import java.util.List;

import com.angel.server.Material;
import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Material.class)
public interface MaterialRequest extends RequestContext {

	Request<MaterialProxy> findMaterial(int id);
	Request<List<MaterialProxy>> findAll(int foreignkey, String filtro, Integer start, Integer end);
	Request<Integer> count(int foreignkey, String filtro);
	Request<Integer> remove(List<Integer> contacts);

	InstanceRequest<MaterialProxy, Void> persist();
	InstanceRequest<MaterialProxy, Void> remove();
	InstanceRequest<MaterialProxy, Void> update();
}
