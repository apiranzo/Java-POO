package com.apiranzo.javaPoo.dal;

import com.apiranzo.javaPoo.pojos.Producto;

// INTERFACE de la classe Producto
// Asume de forma indirecta los methods establecidos en Dao
//<Producto> Marcamos el Tipo de dato sobre el que se trabajan los methods 
public interface DaoProducto extends Dao<Producto> {

	// METHODS específicos de DaoProducto
	Iterable<Producto> buscarPorNombre(String nombre);
}
