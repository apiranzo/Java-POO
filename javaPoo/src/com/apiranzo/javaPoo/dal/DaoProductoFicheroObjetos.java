package com.apiranzo.javaPoo.dal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeMap;

import com.apiranzo.javaPoo.pojos.Producto;

public class DaoProductoFicheroObjetos extends DaoProductoCSV{

	public DaoProductoFicheroObjetos(String fichero) {
		super(fichero);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected TreeMap<Long, Producto> leerFichero() {
		try (FileInputStream fis = new FileInputStream(fichero);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			return (TreeMap<Long, Producto>) ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			throw new DalException("No se ha podido leer el fichero" + fichero, e);
		}
	}

	
	@Override
	protected void guardarFichero() {
		try (//Guardar un objeto
		 FileOutputStream fos = new FileOutputStream(fichero);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(productos);
		
		} catch (IOException e) {
			throw new DalException("No se ha podido guardar el fichero" + fichero, e);
		}
		
	}
	
	
	
	

}
