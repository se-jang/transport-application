package ku.cs.transport_application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import java.util.UUID;


@Data
@Entity
public class Product {
    @Id
    @GeneratedValue
    private final UUID id;

    private int amount;
    private String name;

    @ManyToOne
    private Type type;
}
