package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.pruebas.productos;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models.Producto;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.repositories.IProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductosTests {

    @Autowired
    private IProductoRepository iProductoRepository;

    @Test
    public void testFindAll(){
        List<Producto> productoList = (List<Producto>) iProductoRepository.findAll();
        assertNotNull(productoList);
        assertFalse(productoList.isEmpty());
    }

    @Test
    @Rollback(value = false)
    public void testsaveProducto(){
        Producto producto = new Producto("Computadora Dell G15", "Computadora de alta gama", 21500.00);
        var guaradoProducto = iProductoRepository.save(producto);
        assertNotNull(guaradoProducto, "Produco guardado correctamente");
    }

    @Test
    public void testFindProductoByNombre(){
        var nombre = "Pan";
        Producto producto = iProductoRepository.findByNombre(nombre);
        assertEquals(nombre, producto.getNombre());
    }

    @Test
    public void testNotFindProductoByNombre(){
        var nombre = "Comida";
        Producto producto = iProductoRepository.findByNombre(nombre);
        assertNull(producto, "Producto no encontrado con ese nombre");
    }

    @Test
    @Rollback(value = false)
    public void testUpdateProducto() {
        Producto producto = iProductoRepository.findById(1).get();
        producto.setNombre("Caja de de Monsters Actualizada");
        iProductoRepository.save(producto);

        //Verificacion de actualizacion
        assertEquals(producto.getNombre(), "Caja de de Monsters Actualizada");
    }

    @Test
    @Rollback(value = false)
    public void testDeleteroducto(){
        iProductoRepository.deleteById(iProductoRepository.findById(1).get().getId());
        boolean productoExiste = iProductoRepository.findById(1).isPresent();
        assertFalse(productoExiste, "El producto no existe en la base de datos");
    }
}
