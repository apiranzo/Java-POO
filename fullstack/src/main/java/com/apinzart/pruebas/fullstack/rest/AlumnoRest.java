package com.apinzart.pruebas.fullstack.rest;

import com.apinzart.pruebas.fullstack.entidades.Alumno;
import static com.apinzart.pruebas.fullstack.configuraciones.Globales.*;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/alumnos")
public class AlumnoRest {

	@GET
	public Iterable<Alumno> get() {
		return daoAlumno.obtenerTodos();
	}

	@GET
	@Path("/{id}")
	public Alumno getId(@PathParam("id") Long id) {
		return daoAlumno.obtenerPorId(id);
	}

	@POST
	public Alumno post(Alumno alumno) {
		return daoAlumno.insertar(alumno);
	}

	@PUT
	@Path("/{id}")
	public Alumno put(@PathParam("id") Long id, Alumno alumno) {
		return daoAlumno.modificar(alumno);
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam(value = "id") Long id) {
		daoAlumno.borrar(id);
	}

}
