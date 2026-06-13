package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.repositories.interfaces;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models.Exam;

import java.util.List;

public interface IExamRepository {
    List<Exam> findAll();
    List<Exam> findAllEmpty();
}
