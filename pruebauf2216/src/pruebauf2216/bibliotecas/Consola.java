package pruebauf2216.bibliotecas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Consola {
	// INSTANCIAS Y VARIABLES FINALES STATICAS 
	// TODO: ESTO ES UN COMENTARIO
		private static final Scanner sc = new Scanner(System.in);

		public static final boolean OPCIONAL = true;
		public static final boolean OBLIGATORIO = false;

		// METHODS DE LA CLASE
		/**
		 * Method de imprimación de los datos de objeto con un formato especifico
		 * 
		 * @param formato
		 * @param datos
		 */
		public static void printFormatObject(String formato, Object... datos) {
			System.out.printf(formato + "\n", datos);
		}

		/**
		 * Method de imprimación del Objeto pasado por parametro Uno con salto de línea
		 * otro no
		 * 
		 * @param obj
		 */
		public static void printObject(Object obj) {
			System.err.println(obj);
		}

		public static void printLineObject(Object obj) {
			System.err.print(obj);
		}

		/**
		 * Method de imprimación básico
		 */
		public static void print() {
			System.out.println();
		}

		/**
		 * Method para leer un String de consola pasando por parámetros el texto
		 * requerido y si la opción es obligatorio una respuesta o no
		 * 
		 * @param mensaje
		 * @param esOpcional
		 * @return
		 */
		public static String leerString(String mensaje, boolean esOpcional) {
			String texto;
			boolean repetir = true;

			do {
				printLineObject(mensaje + ": ");
				texto = sc.nextLine().trim();

				if (!esOpcional && texto.length() == 0) {
					printObject("Este dato es obligatorio");
				} else {
					repetir = false;
				}
			} while (repetir);

			return texto.length() > 0 ? texto : null;
		}

		/**
		 * Method LeerString con el mensaje de parámetro pero sin marcar el parámetro de
		 * obligatoriedad. Devuelve una llamada al method leerString Generico
		 * 
		 * @param mensaje
		 * @return
		 */
		public static String leerString(String mensaje) {
			return leerString(mensaje, OBLIGATORIO);
		}

		/**
		 * Method para leer un Long de consola pasando por parámetros el texto requerido
		 * y si la opción es obligatorio una respuesta o no.
		 * 
		 * @param mensaje
		 * @param opcional
		 * @return
		 */
		public static Long leerLong(String mensaje, boolean opcional) {
			boolean repetir = true;
			long valor = 0;

			do {
				try {
					String dato = leerString(mensaje, opcional);

					if (dato == null) {
						return null;
					}

					valor = Long.parseLong(dato);
					repetir = false;
				} catch (NumberFormatException e) {
					printObject("El número debe ser un entero entre " + Long.MIN_VALUE + " y " + Long.MAX_VALUE);
				}
			} while (repetir);

			return valor;
		}

		/**
		 * Method Leer Long con el mensaje de parámetro pero sin marcar el parámetro de
		 * obligatoriedad Devuelve una llamada al method leerLong Generico
		 * 
		 * @param mensaje
		 * @return
		 */
		public static Long leerLong(String mensaje) {
			return leerLong(mensaje, OBLIGATORIO);
		}

		/**
		 * Method para leer un Integer de consola pasando por parámetros el texto
		 * requerido y si la opción es obligatorio una respuesta o no y los valores
		 * máximos y mínimos entre los que debe estar el valor requerido.
		 * 
		 * @param mensaje
		 * @param opcional
		 * @param minimo
		 * @param maximo
		 * @return
		 */
		public static Integer leerInt(String mensaje, boolean opcional, Integer minimo, Integer maximo) {
			boolean repetir = true;
			int valor = 0;

			if (minimo == null) {
				minimo = Integer.MIN_VALUE;
			}

			if (maximo == null) {
				maximo = Integer.MAX_VALUE;
			}

			do {
				try {
					String dato = leerString(mensaje, opcional);
					if (dato == null) {
						return null;
					} 
						
					valor = Integer.parseInt(dato);
					
					if (valor < minimo || valor > maximo) {
						printObject("El valor está fuera de límites");
						
					} else {
						repetir = false;
					}
				} catch (NumberFormatException e) {
					System.out
							.println("El número debe ser un entero entre " + Integer.MIN_VALUE + " y " + Integer.MAX_VALUE);
				}
			} while (repetir);

			return valor;
		}

		/**
		 * Method Leer Int con el mensaje y si es obligatorio pero sin marcar el mínimo
		 * y maximo Devuelve una llamada al method leerInt Generico
		 * 
		 * @param mensaje
		 * @param opcional
		 * @return
		 */
		public static Integer leerInt(String mensaje, boolean opcional) {
			return leerInt(mensaje, opcional, null, null);
		}

		/**
		 * Method Leer Int con el mensaje de parámetro pero sin marcar el parámetro de
		 * obligatoriedad ni los valores min y max Devuelve una llamada al method
		 * leerInt Generico
		 * 
		 * @param mensaje
		 * @return
		 */
		public static Integer leerInt(String mensaje) {
			return leerInt(mensaje, OBLIGATORIO, null, null);
		}

		/**
		 * Method para leer un BigDecimal de consola pasando por parámetros el texto
		 * requerido y si la opción es obligatorio una respuesta o no y los valores
		 * máximos y mínimos entre los que debe estar el valor requerido.
		 * 
		 * @param mensaje
		 * @param opcional
		 * @param minimo
		 * @param maximo
		 * @return
		 */
		public static BigDecimal leerBigDecimal(String mensaje, boolean opcional, BigDecimal minimo, BigDecimal maximo) {
			boolean repetir = true;
			BigDecimal valorbd = BigDecimal.ZERO;

			do {
				try {
					String dato = leerString(mensaje, opcional);

					if (dato == null) {
						return null;
					}

					valorbd = new BigDecimal(dato);

					if ((minimo != null && valorbd.compareTo(minimo) < 0)
							|| (maximo != null && valorbd.compareTo(maximo) > 0)) {
						printObject("El valor está fuera de límites");
					} else {
						repetir = false;
					}
				} catch (NumberFormatException e) {
					printObject("El número debe ser un decimal");
				}
			} while (repetir);

			return valorbd;
		}

		/**
		 * Method Leer BigDecimal con el mensaje de parámetro y el parámetro de
		 * obligatorio pero sin marcar los valores min y max Devuelve una llamada al
		 * method leerBigDecimal Generico
		 * 
		 * @param mensaje
		 * @param opcional
		 * @return
		 */
		public static BigDecimal leerBigDecimal(String mensaje, boolean opcional) {
			return leerBigDecimal(mensaje, opcional, null, null);
		}

		/**
		 * Method Leer BigDecimal con el mensaje de parámetro pero sin marcar el
		 * parámetro de obligatoriedad ni los valores min y max Devuelve una llamada al
		 * method leerBigDecimal Generico
		 * 
		 * @param mensaje
		 * @return
		 */
		public static BigDecimal leerBigDecimal(String mensaje) {
			return leerBigDecimal(mensaje, OBLIGATORIO, null, null);
		}

		/**
		 * Method Leer Booleano con parámetro de mensaje
		 * 
		 * @param mensaje
		 * @return
		 */
		public static Boolean leerBoolean(String mensaje) {
			String texto = leerString(mensaje + " [P|D]");
			
			
			Boolean bool = "d".equalsIgnoreCase(texto);
			
			return bool;
		}
		/**
		 * Method Leer Booleano con parámetro de mensaje
		 * 
		 * @param mensaje
		 * @param opcion1
		 * @param opcion2
		 * @return
		 */
		public static Boolean leerBoolean(String mensaje, String opcion1, String opcion2) {
			String texto = leerString(mensaje + opcion1 + " " + opcion2);

			Boolean bool = opcion1.equalsIgnoreCase(texto);

			return bool;
		}

		/**
		 * Method para leer un LocalDate de consola pasando por parámetros el texto
		 * requerido y si la opción es obligatorio una respuesta o no y los valores
		 * máximos y mínimos entre los que debe estar el valor requerido.
		 * 
		 * @param mensaje
		 * @param opcional
		 * @param minima
		 * @param maxima
		 * @return
		 */
		public static LocalDate leerFecha(String mensaje, boolean opcional, LocalDate minima, LocalDate maxima) {
			boolean repetir = true;
			LocalDate fecha = null;

			if (minima == null) {
				minima = LocalDate.MIN;
			}

			if (maxima == null) {
				maxima = LocalDate.MAX;
			}

			do {
				try {
					String dato = leerString(mensaje + " [AAAA-MM-DD] ", opcional);

					if (dato == null) {
						return null;
					}

					fecha = LocalDate.parse(dato);

					if (fecha.isBefore(minima) || fecha.isAfter(maxima)) {
						printObject("Fecha fuera de límites");
					} else {
						repetir = false;
					}
				} catch (DateTimeParseException e) {
					printObject("La fecha debe ser válida");
				}
			} while (repetir);

			return fecha;
		}
		
		/**
		 * Method Leer LocalDate con el mensaje de parámetro y el parámetro de
		 * obligatorio pero sin marcar los valores min y max Devuelve una llamada al
		 * method leerLocalDate Generico
		 * @param mensaje
		 * @param opcional
		 * @return
		 */
		public static LocalDate leerFecha(String mensaje, boolean opcional) {
			return leerFecha(mensaje, opcional, null, null);
		}
		
		/**
		 * Method Leer LocalDate con el mensaje de parámetro pero sin marcar el
		 * parámetro de obligatoriedad ni los valores min y max Devuelve una llamada al
		 * method leerLocalDate Generico
		 * @param mensaje
		 * @return
		 */
		public static LocalDate leerFecha(String mensaje) {
			return leerFecha(mensaje, OBLIGATORIO);
		}

}
