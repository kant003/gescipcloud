package com.angel.client;

import javax.validation.Validator;

import com.angel.shared.ContactDetailsProxy;
import com.angel.shared.ContactProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;

public final class SampleValidatorFactory extends AbstractGwtValidatorFactory {

/**
* Validator marker for the Validation Sample project. Only the classes and groups listed
* in the {@link GwtValidation} annotation can be validated.
*/
@GwtValidation(value = {ContactProxy.class, ContactDetailsProxy.class})
public interface GwtValidator extends Validator {
}

@Override
public AbstractGwtValidator createValidator() {
return GWT.create(GwtValidator.class);
}
}



