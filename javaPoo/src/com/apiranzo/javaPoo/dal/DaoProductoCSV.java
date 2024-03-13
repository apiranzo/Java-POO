package com.apiranzo.javaPoo.dal;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.TreeMap;

import com.apiranzo.javaPoo.pojos.Producto;

//Comma Separated Values (;)
public class DaoProductoCSV extends DaoProductoTreeMap{

	//CSV va a trabajar con ficheros 
	protected String fichero;
	
	public DaoProductoCSV(String fichero) {
		super();
		productos.clear();
		this.fichero = fichero; 
	}
	
	// METHODS propios
	protected TreeMap<Long, Producto> leerFichero(){
		
		FileReader fr = null;
		Scanner sc = null;
		
		try {
			//Usamos una api de flujo para la lectura del documento
			fr = new FileReader(fichero);
			//Uso del scanner para leer las lineas del fichero
			sc = new Scanner(fr);
			
			//Toda la interacción del fichero lo hacemos con nextLine() de consula
			String linea;
			String[] pedazos;
			
			Producto producto;
			
			//Datos a extraer del fichero
			Long id;
			String nombre;
			BigDecimal precio;
			Integer stock;
			Boolean disponible;
			
			if(sc.hasNext()) {
				//Saltar línea de títulos
				sc.nextLine();
			}
			
			while(sc.hasNext()) {
				linea = sc.nextLine();
				
				//Rompemos la línea en trozos
				pedazos = linea.split(";");
				
				id = Long.valueOf(pedazos[0]);
				nombre = pedazos[1];
				precio = new BigDecimal(pedazos[2]);
				stock = Integer.valueOf(pedazos[3]);
				disponible = Boolean.valueOf(pedazos[4]);
				
				producto = new Producto(id, nombre, precio, stock, disponible);
				
				productos.put(producto.getId(), producto);
			}
			
			return productos;
			
		} catch (NumberFormatException e) {
			throw new DalException("Un dato recibido que debería ser numérico no lo es", e);
			
		} catch (FileNotFoundException e) {
			throw new DalException("No se ha podido leer el fichero", e);
		} finally {
			if(sc != null) {
				sc.close();
				sc = null;
			}
			if(fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					throw new DalException("No se ha podido cerrar el fichero", e);
				}
			}
		}
	}
	
	protected void guardarFichero() {
		
		try (//Escritura en el fichero
		 FileWriter fw = new FileWriter(fichero);
				PrintWriter pw = new PrintWriter(fw)) {
			pw.println("id;nombre;precio;stock;disponible");
			
			for(var p: productos.values()) {
				pw.printf("%s;%s;%s;%s;%s\n", p.getId(), p.getNombre(), p.getPrecio(), p.getStock(), p.getDisponible());
				
			}
		} catch (IOException e) {
			throw new DalException("No se ha podido escribir el fichero", e);
		}
	}
	
	// IMPLEMENTAMOS TODOS LO METHODS DE TreeMap
	// La información se nos va a guardar en un documento gestionado por CSV
	@Override
	public Iterable<Producto> obtenerTodos() {
		// Para obtener la información vamos a tener que leerla de un fichero
		leerFichero();
		return super.obtenerTodos();
	}


	@Override
	public Producto obtenerPorId(Long id) {
		// TODO Auto-generated method stub
		return super.obtenerPorId(id);
	}

	@Override
	public Producto insertar(Producto producto) {
		leerFichero();
		
		Producto p = super.insertar(producto);
		
		guardarFichero();
		
		return p;
	}
	@Override
	public Producto modificar(Producto producto) {
		// TODO Auto-generated method stub
		return super.modificar(producto);
	}

	@Override
	public void borrar(Long id) {
		super.borrar(id);
		
		guardarFichero();
	}

	@Override
	public Iterable<Producto> buscarPorNombre(String nombre) {
		leerFichero();
		
		return super.buscarPorNombre(nombre);
	}
	
	
}
