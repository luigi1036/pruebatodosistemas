package com.example.todolist.interfaces;

import com.example.todolist.models.Tarea;

import java.util.List;
import java.util.Optional;

public interface ITareasService {

    List<Tarea> getAllTasks();
    Tarea save(Tarea tarea);
    Tarea findById(Long id);
    void deleteById(Long id);
}
