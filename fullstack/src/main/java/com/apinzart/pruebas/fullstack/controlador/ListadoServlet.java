package com.apinzart.pruebas.fullstack.controlador;

import java.io.IOException;

import com.apinzart.pruebas.fullstack.configuraciones.Globales;
import com.apinzart.pruebas.fullstack.dtos.AlumnoDto;
import com.apinzart.pruebas.fullstack.dtos.CursoDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListadoCursosServlet
 */
@WebServlet("/listado")
public class ListadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sId = request.getParameter("id"); // names del formulario, o datos de la querystring ?id=valor
		
		Long id = Long.valueOf(sId);
		
		Iterable<AlumnoDto> alumnos = Globales.daoCurso.alumnos(id);
		CursoDto curso = Globales.daoCurso.obtenerPorId(id);
		
		request.setAttribute("curso", curso);
		request.setAttribute("alumnos", alumnos);
		
		request.getRequestDispatcher("/WEB-INF/vistas/listado.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
