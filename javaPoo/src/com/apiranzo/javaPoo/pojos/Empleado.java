package com.apiranzo.javaPoo.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Empleado implements Serializable{
	
	private static final String NOMBRE_EMPLEADO = "Nombre Empleado";

	private static final long serialVersionUID = 1080246268984007826L;
	
	//VARIABLES DE INSTANCIA
	private Long id;
	private String nombre;
	
	
	public Empleado() {
		this(null, NOMBRE_EMPLEADO);
	}
	
	public Empleado(Long id, String nombre) {
		super();
		setId(id);
		setNombre(nombre);
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}


	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + "]";
	} 
	
	

	
}
