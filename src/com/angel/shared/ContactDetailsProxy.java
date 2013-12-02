package com.angel.shared;

import com.angel.server.ContactDetails;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;


@ProxyFor(ContactDetails.class)
public interface ContactDetailsProxy extends EntityProxy {
	int getId();
	 String getDisplayName();

	 Integer getVersion();
	 
}
