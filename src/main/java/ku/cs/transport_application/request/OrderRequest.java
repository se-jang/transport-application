package ku.cs.transport_application.request;

import jakarta.validation.constraints.NotBlank;
import ku.cs.transport_application.common.OrderStatus;
import ku.cs.transport_application.entity.Product;
import lombok.Data;

import java.util.Map;

@Data
public class OrderRequest {

    @NotBlank
    private OrderStatus status;

    @NotBlank
    private String customerUsername;

    @NotBlank
    private String companyUsername;

    @NotBlank
    private Map<Product, Integer> productQuantities;
}
