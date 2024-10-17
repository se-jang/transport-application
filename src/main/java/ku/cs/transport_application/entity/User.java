package ku.cs.transport_application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import ku.cs.transport_application.common.UserRole;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    private String username;
    private String name;
    private String password;
    private String phoneNum;
    private String address;
    private UserRole userRole;

    @OneToMany(mappedBy = "company")
    private List<Bill> bills = new ArrayList<>();
}
