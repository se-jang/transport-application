package ku.cs.transport_application.repository;

import ku.cs.transport_application.common.OrderStatus;
import ku.cs.transport_application.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Order findByStatus(OrderStatus status);
}
