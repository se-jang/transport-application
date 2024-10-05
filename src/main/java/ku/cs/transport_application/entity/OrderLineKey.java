package ku.cs.transport_application.entity;

import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.UUID;

public class OrderLineKey implements Serializable {
    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "menu_id")
    private UUID menuId;
}
