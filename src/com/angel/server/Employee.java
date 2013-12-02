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
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "observacionAlumno")
@NamedQueries({
		@NamedQuery(name = "Employee.findAll", query = "SELECT n FROM ObservacionAlumno n where n.idAlumno =:idAlumno and n.texto like :filtro"),
		@NamedQuery(name = "Employee.count", query = "SELECT COUNT(n) FROM ObservacionAlumno n where n.idAlumno =:idAlumno and n.texto like :filtro"),
		@NamedQuery(name = "Employee.delete", query = "delete from ObservacionAlumno o where o.id =:id")
		

})



public class Employee {

	

	@PrePersist
	void onPersist() {
		this.version++;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy=GenerationType.AUTO)
	// @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="POST_SEQ")
	protected int id;

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
	private String texto;
	private int idAlumno;

	public Employee() {
		super();
	//	super.namedQuery = "ObservacionAlumno";
	}

	
	
	
	public Employee(int id, String texto, int idAlumno) {
		super();
		this.id = id;
		this.texto = texto;
		this.idAlumno = idAlumno;
		
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


	

/*	public static ObservacionAlumno findObservacionAlumno(int id) {
		
		EntityManager em = entityManager();
		try {
			ObservacionAlumno employee = em.find(ObservacionAlumno.class, id);
			return employee;
		} finally {
			em.close();
		}
	}
*/
	

	  
	  public Integer count(int idAlumno, String filtro){
		  return null;
	  }
	

}
