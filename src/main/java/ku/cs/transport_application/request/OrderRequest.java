package ku.cs.transport_application.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import ku.cs.transport_application.common.OrderStatus;
import ku.cs.transport_application.common.TransportationWorkerStatus;
import ku.cs.transport_application.entity.TransportationWorker;
import ku.cs.transport_application.entity.User;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OrderRequest {

    @NotBlank
    private OrderStatus status;

    @PastOrPresent(message = "The date must be in the present or past.")
    private LocalDateTime date;

    @FutureOrPresent(message = "The date must be in the present or Future.")
    private LocalDateTime deliveryDate;

    @NotBlank
    private User customer;

    @NotBlank
    private User company;

    @NotBlank
    private TransportationWorker worker;

}
