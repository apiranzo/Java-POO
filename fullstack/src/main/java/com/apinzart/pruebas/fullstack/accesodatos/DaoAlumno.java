package com.apinzart.pruebas.fullstack.accesodatos;


import com.apinzart.pruebas.fullstack.dtos.AlumnoDto;
import com.apinzart.pruebas.fullstack.dtos.CursoDto;

public interface DaoAlumno extends Dao<AlumnoDto> {
	
	Iterable<AlumnoDto> obtenerAprobados();

	void apuntarseACurso(Long idAlumno, Long idCurso);
	
	Iterable<CursoDto> cursosAlumno(Long id);
}
