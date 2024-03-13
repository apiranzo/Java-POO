package com.apiranzo.javaPoo.consola;

import java.math.BigDecimal;

import com.apiranzo.javaPoo.dal.DalException;
import com.apiranzo.javaPoo.dal.DaoProducto;
import com.apiranzo.javaPoo.dal.FabricaDaoTipos;
import com.apiranzo.javaPoo.pojos.Producto;

public class ProductoConsola {

	public static void main(String[] args) {
		try {
			DaoProducto dao = new FabricaDaoTipos().getDaoProducto(); // DaoProductoArrayList.getInstancia();
			
			//dao.insertar(new Producto(4L,"Nuevo", new BigDecimal("111"), 5, true));
			//dao.modificar(new Producto(1L, "Modificado", new BigDecimal("1234"), 7, false));
			
			//dao.insertar(4L, new Producto(4L, "Producto4TM", new BigDecimal("321"), 33, true));
			//dao.modificar(3L, new Producto(5L, "Producto3TM", new BigDecimal("321"), 33, true));
			dao.insertar(new Producto("Producto4", new BigDecimal("222"), 6, false));
			dao.borrar(3L);
			
			for(var p: dao.obtenerTodos()) {
				System.out.println(p);
			}
			
			System.out.println(dao.obtenerPorId(2L));
		} catch (DalException e) {
			System.out.println("Ha habido un error en el acceso a datos");
			e.printStackTrace(System.err);
		} catch(Exception e) {
			System.out.println("Se ha detectado un error no esperado");
			e.printStackTrace(System.err);
		}
		
		
	}
}
