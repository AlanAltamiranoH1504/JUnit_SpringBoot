package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.repositories;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IProductoRepository extends CrudRepository<Producto, Integer> {

    @Query("SELECT p FROM Producto p WHERE p.nombre =:nombre")
    public Producto findByNombre(@Param("nombre") String nombre);
}
