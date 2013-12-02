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
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	 @Version
	  @Column(name = "version")
	  private Integer version;
	
	public Persona() {
		super();
	}

	public Persona(Long id, String firstName) {
		super();
		this.id = id;
		this.setNombre(firstName);
	}

	public Integer getVersion() {
		return version;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public static List<Persona> findAllPersonas() {
		EntityManager em = entityManager();
		try {
			List<Persona> list = em.createQuery("select o from Persona o")
					.getResultList();
			// force to get all the employees
			list.size();
			return list;
		} finally {
			em.close();
		}
	}

	public static Persona findPersona(Long id) {
		if (id == null) {
			return null;
		}
		EntityManager em = entityManager();
		try {
			Persona employee = em.find(Persona.class, id);
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
			Persona attached = em.find(Persona.class, this.id);
			em.remove(attached);
		} finally {
			em.close();
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
