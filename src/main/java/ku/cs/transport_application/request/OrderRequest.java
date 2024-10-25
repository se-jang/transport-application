package ku.cs.transport_application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import ku.cs.transport_application.common.OrderStatus;
import ku.cs.transport_application.entity.TransportationWorker;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class OrderRequest {

    private UUID id;

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @NotBlank(message = "Customer address is required")
    private String customerAddress;

    private String username;

    private String workerUsername;

    private OrderStatus status;

    private LocalDateTime date;

    private LocalDateTime deliveredDate;

    private TransportationWorker worker;

    @NotEmpty(message = "Product are required")
    private List<ProductDetailRequest> productDetails;
}
