package com.apinzart.pruebas.fullstack.accesodatos;

import com.apinzart.pruebas.fullstack.dtos.AlumnoDto;
import com.apinzart.pruebas.fullstack.dtos.CursoDto;

public interface DaoCurso extends Dao<CursoDto> {
	
	Iterable<AlumnoDto> alumnos(Long id);

}
