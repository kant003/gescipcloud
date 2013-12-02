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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "contact")
@NamedQueries({
		@NamedQuery(name = "Contact.findAll", query = "SELECT n FROM Contact n"),
		@NamedQuery(name = "Contact.count", query = "SELECT COUNT(n) FROM Contact n"),
		//@NamedQuery(name = "Contact.delete", query = "delete from Contact obj where obj.id in (?1)")
		@NamedQuery(name = "Contact.delete", query = "delete from Contact o where o.id =:id")
		

})

//entityManager.createQuery("delete from Country where countryId in(:id)").setParameter("id", list).executeUpdate();

/*List<Long> lstExistingVisitors = ...
Query qDeleteVisitors = em.createQuery("delete from Visitor obj where obj.id in (?1)");
qDeleteVisitors.setParameter(1, lstExistingVisitors);
qDeleteVisitors.executeUpdate();
*/

public class Contact{

	

	@PrePersist
	void onPersist() {
		this.version++;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy=GenerationType.AUTO)
	// @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="POST_SEQ")
	private int id;

	/*
	 * @Version
	 * 
	 * @Column(name = "version") private Integer version = 0; // require to
	 * persist
	 */
	@Version
	@Column(name = "version")
	private Integer version = 0;

	@NotNull
	//@Size(min = 4, message = "Name must be at least 4 characters long.")
	private String firstName;
	private String lastName;
	private String emailAddress;

	public Contact() {
		super();
	}

	public Contact(int id, String firstName, String lastName,
			String emailAddress) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
	}

	public Integer getVersion() {
		return version;
	}

	/*
	 * public void setVersion(Integer version) { this.version = version; }
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/*
	 * @Override public Integer count(String foreignKey, String filtro) {
	 * Integer count = -1; PreparedStatement pst = null; Connection con =
	 * getConexion(); // System.out.println("clase:"+clase); try { if
	 * (clase.equals(Alumno.class.getName())) { //
	 * System.out.println("al:"+foreignKey); pst = con
	 * .prepareStatement("SELECT count(*) as tot FROM alumno"); }else if
	 * (clase.equals(ObservacionesAlumno.class.getName())) { //
	 * System.out.println("obs:"+foreignKey); pst = con .prepareStatement(
	 * "SELECT count(*) as tot FROM observacionesAlumno WHERE nifAlumno=?");
	 * pst.setString(1, (String)foreignKey); }else{ System.out.println("noooo");
	 * }
	 * 
	 * ResultSet rs = pst.executeQuery(); if (rs.next()) { count =
	 * rs.getInt("tot"); } else { count = 0; }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally {
	 * closeConection(con); }
	 * 
	 * return count; }
	 */

	/*
	 * @Override public List<T> select(String clase, String foreignKey, String
	 * filtro, Integer start, Integer end) throws BDException { //
	 * System.out.println("start:" + start + " end:" + end); List<Elemento>
	 * result = new ArrayList<Elemento>(); List<T> resultRango = new
	 * ArrayList<T>();
	 * 
	 * Connection con = getConexion(); try {
	 * 
	 * if(clase.equals(Alumno.class.getName())){ result =
	 * BDAlumnoHelper.select(con, filtro); }else
	 * if(clase.equals(ObservacionesAlumno.class.getName())){ result =
	 * BDObservacionesAlumnoHelper.select(con, (String)foreignKey, filtro); } //
	 * System.out.println("uuu:"+foreignKey+","+start+","+end); end =
	 * Math.min(count(clase,foreignKey, filtro), end);
	 * 
	 * for (Elemento a : result.subList(start, end)) { resultRango.add((T) a);
	 * // System.out.println("a:"+a.getNombre()); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally {
	 * closeConection(con); }
	 * 
	 * return resultRango; }
	 */

	public static Integer count(String filtro) {

		EntityManager em = null;
		try {

			em = entityManager();

			Query q = null;
			q = em.createNamedQuery("Contact.count");
			int count = ((Long) em.createNamedQuery("Contact.count")
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

	public static List<Contact> findAllContacts(String filtro, Integer start,
			Integer end) {
		List<Contact> result = new ArrayList<Contact>();
		List<Contact> resultRango = new ArrayList<Contact>();
		EntityManager em = null;
		try {
			em = entityManager();

			end = Math.min(count(filtro), end);

			Query q1 = em.createNamedQuery("Contact.findAll");
			resultRango = q1.setFirstResult(start).setMaxResults(end)
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

	public static List<Contact> findAllContacts() {

		EntityManager em = null;
		try {
			System.out.println("findallcontact");
			em = entityManager();
			System.out.println("findallcontact2");
			Query q = null;
			q = em.createNamedQuery("Contact.findAll");

			// List<Contact> list =
			// em.createQuery("select o from contact o").getResultList();
			List<Contact> list = q.getResultList();
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
		/*
		 * Contact c = new Contact("1","asd","adf","adf"); List<Contact> l = new
		 * ArrayList<Contact>(); l.add(c); return l;
		 */
	}

	public static Contact findContact(int id) {
		/*
		 * if (id == null) { return null; }
		 */
		EntityManager em = entityManager();
		try {
			Contact employee = em.find(Contact.class, id);
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
				+ this.firstName);
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

	public void remove() {
		System.out.println("borrando en servidor");
		EntityManager em = entityManager();
		try {
			em.getTransaction().begin();
			Contact attached = em.find(Contact.class, this.id);
			em.remove(attached);
			em.flush();
			em.getTransaction().commit();
			System.out.println("borrado..... en servidor");
		} finally {
			em.close();
		}
	}
	/*List<Long> lstExistingVisitors = ...
			Query qDeleteVisitors = em.createQuery("delete from Visitor obj where obj.id in (?1)");
			qDeleteVisitors.setParameter(1, lstExistingVisitors);
			qDeleteVisitors.executeUpdate();
			
			entityManager.createQuery("delete from Country where countryId in(:id)").setParameter("id", list).executeUpdate();

			*
			*/
	/*public static int remove(List<String>  contactos) {
		
		EntityManager em = entityManager();
		int deleteRecords =0 ;
		try {
			em.getTransaction().begin();
			
			System.out.println("borrado..... en servidor:"+contactos.toString());
			deleteRecords = em.createNamedQuery("Contact.delete")
			.setParameter("id", contactos)
	        .executeUpdate();

			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		
		return deleteRecords;
	}*/

	
	public static int remove(List<Integer>  contactos) {
		EntityManager em = entityManager();
		int deleteRecords =0 ;
		try {
			em.getTransaction().begin();
			
			System.out.println("borrado..... en servidor");
			for(Integer c : contactos){
			deleteRecords += em.createNamedQuery("Contact.delete")
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


	/*
	 * public static void removeAll(List<Contact> ids) {
	 * System.out.println("borrado!"); EntityManager em = entityManager(); try {
	 * 
	 * em.getTransaction().begin();
	 * 
	 * for(int i=0;i<ids.size();i++){ Contact attached =
	 * em.find(Contact.class,ids.get(i)); em.remove(attached); } em.flush();
	 * em.getTransaction().commit(); }catch(Exception e){
	 * System.out.println("error!"+e); }finally { em.close(); } }
	 */

}
