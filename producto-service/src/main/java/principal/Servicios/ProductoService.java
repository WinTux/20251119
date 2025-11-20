package principal.Servicios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import principal.Modelos.Producto;
import principal.Repositorios.ProductoRepository;

@Service
public class ProductoService {
    private static final Logger logger = LoggerFactory.getLogger(ProductoService.class);
    @Autowired
    private ProductoRepository productoRepository;
    // Método para guardar un nuevo producto
    public Producto guardar(Producto producto) {
        logger.info("Intentando guardar producto: {}", producto);
        Producto productoGuardado = productoRepository.save(producto);
        logger.info("Producto guardado exitosamente con ID: {}", productoGuardado.getId());
        return productoGuardado;
    }
    // Obtener un producto a partir de su id
    public Producto getById(Long id) {
        logger.info("Buscando producto con ID: {}", id);
        return productoRepository.findById(id)
                .map(producto -> {
                    logger.info("Producto encontrado: {}", producto);
                    return producto;
                })
                .orElseThrow(() -> {
                    logger.error("No existe el producto con el ID: {}", id);
                    return new RuntimeException("No existe el producto con el id: " + id);
                });
    }
}
