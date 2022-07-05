package com.example.todolist.controllers;


import com.example.todolist.interfaces.ITareasService;
import com.example.todolist.models.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private ITareasService taskService;

    // servicio que muestra todas la tareas existente
    @GetMapping("/alltareas")
    public List<Tarea> getTasks(){
        return taskService.getAllTasks();
    }

    // servicio que obtiene una tarea por su id
    @GetMapping("/tarea/{id}")
    public ResponseEntity<Map<String, Object>> getTask(@PathVariable Long id){
        Tarea tarea = null;
        Map<String, Object> response = new HashMap<>();

        try {
            tarea = taskService.findById(id);
            response.put("tarea", tarea);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al Obtener la Tarea");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(tarea == null){
            response.put("mensaje", "La Tarea con el Id: ".concat(id.toString()).concat(" no Existe"));
            return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    //servicio para crear una tarea
    @PostMapping("/tarea/crear")
    public ResponseEntity<Map<String, Object>> crearTask(@RequestBody Tarea tarea, BindingResult result){
        Tarea tareaNueva = null;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()) {
            List<String> errores = result.getFieldErrors().stream()
                    .map(error -> "El Campo " + error.getField() + " " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errores);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            tareaNueva = taskService.save(tarea);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al Crear la tarea");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("tarea", tareaNueva);
        response.put("mensaje", "La Tarea se ha Creado con Exito");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //servicio para actualizar una tarea por su id
    @PutMapping("/tarea/{id}")
    public ResponseEntity<Map<String, Object>> updateTask(@RequestBody Tarea task, BindingResult result,
                                                          @PathVariable Long id){

        Tarea currentTask = null;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){
            List<String> errores = result.getFieldErrors().stream()
                    .map((error) -> "El campo " + error.getField() + " " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errores);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        currentTask = taskService.findById(id); //valido que la tarea exista.

        if(currentTask == null){
            response.put("mensaje", "la Tarea con el Id: ".concat(id.toString()).concat("No Existe en la Base de datos"));
            return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            currentTask.setNombre(task.getNombre());
            currentTask.setDescripcion(task.getDescripcion());
            currentTask.setEstado(task.getEstado());
            currentTask.setFechaEjecucion(task.getFechaEjecucion());
            currentTask.setEmpleado(task.getEmpleado());
            taskService.save(currentTask);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al Actualizar la Tarea");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("task", currentTask);
        response.put("mensaje", "La Tarea se ha Actualizado con Exito");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // servico para eliminar una tarea por su id
    @DeleteMapping("/tarea/{id}")
    public ResponseEntity<Map<String, Object>> deletetask(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();

        try {
            taskService.deleteById(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al Eliminar la Tarea");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La tarea se ha Elimanado con Exito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
