package principal.Controladores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import principal.Modelos.Producto;
import principal.Servicios.ProductoService;

@RestController
@RequestMapping("/producto") // http://localhost:8080/producto
public class ProductoController {
    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);
    @Autowired
    private ProductoService productoService;

    @PostMapping
    public Producto addProducto(@RequestBody Producto producto){
        logger.info("Llamando a productoService para guardar el producto: {}", producto);
        return productoService.guardar(producto);
    }
    @GetMapping("/{id}")
    public Producto getProducto(@PathVariable Long id){
        return productoService.getById(id);
    }
}
