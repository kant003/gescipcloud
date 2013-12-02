package com.angel.shared;

import com.angel.server.ObservacionAlumno;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface GescipRequestFactory extends RequestFactory {

	PersonaRequest personaRequest();
	ContactRequest contactRequest();
	AlumnoRequest alumnoRequest();
	ObservacionAlumnoRequest observacionAlumnoRequest();
	//GenericRequest<ObservacionAlumnoProxy> genericRequest();
	MatriculaRequest matriculaRequest();
	ContactDetailsRequest contactDetailsRequest();
}
