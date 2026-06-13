package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.repositories.interfaces;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models.Question;

import java.util.List;

public interface IQuestionRepository {
    List<Question> findQuestionsByExamId(Long examId);
}
