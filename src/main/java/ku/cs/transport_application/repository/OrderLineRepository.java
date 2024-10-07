package ku.cs.transport_application.repository;

import ku.cs.transport_application.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderLineRepository extends JpaRepository<OrderLine, UUID> {
    OrderLine findByOrderID(UUID orderID);
    OrderLine findByProductID(UUID productID);
}
