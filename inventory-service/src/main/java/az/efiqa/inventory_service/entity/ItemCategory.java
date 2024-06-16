package az.efiqa.inventory_service.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
public class ItemCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Item> items;

}
