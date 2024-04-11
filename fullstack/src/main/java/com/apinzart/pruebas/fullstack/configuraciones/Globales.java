package com.apinzart.pruebas.fullstack.configuraciones;

import com.apinzart.pruebas.fullstack.accesodatos.DaoAlumno;
import com.apinzart.pruebas.fullstack.accesodatos.DaoCurso;
import com.apinzart.pruebas.fullstack.fabrica.Fabrica;
import com.apinzart.pruebas.fullstack.fabrica.FabricaEstatica;

public class Globales {
	
	private static final Fabrica fabrica = new FabricaEstatica();
	public static final DaoCurso daoCurso = fabrica.getDaoCurso();
	public static final DaoAlumno daoAlumno = fabrica.getDaoAlumno();

}
