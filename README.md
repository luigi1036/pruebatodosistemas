## CRUD de Tareas para conocer el estado de las tareas de una empresa

## Objetivo
Este proyecto tiene como objetivo realizar la prueba de conocimiento

## Introducción 

Se requiere hacer un sistema web para registrar actividades o tareas en una empresa para
saber qué trabajo está pendiente por hacer. A dichas actividades se les debe asignar quién
de los empleados las ejecutará desde una lista.
Para ello se debe crear una base de datos que contenga la información de dichas actividades
y los empleados. La premisa es que a una actividad se le puede asignar sólo un empleado.
Un empleado puede estar asignado a más de una actividad.
De la actividad se desea saber el estatus, (si se realizó, o si por el contrario, está pendiente),
la fecha y hora estimada de ejecución, días de retraso de la ejecución respecto al día de hoy
(si no hay retraso indicar 0 (cero)), y quién está asignado a dicha actividad.

## Instruciones para la su prueba

Este proyecto utiliza Gradle. Antes de empezar,
asegurese de descargarlo e instalarlo. Luego, Gradle descargará automáticamente las librerias requeridas por el proyecto

### Repositorio
El código se encuentra alojado en github. Deberas contar con una cuenta github.
* Cree una carpeta en donde se incluirá el código fuente<br>
* Abra su consola y posicionese en la carpeta previamente creada<br>
* Ejecute el comando<br>
git clone https://github.com/luigi1036/pruebatodosistemas.git <br>

Luego de que termine la descarga, usted tendrá clonado el branch master en la carpeta previamente creada.

Ejecute el proyecto de forma local.

##El servicio actualmente cuenta con los siguientes métodos:<br>
###Método POST para Crear una Tarea:

La URL del método seria: [http://localhost:8080/tareas/tarea/crear](http://localhost:8080/tareas/tarea/crear) <br><br>
Se puede crear una tarea enviando una tarea con la siguiente información en un HTTP POST con un Json el cual tenga el siguiente formato:

POST -> /tareas/tarea/crear<br>
{
"nombre": "primera tarea",
"descripcion": "realizar la primera tarea",
"estado": "EN_EJECUCION",
"fechaEjecucion": "2022-07-03",
"empleado": {
"idEmpleado": 1
}
}<br><br>
En caso de crear la tarea exitosamente, el método devuelve como respuesta un HTTP 201-Create, en caso contrario un 400-Bad_Request


###Método GET para Obtener todas las Tareas:

La URL del método seria: [http://localhost:8080/tareas/alltareas](http://localhost:8080/tareas/alltareas) <br><br>
Se puede listar todas las tareas mediante HTTP GET.

En caso de que existan tareas en la base de datos, este método las obtendrá todas, el método devuelve como respuesta un HTTP 200-OK.
###Método GET para Obtener una tarea por su id:

La URL del método seria: [http://localhost:8080/tareas/tarea/{id}](http://localhost:8080/tareas/tarea/{id}) <br><br>
Se Obtiene una tarea por su id mediante HTTP GET.

En caso de que exista la tarea en la base de datos, este método obtendrá la tarea, el método devuelve como respuesta un HTTP 200-OK, en caso contrario un 404-Not_Found

###Método PUT para Actualizar una Tarea:

La URL del método seria: [http://localhost:8080/tareas/tarea/{id}](http://localhost:8080/tareas/tarea/{id}) <br><br>
Se puede actualizar una tarea enviando una tarea con la siguiente información en un HTTP PUT con un Json el cual tenga el siguiente formato:

PUT -> /tareas/tarea/{id}r<br>
{
"nombre": "primera tarea",
"descripcion": "realizar la primera tarea",
"estado": "EN_EJECUCION",
"fechaEjecucion": "2022-07-03",
"empleado": {
"idEmpleado": 1
}
}<br><br>
En caso de crear la tarea exitosamente, el método devuelve como respuesta un HTTP 201-Create, en caso contrario un 400-Bad_Request

###Método DELETE para Eliminar una tarea por su id:

La URL del método seria: [http://localhost:8080/tareas/tarea/{id}](http://localhost:8080/tareas/tarea/{id}) <br><br>
Se Elimina una tarea por su id mediante HTTP DELETE.

En caso de que se elimine la tarea de la base de datos, el método devuelve como respuesta un HTTP 200-OK, en caso contrario un 500-Internal_Server_Error

## Tecnologias

* Java 11
* IDE: Intellij IDEA
* Gradle
* Spring boot
* Mysql
