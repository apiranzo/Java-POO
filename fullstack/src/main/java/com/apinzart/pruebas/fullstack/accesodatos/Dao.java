package com.apinzart.pruebas.fullstack.accesodatos;


public interface Dao<T> {
	
	Iterable<T> obtenerTodos();
	T obtenerPorId(Long id);
	
	T insertar(T objeto);
	T modificar(T objeto);
	void borrar(Long id);

}
