package com.apinzart.pruebas.fullstack.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
@Table(name="alumnos")
public class Alumno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank // - verificación de que el campo no puede estar en blanco
	private String nombre;
	
	@NotNull
	@NotBlank
	private String apellido;
	
	//para buscar validación hacer un jakarta.validation.constraints. y aparecen las opciones
	@Past // - validación de que la fecha sea anterior a la fecha actual
	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	@Min(0)
	@Max(10)
	private BigDecimal nota;
	
	//Relación con un mappedBy porque coincidir - Relación del campo
	@ManyToMany(mappedBy = "alumnos")
	private Set<Curso> cursos;
	
	public Alumno() {
		
	}
		

	public Alumno(Long id, @NotNull @NotBlank String nombre, @NotNull @NotBlank String apellido,
			@Past LocalDate fechaNacimiento, @Min(0) @Max(10) BigDecimal nota, Set<Curso> cursos) {
		super();
		setId(id);
		setNombre(nombre);
		setApellido(apellido);
		setFechaNacimiento(fechaNacimiento);
		setNota(nota);
		setCursos(cursos);
		
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



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}



	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}



	public BigDecimal getNota() {
		return nota;
	}



	public void setNota(BigDecimal nota) {
		this.nota = nota;
	}



	public Set<Curso> getCursos() {
		return cursos;
	}



	public void setCursos(Set<Curso> cursos) {
		this.cursos = cursos;
	}



	@Override
	public int hashCode() {
		return Objects.hash(apellido, cursos, fechaNacimiento, id, nombre, nota);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(apellido, other.apellido) && Objects.equals(cursos, other.cursos)
				&& Objects.equals(fechaNacimiento, other.fechaNacimiento) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(nota, other.nota);
	}



	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento="
				+ fechaNacimiento + ", nota=" + nota + ", cursos=" + cursos + "]";
	}
	
	

}
