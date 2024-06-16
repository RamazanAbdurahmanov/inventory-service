package az.efiqa.inventory_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Item")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private ItemCategory category;

}
