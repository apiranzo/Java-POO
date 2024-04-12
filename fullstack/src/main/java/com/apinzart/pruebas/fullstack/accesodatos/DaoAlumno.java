package com.apinzart.pruebas.fullstack.accesodatos;


import com.apinzart.pruebas.fullstack.dtos.AlumnoDto;

public interface DaoAlumno extends Dao<AlumnoDto> {
	
	Iterable<AlumnoDto> obtenerAprobados();

}
