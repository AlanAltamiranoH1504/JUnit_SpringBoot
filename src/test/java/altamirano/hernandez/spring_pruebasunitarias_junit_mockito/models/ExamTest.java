package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.dto.ExamResponseDTO;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.exeptions.NotFoundEntityException;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.repositories.ExamenRepository;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.repositories.IExamRepository;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.services.interfaces.ExamService;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.services.interfaces.IExamService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExamTest {
    @Test
    @DisplayName("Busqueda de examem por nombre")
    void test_find_exam_by_name() {
        IExamRepository iExamRepository = new ExamenRepository();
        IExamService iExamService = new ExamService(iExamRepository);
        List<Exam> exams = Arrays.asList(
                Exam.builder()
                        .id_exam(1L)
                        .name("POO")
                        .description("Examen de Programacion")
                        .questions(Arrays.asList(Question.builder()
                                .id_question(1L)
                                .question("Pregunta 1")
                                .build()))
                        .build(),
                Exam.builder()
                        .id_exam(2L)
                        .name("Desarrollo Web")
                        .description("Examen de desarrollo web")
                        .questions(Arrays.asList(Question.builder()
                                .id_question(2L)
                                .question("Pregunta 2")
                                .build()))
                        .build(),
                Exam.builder()
                        .id_exam(3L)
                        .name("DevOps")
                        .description("Examen de devops")
                        .questions(Arrays.asList(Question.builder()
                                .id_question(3L)
                                .question("Pregunta 3")
                                .build()))
                        .build(),
                Exam.builder()
                        .id_exam(4L)
                        .name("Compiladores")
                        .description("Examen de Compiladores")
                        .questions(Arrays.asList(Question.builder()
                                .id_question(4L)
                                .question("Pregunta 4")
                                .build()))
                        .build()
        );
        ExamResponseDTO examenToFound = iExamService.getExamByName("POO");
        assertNotNull(examenToFound, "Examen con nombre POO no encontrado");
    }

    @Test
    @DisplayName("Validación lazamiento de excepcion NotFoundEntityException")
    void test_throw_not_found_exam_entity() {
        IExamRepository iExamRepository = new ExamenRepository();
        IExamService iExamService = new ExamService(iExamRepository);
        Exception exception = assertThrows(NotFoundEntityException.class, () -> {
            iExamService.getExamByName("Examen Prueba");
        }, "No se lanza la excepcion");
    }

    @ParameterizedTest
    @DisplayName("Busqueda de examenes por nombres")
    @ValueSource(strings = {"POO", "Desarrollo Web", "DevOps", "Compiladores"})
    void test_find_exams_by_names(String nameExam) {
        IExamRepository iExamRepository = new ExamenRepository();
        IExamService iExamService = new ExamService(iExamRepository);
        ExamResponseDTO examToFound = iExamService.getExamByName(nameExam);
        assertNotNull(examToFound, "No se encontro un examen con el nombre: " + nameExam);
    }

}