package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.services.interfaces;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models.Producto;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.repositories.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplProductoService implements IProductoService{

    @Autowired
    private IProductoRepository iProductoRepository;

    @Override
    public List<Producto> findAllProductos() {
        try {
            List<Producto> productos = (List<Producto>) iProductoRepository.findAll();
            return productos;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Producto findProductoById(int id) {
        try {
            Producto producto = iProductoRepository.findById(id).get();
            return producto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Producto findProductoByNombre(String nombre) {
        try {
            Producto producto = iProductoRepository.findByNombre(nombre);
            return producto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Producto saveProducto(Producto producto) {
        try {
            iProductoRepository.save(producto);
            return producto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteProductoById(int id) {
        try {
            Producto producto = iProductoRepository.findById(id).get();
            if (producto != null){
                iProductoRepository.deleteById(producto.getId());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
