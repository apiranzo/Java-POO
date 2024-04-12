package com.apinzart.pruebas.fullstack.accesodatos;

import com.apinzart.pruebas.fullstack.dtos.AlumnoDto;
import com.apinzart.pruebas.fullstack.dtos.CursoDto;
import com.apinzart.pruebas.fullstack.entidades.Curso;

public class DaoCursoJpa extends AccesoDatosJpa implements DaoCurso {

	@Override
	public Iterable<CursoDto> obtenerTodos() {
		return enTransaccion(em -> em.createQuery("select c.id, c.nombre from Curso c", CursoDto.class).getResultList());
	}
	
	@Override
	public CursoDto obtenerPorId(Long id) {
		return enTransaccion(em -> em.createQuery("select c.id, c.nombre from Curso c where c.id=:id", CursoDto.class).setParameter("id", id).getSingleResult());
	}

	@Override
	public CursoDto insertar(CursoDto curso) {
		return enTransaccion(em -> {
			Curso c = new Curso(null, curso.nombre(), null);
			em.persist(c);
			return new CursoDto(c.getId(), c.getNombre());
		});
	}

	@Override
	public CursoDto modificar(CursoDto curso) {
		return enTransaccion(em -> {
			if(curso.id() == null) {
				throw new AccesoDatosException("Para modificar un curso debes proporcionar el id");
			}
			
			Curso c = new Curso(curso.id(), curso.nombre(), null);
			em.merge(c);
			return new CursoDto(c.getId(), c.getNombre());
		});
	}

	@Override
	public void borrar(Long id) {
		enTransaccionVoid(em -> em.remove(em.find(Curso.class, id)));
	}

/*	@Override
	public void verAlumnos(Long idCurso) {
		enTransaccionVoid(em ->{
			em.createNativeQuery("SELECT c.id, c.nombre, a.id, a.nombre\r\n"
					+ "	FROM gestor_cursos.alumnos a\r\n"
					+ "	JOIN gestor_cursos.curso_alumnos ca ON a.id = ca.alumnos_id\r\n"
					+ "	JOIN gestor_cursos.curso c ON ca.cursos_id = idCurso\r\n"
					+ "	ORDER BY c.id;").setParameter("idCurso", idCurso);
		});
		
	} */

	@Override
	public Iterable<AlumnoDto> alumnos(Long id) {
		return enTransaccion(em -> em.createQuery(
				"select a.id, a.nombre, a.apellido, a.fechaNacimiento, a.nota from Alumno a join a.cursos c where c.id = :id",
				AlumnoDto.class).setParameter("id", id).getResultList());		
	}
	

}
