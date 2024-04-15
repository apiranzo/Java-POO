<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulari d'inscripció</title>

<!--BootStrap-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link
	href="https://cdn.datatables.net/1.10.24/css/dataTables.bootstrap4.min.css"
	rel="stylesheet">
<script defer src="./js/crud.js"></script>
</head>

<body class="container col-lg-8 mx-auto p-4 py-md-5">
	<header
		class="d-flex flex-wrap justify-content-between py-3 mb-4 border-bottom">
		<a href="/"
			class="row-col-6 d-flex align-items-center text-body-emphasis text-decoration-none">
			<span class="fs-4">MVC - GESTOR DE CURSOS</span>
		</a>
		<nav class="navbar navbar-expand-lg row-col-6">
			<div class="container-fluid">
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02"
					aria-controls="navbarTogglerDemo02" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarTogglerDemo02">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="#">Incici</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Cursos</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="#">Alumnes</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="#">Formularis</a>
						</li>
					</ul>
				</div>
			</div>
		</nav>
	</header>

	<main>
		<h1 class="text-body-emphasis">Hola ${alumno.nombre()}, te has
			dado de alta en el curso ${curso.nombre()}</h1>

		<hr class="col-3 col-md-2 mb-5">

		<div class="row g-5">
			<div class="col-md-6">
				<h2 class="text-body-emphasis">Informació personal</h2>
				<ul class="list-unstyled ps-0">
					<li>Nom: ${alumno.nombre()}</li>
					<li>Cognom: ${alumno.apellido()}</li>
					<li>Data de neixement: ${alumno.fechaNacimiento()}</li>
				</ul>
			</div>
			<div class="col-md-6">
				<h2 class="text-body-emphasis">Els teus cursos</h2>
				<ul class="list-unstyled ps-0">
					<li>Nom: ${alumno.nombre()}</li>
					<li>Cognom: ${alumno.apellido()}</li>
					<li>Data de neixement: ${alumno.fechaNacimiento()}</li>
				</ul>
			</div>


		</div>

	</main>


	<footer class="pt-5 my-5 text-body-secondary border-top">
		Created by the Bootstrap team · © 2024 </footer>



	<!--BootStrap-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
		integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
		integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
		crossorigin="anonymous"></script>


</body>
</html>