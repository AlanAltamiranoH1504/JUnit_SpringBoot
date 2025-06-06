package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.repositories;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models.Cuenta;
import org.springframework.data.repository.CrudRepository;

public interface ICuentaRepository extends CrudRepository<Cuenta, Integer> {
}
