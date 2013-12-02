package com.angel.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Query;
import javax.persistence.Transient;


@MappedSuperclass
public class AbstractEntity<P> {

	@Transient
	private Class< P > entityHija;
	
	//@Transient
	//public static String namedQuery;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy=GenerationType.AUTO)
	// @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="POST_SEQ")
	protected int id;
	int version;
	
	

			@PrePersist
			void onPersist() {
				this.version++;
			}
			
	public AbstractEntity(){
		//this.namedQuery = "";
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
	
	
	
	
	
	
	public static Integer _count(String namedQuery,int foreignkey,String filtro ) {

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
			
			
			Query q = em.createNamedQuery(namedQuery+".count")
			.setParameter("filtro", filtro);
			
			if(foreignkey >= 0)
				q.setParameter("foreignkey", foreignkey);
			
			int count = ((Long) q.getSingleResult()).intValue();
					
					
					

			return count;
		} catch (Exception e) {
			System.out.println("error:" + e);
		} finally {
			if (em != null)
				em.close();
		}
		return 0;
	}
	

	

	public static final EntityManager entityManager() {
		return EMF.get().createEntityManager();
	}
	
	
	public static<P> P _findBase(int id, Class<P> clazz) {
		
		EntityManager em = entityManager();
		try {
			P employee = em.find(clazz, id);
			return employee;
		} finally {
			em.close();
		}
	}
	
	/*protected static<P> List<P> _findAllBase(String namedQuery,int foreignkey) {
		 

			EntityManager em = null;
			try {
				System.out.println("findallcontact");
				em = entityManager();
				
				
				
				Query q = em.createNamedQuery(namedQuery+".findAll");
				
				if(foreignkey >= 0)
					q.setParameter("foreignkey", foreignkey);
				
				List<P> list = q.getResultList();
								
				return list;
			} catch (Exception e) {
				System.out.println("error:" + e);
			} finally {
				if (em != null)
					em.close();
			}
			return null;
			
		}*/

	
	public static<P> List<P> _findAllBase(String namedQuery,int foreignkey,String filtro, Integer start, Integer end) {
		System.out.println("servidor:findAll con foreing"+filtro+","+start+","+end+","+namedQuery);
		List<P> result = new ArrayList<P>();
		List<P> resultRango = new ArrayList<P>();
		EntityManager em = null;
		try {
			em = entityManager();

			end = Math.min(_count(namedQuery,foreignkey,filtro), end);

			if(filtro == null || filtro.isEmpty())
				filtro = "%";
			
			 Query q = em.createNamedQuery(namedQuery+".findAll")
				.setParameter("filtro", filtro);
			 
				if(foreignkey >= 0)
					q.setParameter("foreignkey", foreignkey);
			
				if (start!=null && end!=null){
					resultRango = q
					.setParameter("filtro", filtro)
					.setFirstResult(start)
					.setMaxResults(end)
					.getResultList();
				}else{
					resultRango = q
						.setParameter("filtro", filtro)
						.getResultList();
				}
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
	
	
	public void _persist() {
	//	System.out.println("persistiendo en servidor" + this.id + ","	+ this.id);
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

	public void _update() {
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

	public void _remove() {
		System.out.println("borrando en servidor");
		EntityManager em = entityManager();
		try {
			em.getTransaction().begin();
			P attached = em.find(entityHija, this.id);
			em.remove(attached);
			em.flush();
			em.getTransaction().commit();
			System.out.println("borrado..... en servidor");
		} finally {
			em.close();
		}
	}
	
	
	public static int _remove(String namedQuery, List<Integer>  contactos) {
		EntityManager em = entityManager();
		int deleteRecords =0 ;
		try {
			em.getTransaction().begin();
			
			System.out.println("borrado..... en servidor");
			for(Integer c : contactos){
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
}
