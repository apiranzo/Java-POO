'use strict';

var URL= 'http://localhost:8080/fullstack/api/';

let ulCursos;
let ulAlumnos;
let formInscripcion;
let ulCursoAlumnos;

// id de HTML
let cabecera;
let ofertaCursos;
let listaAlumnos;
let registroAlumnos;
let infoCurso;


window.addEventListener('DOMContentLoaded', async function(){
    ulCursos = document.querySelector('#listadoCursos');
    ulAlumnos = document.querySelector('#listadoAlumnos');
    formInscripcion = document.querySelector('#formInscripcion');
    ulCursoAlumnos = document.querySelector('#cursoAlumnos');

    const respuestaCursos = await this.fetch(URL + 'cursos');
    const cursos = await respuestaCursos.json();

    const respuestaAlumnos = await this.fetch(URL + 'alumnos');
    const alumnos = await respuestaAlumnos.json(); 

    listadoCurso(cursos);
	listadoAlumno(alumnos);
    
});

async function listadoCurso(cursos){
	
	cursos.forEach(c => {
        const liCursos = document.createElement('li');
        liCursos.innerHTML = `${c.id} : ${c.nombre}
        
            <a class="btn btn-primary" href="javascript:apuntarmeCurso(${c.id})">Apuntarme</a>
            <a class="btn btn-primary" href="javascript:verAlumnosCurso(${c.id})">Ver Alumnos</a>;
            <a class="btn btn-primary" href="javascript:verFichaCurso(${c.id})">Ver Ficha Curso</a>`;
            
		
        ulCursos.appendChild(liCursos);
        
    });
}

async function listadoAlumno(alumnos){
	alumnos.forEach(a => {
        const liAlumnos = document.createElement('li');
        liAlumnos.innerHTML = `${a.id} : ${a.nombre} ${a.apellido}, Nacimiento ${a.fechaNacimiento}, Nota: ${a.nota}`;

        ulAlumnos.appendChild(liAlumnos);
    }); 
}

async function apuntarmeCurso(id){
    ulCursos.style.display = 'none';
  	formInscripcion.style.display = null;
 	formInscripcion.cursoid.value = id;
 	
  	console.log(formInscripcion.cursoid.value);

    const alumno = {
        nombre: formInscripcion.nombre.value,
        apellido: formInscripcion.apellido.value,
        fechaNacimiento: formInscripcion['fechaNacimiento'].value

    };

    const respuesta = await fetch(URL + 'alumnos', {
        body: JSON.stringify(alumno),
        method: 'POST',
        headers:{ 
            'Content-type' : 'application/json'
        }
        
    });
    console.log(alumno);

}

async function verAlumnosCurso(id){
	
	const respuestaAlumnosCurso = await fetch(URL + 'cursos/' + id + '/alumnos' );
    const alumnosCurso = await respuestaAlumnosCurso.json();
    
    const nombreCurso = document.getElementById('nombreCurso');
    nombreCurso.innerHTML = `${id}`;
    ulCursoAlumnos.innerHTML = ``;
    
    alumnosCurso.forEach(a => {
        const liAlumnosCurso = document.createElement('li');
        liAlumnosCurso.innerHTML = `${a.id} : ${a.nombre} ${a.apellido}, Nacimiento ${a.fechaNacimiento}, Nota: ${a.nota}`;

        ulCursoAlumnos.appendChild(liAlumnosCurso);
    }); 
	
}

async function verFichaCurso(id){
	
	const respuestaCurso = await fetch(URL + 'cursos/' + id);
    const curso = await respuestaCurso.json();
    
    console.log(curso);
    const fichaCurso = document.getElementById('fichaCurso');
    fichaCurso.innerHTML = ``;
    fichaCurso.innerHTML = `
    <div class="card text-center mb-3" style="width: 18rem;">
  <div class="card-body">
    <h5 class="card-title">${curso.nombre}</h5>
    <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
    <a href="javascript:apuntarmeCurso(${curso.id})" class="btn btn-primary">Apuntarme</a>
  </div>
  <div class="card-footer">
  <small><a href="javascript:verAlumnosCurso(${curso.id})">Ver Alumnos</a></small>
  </div>
</div>
    
    `;
}