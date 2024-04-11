package pruebauf2216.pojos;

import java.util.Objects;
import static pruebauf2216.bibliotecas.Consola.*;

public class Libro {

	// CONSTANTES
	private static final String EXP_REG_NO_LETRAS = "^[^a-zA-Z]*$";
	private static final int NUM_ISBN = 10;
	private static final int MAX_LETRAS_TÍTULO = 150;
	private static final int MIN_LETRAS_TÍTULO = 3;
	public static final Boolean FORMATO_LIBRO = null;
	public static final int MIN_NUM_PAGINAS = 1;
	public static final String ISBN_DEFECTO = "0000000000";
	public static final String TITULO_POR_DEFECTO = "Titulo por defecto";

	// VARIABLES DE INSTANCIA
	private Long id;
	private String titulo;
	private String isbn;
	private Integer numpg;
	private Boolean formato;

	// CONSTRUCTORES
	public Libro(Long id, String titulo, String isbn, Integer numpg, Boolean formato) {
		setId(id);
		setTitulo(titulo);
		setIsbn(isbn);
		setNumpg(numpg);
		setFormato(formato);
	}

	public Libro() {
		this(null, TITULO_POR_DEFECTO, ISBN_DEFECTO, MIN_NUM_PAGINAS, FORMATO_LIBRO);
	}

	// METHODS GETTERS/SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if (id != null && id < 0) {
			printObject("No se admiten valores de id menores que 0");
		}

		this.id = id;
	}
	public void setId(String id) {
		if (id == null || id.isBlank()) {
			this.id = null;
			return;
		}

		this.id = Long.valueOf(id);
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		if(titulo == null || titulo.isBlank()) {
			printObject("El título del libro es obligatorio");
		} else if(titulo.length() < MIN_LETRAS_TÍTULO  || titulo.length() > MAX_LETRAS_TÍTULO) {
			
			printObject("El título del libro debe contener entre " + MIN_LETRAS_TÍTULO + " y " + MAX_LETRAS_TÍTULO + " carácteres");
		}else {
			
			this.titulo = titulo.trim();
		}
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		if(isbn == null || isbn.isBlank()) {
			printObject("El ISBN del libro es obligatorio");
			isbn = ISBN_DEFECTO;
		} else if(isbn.length() != NUM_ISBN && isbn.matches(EXP_REG_NO_LETRAS) ) {
			printObject("El ISBN debe tener una longitud de " + NUM_ISBN + " y no puede contener carácteres");
			isbn = ISBN_DEFECTO;
		} 
		this.isbn = isbn;
	}

	public Integer getNumpg() {
		return numpg;
	}

	public void setNumpg(Integer numpg) {
		if(numpg == null || numpg < MIN_NUM_PAGINAS) {
			printObject("El número de páginas es obligatorio y no puede ser menor que " + MIN_NUM_PAGINAS);
			numpg = MIN_NUM_PAGINAS;
		}
		this.numpg = numpg;
	}

	public Boolean getFormato() {
		return formato;
	}

	public void setFormato(Boolean formato) {
		if (formato == null) {
			printObject("Es obligatorio indicar el formato");
			this.formato = FORMATO_LIBRO;
		} 
		this.formato = formato;
	}

	// METHODS HASCODE-EQUALS / TOSTRING
	@Override
	public int hashCode() {
		return Objects.hash(formato, id, isbn, numpg, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(formato, other.formato) && Objects.equals(id, other.id)
				&& Objects.equals(isbn, other.isbn) && Objects.equals(numpg, other.numpg)
				&& Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", isbn=" + isbn + ", numpg=" + numpg + ", formato=" + formato
				+ "]";
	}

}
