package com.angel.shared;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface GescipRequestFactory extends RequestFactory {

	PersonaRequest personaRequest();
	ContactRequest contactRequest();
	AlumnoRequest alumnoRequest();
	ObservacionAlumnoRequest observacionAlumnoRequest();
	//GenericRequest<ObservacionAlumnoProxy> genericRequest();
	MatriculaRequest matriculaRequest();
	MaterialRequest materialRequest();
	ContactDetailsRequest contactDetailsRequest();
}
