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

import com.angel.shared.MatriculaProxy;



@Entity
@Table(name = "matricula")
@NamedQueries({
		@NamedQuery(name = "Matricula.findAll", query = "SELECT n FROM Matricula n where n.idAlumno =:foreignkey and n.texto like :filtro"),
		@NamedQuery(name = "Matricula.count", query = "SELECT COUNT(n) FROM Matricula n where n.idAlumno =:foreignkey and n.texto like :filtro"),
		@NamedQuery(name = "Matricula.delete", query = "delete from Matricula o where o.id =:id")


})


public class Matricula extends AbstractEntity<MatriculaProxy>{

	private final static String NAMEQUERY = "Matricula";

	

	@NotNull
	//@Size(min = 4, message = "Name must be at least 4 characters long.")
	private String texto;
	private int idAlumno;
	

	public Matricula() {
		super();
	}

	public Matricula(int id, String texto, int idAlumno) {
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

	
	public static Matricula findMatricula(int id) {
		
	/*	EntityManager em = entityManager();
		try {
			Matricula employee = em.find(Matricula.class, id);
			return employee;
		} finally {
			em.close();
		}*/
		return _findBase(id, Matricula.class);
	}
	
	
	public static List<Matricula> findAll(int idAlumno,String filtro, Integer start,
			Integer end) {
		return _findAllBase(NAMEQUERY,idAlumno,filtro, start, end);
	}
	/*public static List<Matricula> findAll(int idAlumno) {
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
}