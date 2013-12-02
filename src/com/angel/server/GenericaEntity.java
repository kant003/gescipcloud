package com.angel.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import com.angel.shared.ObservacionAlumnoProxy;
import com.google.gwt.editor.client.Editor.Ignore;
import com.google.web.bindery.event.shared.binder.GenericEvent;

@MappedSuperclass
public abstract class GenericaEntity<E extends GenericaEntity<?>> {

	@Transient
	private Class< E > entityHija;
	
	@Transient
	public static String namedQuery;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy=GenerationType.AUTO)
	// @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="POST_SEQ")
	protected int id;
	
	
	int version;
	
	

	public GenericaEntity(){
		this.namedQuery = "";
	}
	
	public GenericaEntity(String namedQuery){
		this.namedQuery = namedQuery;
	}
	
	public int getClavePrimaria(){
		return id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public static final EntityManager entityManager() {
		return EMF.get().createEntityManager();
	}

	
	public void remove() {
		System.out.println("borrando en servidor");
		EntityManager em = entityManager();
		try {
			em.getTransaction().begin();
			E attached = em.find(entityHija, this.id);
			em.remove(attached);
			em.flush();
			em.getTransaction().commit();
			System.out.println("borrado..... en servidor");
		} finally {
			em.close();
		}
	}
	
	public static int remove(List<Integer>  contactos) {
		//Class< E > entityHija;
		EntityManager em = entityManager();
		int deleteRecords =0 ;
		try {
			em.getTransaction().begin();
			
			//System.out.println("borrado..... en servidor::"+entityHija.getSimpleName());
			for(Integer c : contactos){
				System.out.println("borrado:"+c.intValue());
				
			deleteRecords += em.createNamedQuery(namedQuery+".delete")
			.setParameter("id", c)
	        .executeUpdate();
			
			}
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		
		return deleteRecords;
	}
	
	public static Integer count(int idAlumno, String filtro) {

		EntityManager em = null;
		try {

			em = entityManager();

//			Query q = null;
	//		q = em.createNamedQuery("ObservacionAlumno.count");
		
			if(filtro == null || filtro.isEmpty())
				filtro = "%";
			
			int count = ((Long) em.createNamedQuery(namedQuery+".count")
					.setParameter("idAlumno", idAlumno)
					.setParameter("filtro", filtro)
					.getSingleResult())
					.intValue();

			return count;
		} catch (Exception e) {
			System.out.println("error:" + e);
		} finally {
			if (em != null)
				em.close();
		}
		return 0;
	}

	
	public static List<ObservacionAlumno> findAll(int idAlumno, String filtro, Integer start,
			Integer end) {
		List<ObservacionAlumno> result = new ArrayList<ObservacionAlumno>();
		List<ObservacionAlumno> resultRango = new ArrayList<ObservacionAlumno>();
		EntityManager em = null;
		try {
			em = entityManager();

			end = Math.min(count(idAlumno,filtro), end);

			if(filtro == null || filtro.isEmpty())
				filtro = "%";
			
			resultRango = em.createNamedQuery(namedQuery+".findAll")
					.setParameter("idAlumno", idAlumno)
					.setParameter("filtro", filtro)
					.setFirstResult(start)
					.setMaxResults(end)
					.getResultList();
		
			// int count =
			// ((Long)em.createNamedQuery("getAccounts.count").getSingleResult()).intValue();

			/*
			 * Query q = null; q = em.createNamedQuery("Contact.findAll");
			 * 
			 * List<Contact> list = q.getResultList();
			 * 
			 * end = Math.min(count(filtro), end);
			 * 
			 * for (Contact a : list.subList(start, end)) {
			 * resultRango.add((Contact) a); //
			 * System.out.println("a:"+a.getNombre()); }
			 */

			return resultRango;
		} catch (Exception e) {
			System.out.println("error:" + e);
		} finally {
			if (em != null)
				em.close();
		}
		return null;

	}
	
	
	

	public static List<ObservacionAlumno> findAll(int idAlumno) {

		EntityManager em = null;
		try {
			System.out.println("findallcontact");
			em = entityManager();
			System.out.println("findallcontact2");
			
			

			List<ObservacionAlumno> list = em.createNamedQuery(namedQuery+".findAll")
					.setParameter("idAlumno", idAlumno)
			        .getResultList();
			
			
		
			return list;
		} catch (Exception e) {
			System.out.println("error:" + e);
		} finally {
			if (em != null)
				em.close();
		}
		return null;
		/*
		 * Contact c = new Contact("1","asd","adf","adf"); List<Contact> l = new
		 * ArrayList<Contact>(); l.add(c); return l;
		 */
	}

	
	
	public void persist() {
		
		EntityManager em = entityManager();
		try {
			/* em.persist(this); */

			em.getTransaction().begin();
			em.persist(this);
			em.flush();
			em.getTransaction().commit();
			System.out.println("persist en servidor ok");
		} finally {
			em.close();
		}
	}

	public void update() {
		EntityManager em = entityManager();
		try {
			em.getTransaction().begin();
			em.merge(this);
			// persist(this);
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}



}
