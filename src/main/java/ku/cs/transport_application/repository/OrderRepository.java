package ku.cs.transport_application.repository;

import ku.cs.transport_application.common.OrderStatus;
import ku.cs.transport_application.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Order findByStatus(OrderStatus status);
    List<Order> findByCustomerId(UUID customerId);
    List<Order> findByCompanyId(UUID companyId);
}
