package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.repositories;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models.Cuenta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICuentaRepository extends CrudRepository<Cuenta, Integer> {

    @Query("SELECT c FROM Cuenta c WHERE c.nombre =:nombre AND c.apellidos =:apellidos")
    Optional<Cuenta> findByNombreAndApellidos(@Param("nombre") String nombre, @Param("apellidos") String apellidos);
}
