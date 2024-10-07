package ku.cs.transport_application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotBlank
    @Size(min=4, message = "Username must have at least 4 characters")
    private String username;

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name only contain letters")
    private String name;

    @NotBlank
    @Size(min=8, max=128, message = "Password must have at least 8 characters")
    private String password;

    @Pattern(regexp = "^[0-9]*$", message = "Phone number must be numeric")
    @Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits")
    private String phoneNum;

    @NotBlank(message = "User type is required")
    private String userType;

    @NotBlank(message = "Address is required")
    private String address;
}
