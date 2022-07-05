package com.example.todolist.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "tareas")
public class Tarea implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Estado estado;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "fechacreacion")
    private Date fechaCreacion;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "fechaejecucion")
    private Date fechaEjecucion;

    @Column(name = "diasretraso")
    private int diasRetraso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idempleado")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Empleado empleado;
}
