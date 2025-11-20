package principal.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import principal.Modelos.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
