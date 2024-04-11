package com.apinzart.pruebas.fullstack.fabrica;

import com.apinzart.pruebas.fullstack.accesodatos.DaoAlumno;
import com.apinzart.pruebas.fullstack.accesodatos.DaoCurso;
import com.apinzart.pruebas.fullstack.accesodatos.DaoCursoJpa;

public class FabricaEstatica implements Fabrica {

//	@Override
	/*	public DaoAlumno getDaoAlumno() {
		
		return new DaoAlumnoJpa();
	} */

	@Override
	public DaoCurso getDaoCurso() {
		return new DaoCursoJpa();
	}

}
