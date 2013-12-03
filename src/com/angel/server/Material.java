package com.angel.server;






import java.util.List;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.angel.shared.MaterialProxy;



@Entity
@Table(name = "material")
@NamedQueries({
		@NamedQuery(name = "Material.findAll", query = "SELECT n FROM Material n where n.idMatricula =:foreignkey and n.texto like :filtro"),
		@NamedQuery(name = "Material.count", query = "SELECT COUNT(n) FROM Material n where n.idMatricula =:foreignkey and n.texto like :filtro"),
		@NamedQuery(name = "Material.delete", query = "delete from Material o where o.id =:id")


})


public class Material extends AbstractEntity<MaterialProxy>{

	private final static String NAMEQUERY = "Material";

	

	@NotNull
	//@Size(min = 4, message = "Name must be at least 4 characters long.")
	private String texto;
	private int idMatricula;
	

	public Material() {
		super();
	}

	public Material(int id, String texto, int idAlumno) {
		super();
		this.id = id;
		this.texto = texto;
		this.idMatricula = idAlumno;
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

	public int getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(int idMatricula) {
		this.idMatricula = idMatricula;
	}

	
	public static Material findMaterial(int id) {
		
	/*	EntityManager em = entityManager();
		try {
			Matricula employee = em.find(Matricula.class, id);
			return employee;
		} finally {
			em.close();
		}*/
		return _findBase(id, Material.class);
	}
	
	
	public static List<Material> findAll(int idMatricula,String filtro, Integer start,
			Integer end) {
		return _findAllBase(NAMEQUERY,idMatricula,filtro, start, end);
	}
	/*public static List<Matricula> findAll(int idAlumno) {
		return _findAllBase(NAMEQUERY,idAlumno);
	}*/

	

public static Integer count(int idMatricula, String filtro){
	return _count(NAMEQUERY,idMatricula,filtro);
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