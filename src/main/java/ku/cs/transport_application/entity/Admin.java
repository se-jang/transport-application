package ku.cs.transport_application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Admin {
    private String username;
    private String name;
    private String password;

    @Id
    @GeneratedValue
    private final UUID id;
}
