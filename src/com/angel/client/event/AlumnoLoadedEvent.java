package com.angel.client.event;

import com.google.web.bindery.event.shared.binder.GenericEvent;

public class AlumnoLoadedEvent extends GenericEvent {
	// public final List<Contact> contacts;
	private final String subject;
	private final String body;

	public AlumnoLoadedEvent(String subject, String body) {
		this.subject = subject;
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}
}
