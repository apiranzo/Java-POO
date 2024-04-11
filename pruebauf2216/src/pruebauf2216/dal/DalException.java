package pruebauf2216.dal;

/**
 * Classe de Gestión de como mostrar las Excepciones y sus mensajes
 */
public class DalException extends RuntimeException{

	private static final long serialVersionUID = -1105313146416123914L;

	public DalException() {
		super();
	}

	public DalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DalException(String message, Throwable cause) {
		super(message, cause);
	}

	public DalException(String message) {
		super(message);
	}

	public DalException(Throwable cause) {
		super(cause);
	}
}
