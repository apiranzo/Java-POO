package com.apiranzo.javaPoo.dal;

import com.apiranzo.javaPoo.pojos.Empleado;

public interface DaoEmpleado extends Dao<Empleado>{
	
	Iterable<Empleado> buscarPorNombre(String nombre);

}
