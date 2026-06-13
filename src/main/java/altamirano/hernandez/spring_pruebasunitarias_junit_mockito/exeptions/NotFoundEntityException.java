package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.exeptions;

public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException(String message) {
        super(message);
    }
}
