package ku.cs.transport_application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import ku.cs.transport_application.common.ProductType;
import ku.cs.transport_application.entity.User;

public class CreateOrderRequest {
    @NotNull(message = "Customer must be selected.")
    private User customer; // in Order Entity

    @NotBlank(message = "Name is required.")
    private String name; //product name in Product Entity

    @NotNull(message = "Type must be selected.")
    private ProductType type; //in Product Entity

    @NotBlank(message = "Value must be provided.")
    @Positive(message = "Value must be a positive number.")
    private int quantity; //product value in orderLine Entity
}
