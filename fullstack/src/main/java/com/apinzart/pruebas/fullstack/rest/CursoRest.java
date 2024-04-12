package com.apinzart.pruebas.fullstack.rest;

import static com.apinzart.pruebas.fullstack.configuraciones.Globales.daoCurso;
import static com.apinzart.pruebas.fullstack.configuraciones.Globales.daoAlumno;

import com.apinzart.pruebas.fullstack.dtos.AlumnoDto;
import com.apinzart.pruebas.fullstack.dtos.CursoDto;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/cursos")
public class CursoRest {
	
	@GET
	public Iterable<CursoDto> get() {
		return daoCurso.obtenerTodos(); 
	}
	
	@GET
	@Path("/{id}")
	public CursoDto getId(@PathParam(value = "id") Long id) {
		return daoCurso.obtenerPorId(id);
	}
	
	@GET
	@Path("/{id}/alumnos")
	public Iterable<AlumnoDto> alumnosDeUnCurso(@PathParam(value = "id") Long id) {
		return daoCurso.alumnos(id);
	}
	
/*	@GET
	@Path("/{id}/alumnos")
	public CursoDto apuntarse(@PathParam(value = "id") Long id, Alumno alumno) {
		Alumno
		return daoCurso.obtenerPorId(id);
	} */
	
	@POST
	public CursoDto post(CursoDto curso) {
		return daoCurso.insertar(curso);
	}
	
	@POST
	@Path("/{id}/alumnos")
	public AlumnoDto apuntarse(@PathParam(value = "id") Long id, final AlumnoDto alumno) {
		AlumnoDto alumnoConId = daoAlumno.insertar(alumno);

		// daoAlumno.apuntarseACurso(alumnoConId.id(), id);
		
		apuntarseAlumnoExistente(id, alumnoConId.id());

		return alumnoConId;
	}
	
	@POST
	@Path("/{id}/alumnos/{idAlumno}")
	public void apuntarseAlumnoExistente(@PathParam(value = "id") Long id, @PathParam(value = "idAlumno") Long idAlumno) {
		daoAlumno.apuntarseACurso(idAlumno, id);
	}
	
	@PUT
	@Path("/{id}")
	public CursoDto put(@PathParam(value = "id") Long id, CursoDto curso) {
		return daoCurso.modificar(new CursoDto(id, curso.nombre()));
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam(value = "id") Long id) {
		daoCurso.borrar(id);
	}

}
