package com.angel.server;

import java.util.ArrayList;
import java.util.List;

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
import javax.validation.constraints.NotNull;

import com.angel.shared.ObservacionAlumnoProxy;


@Entity
@Table(name = "ObservacionAlumno")
@NamedQueries({
		@NamedQuery(name = "ObservacionAlumno.findAll", query = "SELECT n FROM ObservacionAlumno n where n.idAlumno =:foreignkey and n.texto like :filtro"),
		@NamedQuery(name = "ObservacionAlumno.count", query = "SELECT COUNT(n) FROM ObservacionAlumno n where n.idAlumno =:foreignkey and n.texto like :filtro"),
		@NamedQuery(name = "ObservacionAlumno.delete", query = "delete from ObservacionAlumno o where o.id =:id")
		

})

//query.setParameter("date", new java.util.Date(), TemporalType.DATE);

public class ObservacionAlumno extends AbstractEntity<ObservacionAlumnoProxy>{

	private final static String NAMEQUERY = "ObservacionAlumno";

	

/*	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy=GenerationType.AUTO)
	// @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="POST_SEQ")
	private int id;
*/
	/*
	 * @Version
	 * 
	 * @Column(name = "version") private Integer version = 0; // require to
	 * persist
	 */
/*	@Version
	@Column(name = "version")
	private Integer version = 0;
*/
	@NotNull
	//@Size(min = 4, message = "Name must be at least 4 characters long.")
	private String texto;
	private int idAlumno;
	

	public ObservacionAlumno() {
		super();
	}

	public ObservacionAlumno(int id, String texto, int idAlumno) {
		super();
		this.id = id;
		this.texto = texto;
		this.idAlumno = idAlumno;
	}

	

	/*
	 * public void setVersion(Integer version) { this.version = version; }
	 */

	
	public int getClavePrimaria() {
		return id;
	}
	
	/*public int getId() {
		return id;
	}

	
	
	public void setId(int id) {
		this.id = id;
	}

*/

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}

	
	public static ObservacionAlumno findObservacionAlumno(int id) {
		
	/*	EntityManager em = entityManager();
		try {
			ObservacionAlumno employee = em.find(ObservacionAlumno.class, id);
			return employee;
		} finally {
			em.close();
		}*/
		return _findBase(id, ObservacionAlumno.class);
	}
	
	
	public static List<ObservacionAlumno> findAll(int idAlumno,String filtro, Integer start,
			Integer end) {
		return _findAllBase(NAMEQUERY,idAlumno,filtro, start, end);
	}
	/*public static List<ObservacionAlumno> findAll(int idAlumno) {
		return _findAllBase(NAMEQUERY,idAlumno);
	}*/

	

public static Integer count(int idAlumno, String filtro){
	return _count(NAMEQUERY,idAlumno,filtro);
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
	/*public int getVersion() {
		return version;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}*/

	/*public static Integer count(int idAlumno,String filtro ) {

		//CriteriaQuery<Country> q = cb.createQuery(Country.class);
		//  Root<Country> c = q.from(Country.class);
		 // ParameterExpression<Integer> p = cb.parameter(Integer.class);
		 // q.select(c).where(cb.gt(c.get("population"), p));
		//
		EntityManager em = null;
		try {

			em = entityManager();


			if(filtro == null || filtro.isEmpty())
				filtro = "%";
			int count = ((Long) em.createNamedQuery("ObservacionAlumno.count")
					.setParameter("idAlumno", idAlumno)
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
*/
	/*public static List<ObservacionAlumno> findAll(int idAlumno,String filtro, Integer start,
			Integer end) {
		System.out.println("servidor:findAll"+filtro+","+start+","+end);
		List<ObservacionAlumno> result = new ArrayList<ObservacionAlumno>();
		List<ObservacionAlumno> resultRango = new ArrayList<ObservacionAlumno>();
		EntityManager em = null;
		try {
			em = entityManager();

			end = Math.min(count(idAlumno,filtro), end);

			if(filtro == null || filtro.isEmpty())
				filtro = "%";
			
			resultRango = em.createNamedQuery("ObservacionAlumno.findAll")
					.setParameter("idAlumno", idAlumno)
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

	}*/

	
	
	/*
	  public static List<ObservacionAlumno> findAll(int idAlumno) {
	 

		EntityManager em = null;
		try {
			System.out.println("findallcontact");
			em = entityManager();
			System.out.println("findallcontact2");
			Query q = null;
			q = em.createNamedQuery("ObservacionAlumno.findAll")
					.setParameter("idAlumno", idAlumno);

			// List<Contact> list =
			// em.createQuery("select o from contact o").getResultList();
			List<ObservacionAlumno> list = q.getResultList();
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
*/
	
	/*
	public static final EntityManager entityManager() {
		return EMF.get().createEntityManager();
	}*/

	/*public void persist() {
		System.out.println("persistiendo en servidor" + this.id + ","
				+ this.id);
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
			ObservacionAlumno attached = em.find(ObservacionAlumno.class, this.id);
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
			deleteRecords += em.createNamedQuery("ObservacionAlumno.delete")
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











