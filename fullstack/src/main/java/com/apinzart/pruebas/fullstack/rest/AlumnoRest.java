package com.apinzart.pruebas.fullstack.rest;

import static com.apinzart.pruebas.fullstack.configuraciones.Globales.daoAlumno;

import com.apinzart.pruebas.fullstack.dtos.AlumnoDto;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/alumnos")
public class AlumnoRest {

	@GET
	public Iterable<AlumnoDto> get() {
		return daoAlumno.obtenerTodos();
	}
	
/*	@GET
	@Path("/aprobados")
	public Iterable<Alumno> getAprobados() {
		return daoAlumno.obtenerAprobados();
	}
*/
	@GET
	@Path("/{id}")
	public AlumnoDto getId(@PathParam("id") Long id) {
		return daoAlumno.obtenerPorId(id);
	}

	@POST
	public AlumnoDto post(AlumnoDto alumno) {
		return daoAlumno.insertar(alumno);
	}

	@PUT
	@Path("/{id}")
	public AlumnoDto put(@PathParam("id") Long id, AlumnoDto alumno) {
		return daoAlumno.modificar(alumno);
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam(value = "id") Long id) {
		daoAlumno.borrar(id);
	}

}
