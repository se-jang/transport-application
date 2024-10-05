package ku.cs.transport_application.entity;

import jakarta.persistence.*;
import ku.cs.transport_application.common.BillStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Bill {
    @Id
    @GeneratedValue
    private UUID id;

    private BillStatus status;
    private int shippingCost;
    private LocalDateTime date;
    private LocalDateTime paidDate;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;
}
