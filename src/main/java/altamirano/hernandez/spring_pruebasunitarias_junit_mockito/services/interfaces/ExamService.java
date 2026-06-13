package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.services.interfaces;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.dto.ExamRequestDTO;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.dto.ExamResponseDTO;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.exeptions.NotFoundEntityException;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.repositories.interfaces.IExamRepository;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.repositories.interfaces.IQuestionRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
@Service
public class ExamService implements IExamService {
    private IExamRepository iExamRepository;
    private IQuestionRepository iQuestionRepository;

    @Override
    public List<ExamResponseDTO> listExams() {
        return List.of();
    }

    @Override
    public ExamResponseDTO getExam(Long idExam) {
        return null;
    }

    @Override
    public ExamResponseDTO getExamByName(String nameExam) {
        return iExamRepository.findAll()
                .stream()
                .filter(exam -> exam.getName().equals(nameExam))
                .findFirst()
                .map(exam -> {
                    return new ExamResponseDTO(exam.getId_exam(), exam.getName(), exam.getDescription(), exam.getQuestions());
                })
                .orElseThrow(() -> new NotFoundEntityException("Examen con nombre " + nameExam + " no encontrado"));
    }

    @Override
    public ExamResponseDTO createExam(ExamRequestDTO examRequestDTO) {
        return null;
    }

    @Override
    public ExamResponseDTO updateExam(Long idExam, ExamRequestDTO examRequestDTO) {
        return null;
    }

    @Override
    public void deleteExam(Long idExam) {

    }
}
