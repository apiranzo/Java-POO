package com.apiranzo.javaPoo.consola;

import java.math.BigDecimal;

import com.apiranzo.javaPoo.dal.DalException;
import com.apiranzo.javaPoo.dal.DaoProducto;
import com.apiranzo.javaPoo.dal.FabricaDaoTipos;
import com.apiranzo.javaPoo.pojos.Producto;

public class ProductoConsola {

	public static void main(String[] args) {
		try {
			
			DaoProducto dao = new FabricaDaoTipos().getDaoProducto();
			for(var p: dao.obtenerTodos()) {
				System.out.println(p);
			}
			dao.insertar(new Producto("Nuevo1", new BigDecimal("111"), 5, true));
			dao.insertar(new Producto("Nuevo2", new BigDecimal("111"), 5, true));
			dao.insertar(new Producto("Nuevo3", new BigDecimal("111"), 5, true));
			dao.insertar(new Producto("Nuevo4", new BigDecimal("111"), 5, true));
			dao.insertar(new Producto("Nuevo", new BigDecimal("111"), 5, true));
			dao.modificar(new Producto(1L, "Modificado", new BigDecimal("1234"), 7, false));
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
