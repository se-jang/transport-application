package ku.cs.transport_application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class User {
    private String username;
    private String name;
    private String password;
    private String phoneNum;
    private String userType;
    private String address;

    @Id
    @GeneratedValue
    private final UUID id;
}
