package principal_orden.Services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import principal_orden.DTO.Producto;
import principal_orden.Models.Orden;
import principal_orden.Repositories.OrdenRepository;

import java.util.List;

@Service
public class OrdenService {
    @Autowired
    private OrdenRepository ordenRepository;
    @Autowired
    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(OrdenService.class);
    @CircuitBreaker(name="productoServiceCircuitBreaker", fallbackMethod = "fallbackGetAproducto")
    public Orden realizarOrden(Orden order) {

        // Llamada al microservicio de productos
        String productUrl = "http://localhost:8081/producto/" + order.getProductoId();
        Producto product = restTemplate.getForObject(productUrl, Producto.class);
        if (product == null) {
            logger.error("Error al rescatar el producto " +  order.getProductoId());
            throw new RuntimeException("Producto no encontrado");
        }
        order.setTotal(product.getPrecio() * order.getQuantity());
        return ordenRepository.save(order);
    }
    public List<Orden> getAllOrdenes() {
        logger.debug("getAllOrdenes");
        logger.info("Retornando todas las ordenes");
        return ordenRepository.findAll();
    }
    public Orden fallbackGetAproducto(Orden o, Throwable throwable) {
        System.out.println("FALLBACK GET A PRODUCTO");
        logger.error("FALLBACK GET A PRODUCTO: "+throwable.getMessage());
        Orden fallbackOrden =  new Orden();
        fallbackOrden.setProductoId(o.getProductoId());
        fallbackOrden.setQuantity(o.getQuantity());
        fallbackOrden.setTotal(-1.0);
        return fallbackOrden;
    }
}
