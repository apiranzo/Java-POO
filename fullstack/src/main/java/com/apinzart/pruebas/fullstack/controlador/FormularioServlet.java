package com.apinzart.pruebas.fullstack.controlador;

import java.io.Console;
import java.io.IOException;
import java.time.LocalDate;

import com.apinzart.pruebas.fullstack.configuraciones.Globales;
import com.apinzart.pruebas.fullstack.dtos.AlumnoDto;
import com.apinzart.pruebas.fullstack.dtos.CursoDto;
import com.apinzart.pruebas.fullstack.dtos.InscripcionDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListadoCursosServlet
 */
@WebServlet("/formulario")
public class FormularioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sId = request.getParameter("id"); // names del formulario, o datos de la querystring ?id=valor
		String sIdAlumno = request.getParameter("id-alumno");

		if (sId != null) {
			Long id = Long.valueOf(sId);
			CursoDto curso = Globales.daoCurso.obtenerPorId(id);
			request.setAttribute("curso", curso);
		}

		if (sIdAlumno != null) {
			Long idAlumno = Long.valueOf(sIdAlumno);
			AlumnoDto alumno = Globales.daoAlumno.obtenerPorId(idAlumno);
			request.setAttribute("alumno", alumno);
		}

		request.getRequestDispatcher("/WEB-INF/vistas/formulario.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// RECOGER DATOS DE LA PETICIÓN
		String sIdAlumno = request.getParameter("id");
		String sIdCurso = request.getParameter("id-curso");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String sFechaNacimiento = request.getParameter("fecha-nacimiento");

		// CONVERTIR SI ES NECESARIO
		Long idAlumno = null;

		if (!sIdAlumno.isBlank()) {
			idAlumno = Long.valueOf(sIdAlumno);
		}

		Long idCurso = sIdCurso.isBlank() ? null : Long.valueOf(sIdCurso);
		LocalDate fechaNacimiento = sFechaNacimiento.isBlank() ? null : LocalDate.parse(sFechaNacimiento);

		// EMPAQUETAR EN MODELO
		AlumnoDto alumno = new AlumnoDto(idAlumno, nombre, apellidos, fechaNacimiento, null);

		// LLAMAR A LÓGICA DE NEGOCIO
		if (idCurso != null) {
			// PREPARAR EL MODELO PARA LA SIGUIENTE VISTA

			var inscripcion = altaAlumnoCurso(alumno, idCurso);
			request.setAttribute("curso", inscripcion.curso());
			request.setAttribute("alumno", inscripcion.alumno());
			request.setAttribute("cursosAlumno", cursosAlumno(inscripcion.alumno().id()));

			// SALTAMOS A LA SIGUIENTE VISTA
			request.getRequestDispatcher("/WEB-INF/vistas/bienvenida-curso.jsp").forward(request, response);

		} else {

			AlumnoDto alumnoConId;
			
			if (idAlumno != null) {
				alumnoConId = Globales.daoAlumno.modificar(alumno);
			} else {
				alumnoConId = Globales.daoAlumno.insertar(alumno);
			}

			request.setAttribute("curso", null);
			request.setAttribute("alumno", alumnoConId);
			request.setAttribute("cursosAlumno", cursosAlumno(alumnoConId.id()));

			// SALTAMOS A LA SIGUIENTE VISTA
			request.getRequestDispatcher("/WEB-INF/vistas/bienvenida-curso.jsp").forward(request, response);
		}
	}

	private InscripcionDto altaAlumnoCurso(AlumnoDto alumno, Long idCurso) {

		var alumnoConId = Globales.daoAlumno.insertar(alumno);

		CursoDto curso = Globales.daoCurso.obtenerPorId(idCurso);
		Globales.daoAlumno.apuntarseACurso(alumnoConId.id(), idCurso);

		return new InscripcionDto(alumnoConId, curso);
	}

	private Iterable<CursoDto> cursosAlumno(Long idAlumno) {
		var cursosAlumno = Globales.daoAlumno.cursosAlumno(idAlumno);

		return cursosAlumno;
	}
}