package pruebauf2216.dal;

/**
 * Interface Genérica de DAO - Data Access Object
 * Requiriendo por parametro cualquier tipo de Objeto
 * @param <T>
 */
public interface Dao<T> {

	/**
	 * Declaración de los methodos basicos
	 * CRUD: Create, Read, Update, Delete
	 * @return
	 */
		Iterable<T> obtenerTodos();
		T obtenerPorId(Long id);
		
		T insertar(T objeto);
		T modificar(T objeto);
		void borrar(Long id);

}
