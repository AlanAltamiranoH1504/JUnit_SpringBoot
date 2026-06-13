package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.dto.ExamResponseDTO;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.exeptions.NotFoundEntityException;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.repositories.interfaces.IExamRepository;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.services.interfaces.ExamService;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.services.interfaces.IExamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.mockito.Mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExamTest {
    // * VARIABLES GENERALES DE CLASE DE PRUEBAS
    IExamRepository iExamRepository;
    IExamService iExamService;
    List<Exam> exams;

    @BeforeEach
    void setUp() {
        // * SIMULACION ESTATICA DE INSTANCIA DE IEXAMREPOSITORY
        iExamRepository = mock(IExamRepository.class); //iExamRepository
        iExamService = ExamService
                .builder()
                .iExamRepository(iExamRepository)
                .build();
        exams = Exam.getExam();
    }

    @Test
    @DisplayName("Busqueda de examem por nombre")
    void test_find_exam_by_name() {
        // * CUANDO SE INVOQUE EL MOTODO FINDALL, RETORNA ESTOS DATOS
        when(iExamRepository.findAll()).thenReturn(exams);
        ExamResponseDTO examenToFound = iExamService.getExamByName("POO");
        assertNotNull(examenToFound, "Examen con nombre POO no encontrado");
        assertNotNull(examenToFound.getQuestions(), "El examen no tiene preguntas");
        assertEquals(1, examenToFound.getQuestions().size());
    }

    @Test
    @DisplayName("Validación lazamiento de excepcion NotFoundEntityException")
    void test_throw_not_found_exam_entity() {
        // * CUANDO SE INVOQUE EL MOTODO FINDALL, RETORNA ESTOS DATOS
        when(iExamRepository.findAll()).thenReturn(exams);
        Exception exception = assertThrows(NotFoundEntityException.class, () -> {
            iExamService.getExamByName("Examen Prueba");
        }, "No se lanza la excepcion");
        assertEquals("Examen con nombre Examen Prueba no encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Busqueda de un examen en una lista vacia")
    void test_find_exam_by_name_with_list_empty() {
        List<Exam> examsEmptyList = Exam.getExamsEmptyList();
        when(iExamRepository.findAll()).thenReturn(examsEmptyList);
        Exception exception = assertThrows(NotFoundEntityException.class, () -> {
            iExamService.getExamByName("ExamenError");
        });
        assertEquals("Examen con nombre ExamenError no encontrado", exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Busqueda de examenes por nombres")
    @ValueSource(strings = {"POO", "Desarrollo Web", "DevOps", "Compiladores"})
    void test_find_exams_by_names(String nameExam) {
        // * CUANDO SE INVOQUE EL MOTODO FINDALL, RETORNA ESTOS DATOS
        when(iExamRepository.findAll()).thenReturn(exams);
        ExamResponseDTO examToFound = iExamService.getExamByName(nameExam);

        assertNotNull(examToFound, "No se encontro un examen con el nombre: " + nameExam);
        assertFalse(examToFound.getQuestions().isEmpty(), "El examen" + nameExam + "de no tiene preguntas");
        assertEquals(1, examToFound.getQuestions().size());

        // * VERIFICAMOS QUE SE HAYA LLAMADO AL METODO DEL MOCK (findAll())
        verify(iExamRepository, times(1)).findAll();
        // * VERIFICAMOS QUE EL METODO DE LISTA VACIA NUNCA SE ENJECUTO
        verify(iExamRepository, never()).findAllEmpty();
    }

}