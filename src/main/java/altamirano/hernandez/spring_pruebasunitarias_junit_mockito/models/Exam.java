package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class Exam {
    private Long id_exam;
    private String name;
    private String description;
    @Builder.Default
    private List<Question> questions = new ArrayList<>();
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @PrePersist
    public void prePersist() {
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updated_at = LocalDateTime.now();
    }

    public static List<Exam> getExam() {
        return Arrays.asList(
                Exam.builder()
                        .id_exam(1L)
                        .name("POO")
                        .description("Examen de Programacion")
                        .questions(java.util.Arrays.asList(Question.builder()
                                .id_question(1L)
                                .question("Pregunta 1")
                                .build()))
                        .build(),
                Exam.builder()
                        .id_exam(2L)
                        .name("Desarrollo Web")
                        .description("Examen de desarrollo web")
                        .questions(java.util.Arrays.asList(Question.builder()
                                .id_question(2L)
                                .question("Pregunta 2")
                                .build()))
                        .build(),
                Exam.builder()
                        .id_exam(3L)
                        .name("DevOps")
                        .description("Examen de devops")
                        .questions(java.util.Arrays.asList(Question.builder()
                                .id_question(3L)
                                .question("Pregunta 3")
                                .build()))
                        .build(),
                Exam.builder()
                        .id_exam(4L)
                        .name("Compiladores")
                        .description("Examen de Compiladores")
                        .questions(java.util.Arrays.asList(Question.builder()
                                .id_question(4L)
                                .question("Pregunta 4")
                                .build()))
                        .build()
        );
    }

    public static List<Exam> getExamsEmptyList() {
        return Collections.emptyList();
    }
}
