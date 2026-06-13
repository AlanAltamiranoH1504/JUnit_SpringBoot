package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.dto;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamResponseDTO {
    private Long id_exam;
    private String name;
    private String description;
    private List<Question> questions = new ArrayList<>();
}
