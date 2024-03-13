package com.apiranzo.javaPoo.dal;

//Creación de una interface básica de DAO 
//DAO: Data Access Object
//<T> -> puede recibir cualquier tipo de dato
public interface Dao<T> {
	
	//METHODS predeterminados
	//CRUD: Create, Read, Update, Delete
	Iterable<T> obtenerTodos();
	T obtenerPorId(Long id);

	T insertar(T objeto);
	T modificar(T objeto);
	void borrar(Long id);
}