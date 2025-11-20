package principal_orden.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import principal_orden.Models.Orden;
import principal_orden.Services.OrdenService;

import java.util.List;

@RestController
@RequestMapping("/orden")
public class OrdenController {
    @Autowired
    private OrdenService orderService;
    @PostMapping
    public Orden create(@RequestBody Orden order) {
        return orderService.realizarOrden(order);
    }
    @GetMapping
    public List<Orden> getAll() {
        return orderService.getAllOrdenes();
    }
}
