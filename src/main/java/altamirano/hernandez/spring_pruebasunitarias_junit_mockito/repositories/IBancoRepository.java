package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.repositories;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models.Banco;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IBancoRepository extends CrudRepository<Banco, Integer> {

    @Query("SELECT b FROM Banco b WHERE b.nombre =:nombre")
    Banco findByNombre(@Param("nombre") String nombre);
}
