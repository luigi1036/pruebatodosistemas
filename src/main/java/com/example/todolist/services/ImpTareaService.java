package com.example.todolist.services;

import com.example.todolist.interfaces.ITareasService;
import com.example.todolist.models.Tarea;
import com.example.todolist.repositories.ITareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImpTareaService implements ITareasService {

    @Autowired
    private ITareaRepository tasksRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Tarea> getAllTasks() {

        // con este metodo seteo los dias de retraso de todas las tareas
        List<Tarea> tasks = tasksRepository.findAll().stream()
                .map( task -> {
                    int days = daysDifference(task.getFechaEjecucion());
                    if(days > 0){
                        task.setDiasRetraso(0);
                    }else{
                        task.setDiasRetraso(days);
                    }
                    return task;
                }).collect(Collectors.toList());

        return tasks;
    }

    @Override
    public Tarea save(Tarea tarea) {
        if(tarea.getId() == null){
            Date fechaActual = formattDate(new Date());
            tarea.setFechaCreacion(fechaActual);
        }
        return tasksRepository.save(tarea);
    }


    @Override
    @Transactional(readOnly = true)
    public Tarea findById(Long id) {

        // con este metodo seteo los dias de retraso en una tarea especifica
        Tarea task = tasksRepository.findById(id).orElse(null);
        if(task != null){
            int days = daysDifference(task.getFechaEjecucion());
            if(days > 0){
                task.setDiasRetraso(0);
            }else{
                task.setDiasRetraso(days);
            }
        }
        return task;
    }


    @Override
    public void deleteById(Long id) {
        tasksRepository.deleteById(id);
    }

    // metodo para darle formato a las fechas
    private Date formattDate(Date fecha){
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        String fechaConFormato = formato.format(fecha);
        try {
            return formato.parse(fechaConFormato);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    // metodo para sacar la diferecia de dias de retraso
    private int daysDifference(Date taskDate){
        long date = taskDate.getTime();
        long datetoday = new Date().getTime();
        long difference = date - datetoday;
        long dias = (long )Math.floor(difference / (1000 * 60 * 60 *24));
        return (int) dias;
    }
}
