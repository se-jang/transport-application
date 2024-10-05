package ku.cs.transport_application.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import ku.cs.transport_application.common.BillStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BillRequest {

    @NotBlank
    private BillStatus status;

    @PositiveOrZero(message = "The shipping cost must be zero or a positive value.")
    private int shippingCost;

    @PastOrPresent(message = "The date must be in the present or past.")
    private LocalDateTime date;

    @PastOrPresent(message = "The date must be in the present or past.")
    private LocalDateTime paidDate;
}
