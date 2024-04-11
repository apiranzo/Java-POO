package com.apinzart.pruebas.fullstack.entidades;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "curso")
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank
	private String nombre;
	
	@ManyToMany(mappedBy = "curso")
	private Set<Alumno> alumnos;
	
	public Curso() {}

	public Curso(Long id, @NotNull @NotBlank String nombre, Set<Alumno> alumnos) {
		super();
		setId(id);
		setNombre(nombre);
		setAlumnos(alumnos);
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



	public Set<Alumno> getAlumnos() {
		return alumnos;
	}



	public void setAlumnos(Set<Alumno> alumnos) {
		this.alumnos = alumnos;
	}



	@Override
	public int hashCode() {
		return Objects.hash(alumnos, id, nombre);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		return Objects.equals(alumnos, other.alumnos) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre);
	}



	@Override
	public String toString() {
		return "Curso [id=" + id + ", nombre=" + nombre + ", alumnos=" + alumnos + "]";
	}

	
}
