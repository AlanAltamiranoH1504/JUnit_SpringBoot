package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.dto;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models.Question;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamRequestDTO {
    @NotBlank(message = "El nombre del examen es requerido")
    @Length(max = 255, message = "Longitud de nombre no valida")
    private String name;

    @NotBlank(message = "La descripción del examen es requerida")
    @Length(max = 1000, message = "Longitud de descripción no valida")
    private String description;

    @NotEmpty(message = "La lista de preguntas es requerida")
    private List<Question> questions = new ArrayList<>();
}
