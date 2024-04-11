package pruebauf2216.presentacion;

import static pruebauf2216.bibliotecas.Consola.*;

import pruebauf2216.dal.DaoLibro;
import pruebauf2216.dal.DaoLibroTreeMap;
import pruebauf2216.pojos.Libro;

public class PresentacionCMS {

	// INSTANCIAS Y VARIABLES FINALES STATICAS
	private static final int SALIR = 0;
	private static final DaoLibro DAO = DaoLibroTreeMap.getInstancia();

	public static void main(String[] args) {
		DaoLibroTreeMap.getInstancia();

		int opcion;

		/**
		 * Bucle de código para mostrar Menú
		 * 
		 */
		do {
			mostrarMenu();
			opcion = pedirOpcion();
			procesarOpcion(opcion);

		} while (opcion != SALIR);

	}

	/**
	 * Method mostrar el menú al usuario
	 */
	public static void mostrarMenu() {
		printObject("BIENVENIDO A NUESTRO CMS DE LIBROS");
		print();
		printObject("1. LISTADO DE REGISTROS");
		printObject("2. REGISTRO POR ID");
		print();
		printObject("3. INSERTAR REGISTRO");
		printObject("4. MODIFICAR REGISTRO");
		printObject("5. BORRAR REGISTRO");
		print();
		printObject("0. SALIR DE LA APP");
		print();
	}

	/**
	 * Method para solicitar una opción al usuario
	 * 
	 * @return
	 */
	public static int pedirOpcion() {
		return leerInt("Selecciona una opción", OBLIGATORIO);
	}

	/**
	 * Method de gestión de la opcion escogida por el usuario. Switch con los
	 * methods a escoger.
	 * 
	 * @param opcion
	 */
	public static void procesarOpcion(int opcion) {
		switch (opcion) {
		case 1 -> listado();
		case 2 -> buscar();
		case 3 -> insertar();
		case 4 -> modificar();
		case 5 -> borrar();
		case 0 -> salir();
		default -> printObject("Opción no reconocida");
		}
	}

	// METHODS MENÚ
	/**
	 * Listado de TODOS los libros, procesados por un for-each
	 */
	public static void listado() {
		for (var libro : DAO.obtenerTodos()) {
			mostrarFila(libro);
		}
	}

	/**
	 * Mostrar UN libro solicitado por su ID
	 */
	public static void buscar() {
		Long id = leerLong("Indicar el id del libro: ", OBLIGATORIO);

		var libro = DAO.obtenerPorId(id);

		if (libro == null) {
			printObject("No existe el libro");
			pedirOpcion();
		}
		mostrarFicha(libro);
	}

	/**
	 * Insertar UN libro
	 */
	public static void insertar() {

		var libro = new Libro();

		boolean repetir;
		do {
			if (libro.getTitulo() == Libro.TITULO_POR_DEFECTO) {
				libro.setTitulo(leerString("Indicar título", OBLIGATORIO));

				repetir = true;

			} else {
				repetir = false;
			}

		} while (repetir);

		do {
			if (libro.getIsbn() == Libro.ISBN_DEFECTO) {
				libro.setIsbn(leerString("Indicar ISBN", OBLIGATORIO));
				repetir = true;

			} else {
				repetir = false;
			}

		} while (repetir);

		do {
			if (libro.getNumpg() == Libro.MIN_NUM_PAGINAS) {
				libro.setNumpg(leerInt("Indicar número de páginas", OBLIGATORIO, Libro.MIN_NUM_PAGINAS, Integer.MAX_VALUE));

				repetir = true;

			} else {
				repetir = false;
			}

		} while (repetir);

		do {
			if (libro.getFormato() == Libro.FORMATO_LIBRO) {
				
				Boolean formato = leerBoolean("Indicar formato Papel o Digital", "D", "P");
				if(formato == true) {
					libro.setFormato(true);
				}else {
					libro.setFormato(false);
				}				

				repetir = true;

			} else {
				repetir = false;
			}

		} while (repetir);
		
		printObject("Libro a registrar");
		mostrarFicha(libro);
		
		Boolean respuesta = leerBoolean("Desea registrarlo? ", "S", "N");
		
		if (respuesta == true) {
			
			DAO.insertar(libro);
		} else {
			Boolean respuesta2 = leerBoolean("Volver a rellenar los campos? ", "S", "N");
			
			if (respuesta2 == true) {
				insertar();
			} else {
				mostrarMenu();
			}
		}
		
		

	}

	/**
	 * Modificar UN libro
	 */
	public static void modificar() {
		String titulo = leerString("Indicar título: ", OBLIGATORIO);
		String isbn = leerString("Indicar ISBN: ", OBLIGATORIO);
		Integer numpg = leerInt("Indicar número de páginas: ", OBLIGATORIO);
		Boolean formato = leerBoolean("Indicar formato: ");

		var libro = new Libro(null, titulo, isbn, numpg, formato);

		DAO.modificar(libro);
	}

	/**
	 * Borrar UN libro
	 */
	public static void borrar() {
		Long id = leerLong("Indicar el id del libro: ", OBLIGATORIO);

		DAO.borrar(id);
	}

	/**
	 * METHOD para salir de la app
	 */
	public static void salir() {
		printObject("Gracias por usar nuestro CMD");
	}

	/**
	 * Method para mostrar UNA FILA solicitando el libro por parámetro
	 * 
	 * @param libro
	 */
	public static void mostrarFila(Libro libro) {
		printFormatObject("| %03d | %-30s | %10s | %4d | %13s |", libro.getId(), libro.getTitulo(), libro.getIsbn(),
				libro.getNumpg(), libro.getFormato() ? "DIGITAL" : "PAPEL");
	}

	/**
	 * Method para mostrar FICHA COMPLETA de un libro
	 * 
	 * @param libro
	 */
	public static void mostrarFicha(Libro libro) {
		print();
		printFormatObject("%10s: %s", "Id", libro.getId());
		printFormatObject("%10s: %s", "Titulo", libro.getTitulo());
		printFormatObject("%10s: %s", "ISBN", libro.getIsbn());
		printFormatObject("%10s: %d", "Núm pg", libro.getNumpg());
		printFormatObject("%10s: %s", "Formato", libro.getFormato() ? "Digital" : "Papel");
		print();
	}

}
