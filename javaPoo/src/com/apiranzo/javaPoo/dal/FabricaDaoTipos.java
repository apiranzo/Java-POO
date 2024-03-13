package com.apiranzo.javaPoo.dal;

import java.util.Properties;

//Classe Fabrica que hereda de FabricaDao
public class FabricaDaoTipos implements FabricaDao {

	private static final String FABRICADAO_PROPERTIES = "fabricadao.properties";

	private final DaoProducto daoProducto;

	public FabricaDaoTipos() {
		this(FABRICADAO_PROPERTIES);
	}

	public FabricaDaoTipos(String configuracion) {
		try {
			String tipo;
			String fichero;

			Properties props = new Properties();
			props.load(FabricaDaoTipos.class.getClassLoader().getResourceAsStream(configuracion));

			tipo = props.getProperty("tipo");
			fichero = props.getProperty("fichero");
			

			daoProducto = switch (tipo) {
			case "arraylist" -> DaoProductoArrayList.getInstancia();
			case "treemap" -> DaoProductoTreeMap.getInstancia();
			case "csv" -> new DaoProductoCSV(fichero);
			case "objetos" -> new DaoProductoFicheroObjetos(fichero);
			default -> throw new DalException("NO se reconoce el tipo " + tipo);
			};
		} catch (Exception e) {
			throw new DalException("Error al leer la configuración", e);
		} finally {

		}

	}

	// METHOD HEREDADO
	@Override
	public DaoProducto getDaoProducto() {
		return daoProducto;
	}

}
