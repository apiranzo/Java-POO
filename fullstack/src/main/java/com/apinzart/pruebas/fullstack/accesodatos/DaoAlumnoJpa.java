package com.apinzart.pruebas.fullstack.accesodatos;

import java.math.BigDecimal;

import com.apinzart.pruebas.fullstack.entidades.Alumno;

public class DaoAlumnoJpa extends AccesoDatosJpa implements DaoAlumno {

	@Override
	public Iterable<Alumno> obtenerTodos() {
		return enTransaccion(em -> em.createQuery("select a from Alumno a", Alumno.class).getResultList());

	}

	@Override
	public Alumno obtenerPorId(Long id) {
		return enTransaccion(em -> em.find(Alumno.class, id));
	}

	@Override
	public Alumno insertar(Alumno alumno) {
		
		 enTransaccion(em -> {
			 em.persist(alumno);
			 return null;
		 });
		return alumno;
	}

	@Override
	public Alumno modificar(Alumno alumno) {
		return enTransaccion(em -> em.merge(alumno));
	}

	@Override
	public void borrar(Long id) {
		enTransaccion(em -> {
			em.remove(em.find(Alumno.class, id));
			return null;
		});
		
	}

}
