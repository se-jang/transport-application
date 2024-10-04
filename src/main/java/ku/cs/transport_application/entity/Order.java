package ku.cs.transport_application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import ku.cs.transport_application.common.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue
    private final UUID id;

    private OrderStatus status;
    private LocalDateTime date;
    private User customer;

    @OneToMany(mappedBy = "order")
    private List<Product> products = new ArrayList<>();;
}
