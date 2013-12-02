package com.angel.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.angel.shared.AlumnoProxy;
import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;


@Entity
@Table(name = "alumno")
@NamedQueries({
		@NamedQuery(name = "Alumno.findAll", query = "SELECT n FROM Alumno n where n.nombre like :filtro"),
		@NamedQuery(name = "Alumno.count", query = "SELECT COUNT(n) FROM Alumno n where n.nombre like :filtro"),
		@NamedQuery(name = "Alumno.delete", query = "delete from Alumno o where o.id =:id")
		

})

//query.setParameter("date", new java.util.Date(), TemporalType.DATE);

public class Alumno extends AbstractEntity<AlumnoProxy>{

	public final static int SIN_FOREINGKEY = -1;
	private final static String NAMEQUERY = "Alumno";
	
/*	@PrePersist
	void onPersist() {
		this.version++;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;

	
	@Version
	@Column(name = "version")
	private Integer version = 0;
*/
	@NotNull
	//@Size(min = 4, message = "Name must be at least 4 characters long.")
	private String nombre;
	private String apellidos;
	private String nif;

	public Alumno() {
		super();
	}

	public Alumno(int id, String firstName, String lastName,
			String emailAddress) {
		super();
		this.id = id;
		this.nombre = firstName;
		this.apellidos = lastName;
		this.nif = emailAddress;
	}

/*	public int getVersion() {
		return version;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

*/

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	
public static Alumno findAlumno(int id) {
		
		/*EntityManager em = entityManager();
		try {
			Alumno employee = em.find(Alumno.class, id);
			return employee;
		} finally {
			em.close();
		}*/
	return _findBase(id, Alumno.class);
	}
	

public static List<Alumno> findAll(String filtro, Integer start,
		Integer end) {
	return _findAllBase(NAMEQUERY,SIN_FOREINGKEY,filtro, start, end);
}
/*public static List<Alumno> findAll() {
	return _findAllBase(NAMEQUERY,SIN_FOREINGKEY);
}*/



public static Integer count(String filtro){
	return _count(NAMEQUERY,SIN_FOREINGKEY,filtro);
}
public static Integer remove(List<Integer> contacts){
	return _remove(NAMEQUERY,contacts);
}


public void persist(){
	_persist();
}


public void remove(){
	_remove();
}

public void update(){
	_update();
}

	/*
	public static Integer count(String filtro) {

	//	CriteriaQuery<Country> q = cb.createQuery(Country.class);
	//	  Root<Country> c = q.from(Country.class);
	//	  ParameterExpression<Integer> p = cb.parameter(Integer.class);
	//	  q.select(c).where(cb.gt(c.get("population"), p));
		
		EntityManager em = null;
		try {

			em = entityManager();


			if(filtro == null || filtro.isEmpty())
				filtro = "%";
			int count = ((Long) em.createNamedQuery("Alumno.count")
					.setParameter("filtro", filtro)
					.getSingleResult()).intValue();

			return count;
		} catch (Exception e) {
			System.out.println("error:" + e);
		} finally {
			if (em != null)
				em.close();
		}
		return 0;
	}

	public static List<Alumno> findAll(String filtro, Integer start,
			Integer end) {
		System.out.println("servidor:findAll"+filtro+","+start+","+end);
		List<Alumno> result = new ArrayList<Alumno>();
		List<Alumno> resultRango = new ArrayList<Alumno>();
		EntityManager em = null;
		try {
			em = entityManager();

			end = Math.min(count(filtro), end);

			if(filtro == null || filtro.isEmpty())
				filtro = "%";
			
			resultRango = em.createNamedQuery("Alumno.findAll")
					.setParameter("filtro", filtro)
					.setFirstResult(start)
					.setMaxResults(end)
					.getResultList();
			// int count =
			// ((Long)em.createNamedQuery("getAccounts.count").getSingleResult()).intValue();

			
			System.out.println("servidor:findAll ok"+resultRango);
			return resultRango;
		} catch (Exception e) {
			System.out.println("error:" + e);
		} finally {
			if (em != null)
				em.close();
		}
		return null;

	}

	public static List<Alumno> findAll() {

		EntityManager em = null;
		try {
			System.out.println("findallcontact");
			em = entityManager();
			System.out.println("findallcontact2");
			Query q = null;
			q = em.createNamedQuery("Alumno.findAll");

			// List<Contact> list =
			// em.createQuery("select o from contact o").getResultList();
			List<Alumno> list = q.getResultList();
			// force to get all the employees
			// list = new ArrayList<Contact>();
			// Contact c = new Contact("1","asd","adf","adf");
			// list.add(c);
			// list.size();
			return list;
		} catch (Exception e) {
			System.out.println("error:" + e);
		} finally {
			if (em != null)
				em.close();
		}
		return null;
		
	}

	public static Alumno findAlumno(int id) {
		
		EntityManager em = entityManager();
		try {
			Alumno employee = em.find(Alumno.class, id);
			return employee;
		} finally {
			em.close();
		}
	}

	public static final EntityManager entityManager() {
		return EMF.get().createEntityManager();
	}

	public void persist() {
		System.out.println("persistiendo en servidor" + this.id + ","
				+ this.nombre);
		EntityManager em = entityManager();
		try {
			// em.persist(this); 

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

	public void remove() {
		System.out.println("borrando en servidor");
		EntityManager em = entityManager();
		try {
			em.getTransaction().begin();
			Alumno attached = em.find(Alumno.class, this.id);
			em.remove(attached);
			em.flush();
			em.getTransaction().commit();
			System.out.println("borrado..... en servidor");
		} finally {
			em.close();
		}
	}
	
	
	public static int remove(List<Integer>  contactos) {
		EntityManager em = entityManager();
		int deleteRecords =0 ;
		try {
			em.getTransaction().begin();
			
			System.out.println("borrado..... en servidor");
			for(Integer c : contactos){
			deleteRecords += em.createNamedQuery("Alumno.delete")
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


	
*/
}
