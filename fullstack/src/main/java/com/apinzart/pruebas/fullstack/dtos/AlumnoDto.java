package com.apinzart.pruebas.fullstack.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;


public record AlumnoDto(Long id, String nombre, String apellido,
		LocalDate fechaNacimiento, BigDecimal nota) {

}
