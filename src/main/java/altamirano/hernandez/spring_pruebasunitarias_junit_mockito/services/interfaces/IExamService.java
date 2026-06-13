package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.services.interfaces;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.dto.ExamRequestDTO;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.dto.ExamResponseDTO;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models.Exam;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models.Question;

import java.util.List;

public interface IExamService {
    public List<ExamResponseDTO> listExams();
    public abstract ExamResponseDTO getExam(Long idExam);
    public abstract ExamResponseDTO getExamByName(String nameExam);
    public abstract ExamResponseDTO createExam(ExamRequestDTO examRequestDTO);
    public abstract ExamResponseDTO updateExam(Long idExam, ExamRequestDTO examRequestDTO);
    public abstract void deleteExam(Long idExam);
}
