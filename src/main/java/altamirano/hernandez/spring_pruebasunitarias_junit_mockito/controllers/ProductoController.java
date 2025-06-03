package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.controllers;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models.Producto;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.services.interfaces.IProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService iProductoService;

    @GetMapping("/prueba")
    public ResponseEntity<?> prueba() {
        Map<Object, String> json = new HashMap<>();
        json.put("msg", "Llega al controlador de productos");

        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listProductos() {
        try {
            List<Producto> productos = iProductoService.findAllProductos();
            return ResponseEntity.status(HttpStatus.OK).body(productos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveProducto(@Valid @RequestBody Producto producto, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            try {
                var savedProducto = iProductoService.saveProducto(producto);
                json.put("msg", "Producto creado correctamente");
                return ResponseEntity.status(HttpStatus.CREATED).body(json);
            } catch (Exception e) {
                json.put("msg", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
            }
        }
    }
}
