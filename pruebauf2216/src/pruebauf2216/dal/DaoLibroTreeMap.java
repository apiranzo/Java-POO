package pruebauf2216.dal;

import java.util.TreeMap;

import pruebauf2216.pojos.Libro;

public class DaoLibroTreeMap implements DaoLibro {
	
	protected TreeMap<Long, Libro> libros = new TreeMap<>();
	
	// SINGLETON
	protected DaoLibroTreeMap() {
		libros.put(1L, new Libro(1L, "Jo que no he conegut mai els homes", "1234567890", 54, false));
		libros.put(2L, new Libro(2L, "Manual de construcció", "1234567890", 33, true));
		libros.put(3L, new Libro(3L, "Solitud", "1234567890", 154, true));
		libros.put(4L, new Libro(4L, "La metamorfosis", "1234567890", 300, false));
	}
	
	private static final DaoLibroTreeMap INSTANCIA = new DaoLibroTreeMap();
	
	public static DaoLibroTreeMap getInstancia() {
		return INSTANCIA;
	}
	//FIN SINGLETON

	@Override
	public Iterable<Libro> obtenerTodos() {
		return libros.values();
	}

	@Override
	public Libro obtenerPorId(Long id) {
		return libros.get(id);
	}

	@Override
	public Libro insertar(Libro libro) {
		Long id = libros.size() > 0 ? libros.lastKey() + 1L : 1L;
		
		libro.setId(id);
		
		libros.put(id, libro);
		
		return libro;
	}

	@Override
	public Libro modificar(Libro libro) {
		libros.put(libro.getId(), libro);
		
		return libro;
	}

	@Override
	public void borrar(Long id) {
		libros.remove(id);
		
	}

}
