package com.angel.shared;

import com.angel.server.Contact;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;


@ProxyFor(Contact.class)
public interface ContactProxy extends EntityProxy {
	
	int getId();
	 String getFirstName();
	 String getLastName();
	 String getEmailAddress();
	 Integer getVersion();
	 void setId(int id);
	 void setFirstName(String s);
	 void setLastName(String s);
	 void setEmailAddress(String s);
	
}
