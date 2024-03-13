package com.apiranzo.javaPoo.dal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import com.apiranzo.javaPoo.pojos.Producto;

// Classe hija de DaoProducto 
// Adquiere los methods de DaoProducto y de Dao<T>
public class DaoProductoArrayList implements DaoProducto{
	
	
	//Creamos una única instáncia de Productos que en este caso va a ser una lista de Productos
	private ArrayList<Producto> productos = new ArrayList<>();

	// SINGLETON
	/**
	 * Mediante el patrón de diseño SINGLETON damos forma a la instancia de DaoProductoArrayList
	 * Esta va a ser única y inmutable 
	 */
	private DaoProductoArrayList() {
		productos.add(new Producto(1L, "Producto2", new BigDecimal("1234"), 45, false));
		productos.add(new Producto(2L, "Producto2", new BigDecimal("1234"), 45, false));
		productos.add(new Producto(3L, "Producto3", new BigDecimal("321"), 33, true));
	}

	private static DaoProductoArrayList instancia;

	public static DaoProductoArrayList getInstancia() {
		if (instancia == null) {
			instancia = new DaoProductoArrayList();
		}

		return instancia;
	}
	// FIN SINGLETON

	//METHODS HEREDADOS
	@Override
	public Iterable<Producto> obtenerTodos() {
		return productos;
	}

	@Override
	public Producto obtenerPorId(Long id) {
		return productos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
	}

	@Override
	public Producto insertar(Producto producto) {
		Optional<Long> oIdMaximo = productos.stream().map(p -> p.getId()).max((id1, id2) -> id1.compareTo(id2));
		Long id = oIdMaximo.isEmpty() ? 1L : oIdMaximo.get() + 1L;

		producto.setId(id);

		productos.add(producto);

		return producto;
	}

	@Override
	public Producto modificar(Producto producto) {
		for (int i = 0; i < productos.size(); i++) {
			if (producto.getId() == productos.get(i).getId()) {
				productos.set(i, producto);
			}
		}

		return producto;
	}

	@Override
	public void borrar(Long id) {
		for (int i = 0; i < productos.size(); i++) {
			if (id == productos.get(i).getId()) {
				productos.remove(i);
			}
		}
	}

	@Override
	public Iterable<Producto> buscarPorNombre(String nombre) {
		return productos.stream().filter(p -> p.getNombre().contains(nombre)).toList();
	}
}
