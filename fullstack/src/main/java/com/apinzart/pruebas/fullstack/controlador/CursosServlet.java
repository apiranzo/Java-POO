package com.apinzart.pruebas.fullstack.controlador;

import java.io.IOException;
import java.io.PrintWriter;

import com.apinzart.pruebas.fullstack.configuraciones.Globales;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListadoCursosServlet
 */
@WebServlet("/cursos")
public class CursosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		var cursos = Globales.daoCurso.obtenerTodos();
		request.setAttribute("cursos", cursos);
		
		request.getRequestDispatcher("/WEB-INF/vistas/cursos.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}