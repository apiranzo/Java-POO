package com.ipartek.formaciones.fullstack.configuraciones;

import com.apinzart.pruebas.fullstack.accesodatos.DaoCurso;
import com.apinzart.pruebas.fullstack.fabrica.Fabrica;
import com.apinzart.pruebas.fullstack.fabrica.FabricaEstatica;

public class Globales {
	
	private static final Fabrica fabrica = new FabricaEstatica();
	public static final DaoCurso daoCurso = fabrica.getDaoCurso();

}
