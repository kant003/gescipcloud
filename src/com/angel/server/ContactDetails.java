package com.angel.server;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class ContactDetails {

	 @Id
	  @Column(name = "id")
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy=GenerationType.AUTO)
		//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="POST_SEQ")
	private int id;

	 @Version
	  @Column(name = "version")
	  private Integer version;
	
	private String displayName;

	public ContactDetails() {
		super();
	}

	public ContactDetails(int id, String displayName) {
		super();
		this.id = id;
		this.setDisplayName(displayName);
	}

	public Integer getVersion() {
		return version;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public static List<ContactDetails> findAllContactsDetails() {
		EntityManager em = entityManager();
		try {
			List<ContactDetails> list = em.createQuery(
					"select o from Contact o").getResultList();
			// force to get all the employees
			list.size();
			return list;
		} finally {
			em.close();
		}
	}

	public static ContactDetails findContactDetails(int id) {
		/*if (id == null) {
			return null;
		}*/
		EntityManager em = entityManager();
		try {
			ContactDetails employee = em.find(ContactDetails.class, id);
			return employee;
		} finally {
			em.close();
		}
	}

	public static final EntityManager entityManager() {
		return EMF.get().createEntityManager();
	}

	public void persist() {
		EntityManager em = entityManager();
		try {
			em.persist(this);
		} finally {
			em.close();
		}
	}

	public void remove() {
		EntityManager em = entityManager();
		try {
			ContactDetails attached = em.find(ContactDetails.class, this.id);
			em.remove(attached);
		} finally {
			em.close();
		}
	}

}
