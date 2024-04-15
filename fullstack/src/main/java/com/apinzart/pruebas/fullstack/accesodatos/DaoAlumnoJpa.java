package com.apinzart.pruebas.fullstack.accesodatos;

import com.apinzart.pruebas.fullstack.dtos.AlumnoDto;
import com.apinzart.pruebas.fullstack.entidades.Alumno;

public class DaoAlumnoJpa extends AccesoDatosJpa implements DaoAlumno {

	@Override
	public Iterable<AlumnoDto> obtenerTodos() {
		return enTransaccion(
				em -> em.createQuery("select a.id, a.nombre, a.apellido, a.fechaNacimiento, a.nota from Alumno a",
						AlumnoDto.class).getResultList());

	}

	@Override
	public AlumnoDto obtenerPorId(Long id) {
		return enTransaccion(em -> em.createQuery(
				"select a.id, a.nombre, a.apellido, a.fechaNacimiento, a.nota from Alumno a where a.id=:id",
				AlumnoDto.class).setParameter("id", id).getSingleResult());
	}

	@Override
	public AlumnoDto insertar(AlumnoDto alumno) {
		return enTransaccion(em -> {
			Alumno a = new Alumno(null, alumno.nombre(), alumno.apellido(), alumno.fechaNacimiento(), alumno.nota(),
					null);
			em.persist(a);
			return new AlumnoDto(a.getId(), a.getNombre(), a.getApellido(), a.getFechaNacimiento(), a.getNota());
		});
	}

	@Override
	public AlumnoDto modificar(AlumnoDto alumno) {
		return enTransaccion(em -> {
			if (alumno.id() == null) {
				throw new AccesoDatosException("Para modificar un alumno debes proporcionar el id");
			}
			Alumno a = new Alumno(alumno.id(), alumno.nombre(), alumno.apellido(), alumno.fechaNacimiento(),
					alumno.nota(), null);

			em.merge(a);
			return new AlumnoDto(a.getId(), a.getNombre(), a.getApellido(), a.getFechaNacimiento(), a.getNota());
		});
	}

	@Override
	public void borrar(Long id) {
		
		enTransaccion(em -> {
			em.remove(em.find(Alumno.class, id));
			return null;
		});

	}

	@Override
	public Iterable<AlumnoDto> obtenerAprobados() {
		return enTransaccion(
				em -> em.createQuery("select a.id, a.nombre, a.apellido, a.fechaNacimiento, a.nota from Alumno a where a.nota > 5",
						AlumnoDto.class).getResultList());
	}

	@Override
	public void apuntarseACurso(Long idAlumno, Long idCurso) {
		enTransaccionVoid(em -> {
			em.createNativeQuery("INSERT INTO curso_alumnos (cursos_id, alumnos_id) VALUES (:idCurso, :idAlumno)").setParameter("idCurso", idCurso).setParameter("idAlumno", idAlumno).executeUpdate();
		});
		
	}
	

}
