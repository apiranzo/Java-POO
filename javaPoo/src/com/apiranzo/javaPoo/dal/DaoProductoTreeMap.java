package com.apiranzo.javaPoo.dal;

import java.math.BigDecimal;
import java.util.TreeMap;

import com.apiranzo.javaPoo.pojos.Producto;

//Classe hija de DaoProducto 
//Adquiere los methods de DaoProducto y de Dao<T>
class DaoProductoTreeMap implements DaoProducto {

	//Creamos una única instáncia de Productos que en este caso va a ser un TreeMap de Productos
	protected TreeMap<Long, Producto> productos = new TreeMap<>();

	// SINGLETON
	/**
	 * Mediante el patrón de diseño SINGLETON damos forma a la instancia de DaoProductoArrayList
	 * Esta va a ser única y inmutable 
	 */
	protected DaoProductoTreeMap() {
		productos.put(1L, new Producto(1L, "Producto2TM", new BigDecimal("1234"), 45, false));
		productos.put(2L, new Producto(2L, "Producto2TM", new BigDecimal("1234"), 45, false));
		productos.put(3L, new Producto(3L, "Producto3TM", new BigDecimal("321"), 33, true));
	}

	private static final DaoProductoTreeMap INSTANCIA = new DaoProductoTreeMap();

	public static DaoProductoTreeMap getInstancia() {
		return INSTANCIA;
	}
	// FIN SINGLETON

	//METHODS HEREDADOS
	@Override
	public Iterable<Producto> obtenerTodos() {
		return productos.values();
	}

	@Override
	public Producto obtenerPorId(Long id) {
		return productos.get(id);
	}

	@Override
	public Producto insertar(Producto producto) {
		Long id = productos.size() > 0 ? productos.lastKey() + 1L : 1L;

		producto.setId(id);

		productos.put(id, producto);

		return producto;
	}

	@Override
	public Producto modificar(Producto producto) {
		productos.put(producto.getId(), producto);

		return producto;
	}

	@Override
	public void borrar(Long id) {
		productos.remove(id);
	}

	@Override
	public Iterable<Producto> buscarPorNombre(String nombre) {
		return productos.values().stream().filter(p -> p.getNombre().contains(nombre)).toList();
	}

}