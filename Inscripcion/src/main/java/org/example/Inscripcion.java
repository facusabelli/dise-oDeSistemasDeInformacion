package org.example;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Inscripcion {
    private Set<Materia> materiasACursar;
    private boolean fueAprobada;
    private Alumno alumno;
    private LocalDateTime fechaDeEvaluacion;

    public Inscripcion(Alumno alumno, Set<Materia> materiasACursar) {
        this.alumno = alumno;
        this.materiasACursar = materiasACursar;
        this.fechaDeEvaluacion = LocalDateTime.now();
        this.fueAprobada = aprobada();
    }

    public boolean aprobada() {
        for (Materia materia : materiasACursar) {
            if (!materia.cumpleCorrelativas(alumno.getMateriasAprobadas())) {
                return false;
            }
        }
        return true;
    }
}

class Materia {
    private String nombre;
    private Set<Materia> correlativas;

    public Materia(String nombre) {
        this.nombre = nombre;
        this.correlativas = new HashSet<>();
    }

    public void agregarCorrelativa(Materia materia) {
        correlativas.add(materia);
    }

    public boolean cumpleCorrelativas(Set<Materia> materiasAprobadas) {
        return materiasAprobadas.containsAll(correlativas);
    }

    public String getNombre() {
        return nombre;
    }

    public Set<Materia> getCorrelativas() {
        return correlativas;
    }
}

class Alumno {
    private String nombre;
    private String apellido;
    private Set<Materia> materiasAprobadas;

    public Alumno(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.materiasAprobadas = new HashSet<>();
    }

    public void agregarMateriaAprobada(Materia materia) {
        materiasAprobadas.add(materia);
    }

    public Set<Materia> getMateriasAprobadas() {
        return materiasAprobadas;
    }
}

