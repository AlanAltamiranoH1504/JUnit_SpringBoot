package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.repositories;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models.Exam;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models.Question;

import java.util.Arrays;
import java.util.List;

public class ExamenRepository implements IExamRepository {
    @Override
    public List<Exam> findAll() {
        return Arrays.asList(
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
    }

    @Override
    public List<Exam> findAllEmpty() {
        return List.of();
    }
}
