package alten.shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    private String name;

    @Column(length = 2000)
    private String description;

    private String image;
    private String category;
    private Double price;
    private Integer quantity;
    private String internalReference;
    private Long shellId;

    // 👇 enum stock status
    @Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus;

    private Double rating;
    private Long createdAt;
    private Long updatedAt;

    @PrePersist
    public void prePersist() {
        long now = Instant.now().toEpochMilli();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now().toEpochMilli();
    }
}
