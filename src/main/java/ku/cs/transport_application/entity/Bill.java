package ku.cs.transport_application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import ku.cs.transport_application.common.BillStatus;
import lombok.Data;

import java.time.LocalDate;
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
}
