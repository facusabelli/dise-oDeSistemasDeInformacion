package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Set;
import java.util.HashSet;

class InscripcionTest {

    private Alumno alumno;
    private Materia matematica;
    private Materia fisica;
    private Materia quimica;

    @BeforeEach
    void setUp() {
        alumno = new Alumno("Facundo", "Sabelli");
        matematica = new Materia("Matematica");
        fisica = new Materia("Fisica");
        quimica = new Materia("Quimica");

        fisica.agregarCorrelativa(matematica);
        quimica.agregarCorrelativa(fisica);
    }

    @Test
    @DisplayName("Test cuando cumple con las correlativas")
    void inscripcionAprobadaCuandoCumpleCorrelativas() {
        alumno.agregarMateriaAprobada(matematica);
        alumno.agregarMateriaAprobada(fisica);

        Set<Materia> materiasACursar = new HashSet<>();
        materiasACursar.add(quimica);

        Inscripcion inscripcion = new Inscripcion(alumno, materiasACursar);
        Assertions.assertTrue(inscripcion.aprobada());
    }

    @Test
    @DisplayName("Test cuando no cumple con las correlativas")
    void inscripcionRechazadaCuandoNoCumpleCorrelativas() {
        alumno.agregarMateriaAprobada(matematica);

        Set<Materia> materiasACursar = new HashSet<>();
        materiasACursar.add(quimica);

        Inscripcion inscripcion = new Inscripcion(alumno, materiasACursar);
        Assertions.assertFalse(inscripcion.aprobada());
    }
}