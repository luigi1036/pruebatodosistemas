package com.example.todolist.controllers;

import com.example.todolist.interfaces.IEmpleadoService;
import com.example.todolist.models.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost: 4200", "*"})
@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService iEmpleadoService;

    @GetMapping("/allEmpleados")
    public List<Empleado> getTasks(){
        return iEmpleadoService.getAllEmpleados();
    }
}
