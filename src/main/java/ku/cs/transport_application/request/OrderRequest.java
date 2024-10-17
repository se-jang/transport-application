package ku.cs.transport_application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    @NotBlank(message = "Customer username is required")
    private String customerUsername;

    @NotBlank(message = "Company username is required")
    private String companyUsername;

    @NotEmpty
    private List<ProductDetailRequest> productDetails;
}
