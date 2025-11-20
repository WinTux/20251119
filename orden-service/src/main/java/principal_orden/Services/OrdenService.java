package principal_orden.Services;

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
    public Orden realizarOrden(Orden order) {
        // Llamada al microservicio de productos
        String productUrl = "http://localhost:8081/producto/" + order.getProductId();
        Producto product = restTemplate.getForObject(productUrl, Producto.class);
        if (product == null) throw new RuntimeException("Producto no encontrado");
        order.setTotal(product.getPrice() * order.getQuantity());
        return ordenRepository.save(order);
    }
    public List<Orden> getAllOrdenes() {
        return ordenRepository.findAll();
    }
}
