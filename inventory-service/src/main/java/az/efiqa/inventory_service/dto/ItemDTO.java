package az.efiqa.inventory_service.dto;

import lombok.Data;

@Data
public class ItemDTO {
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private Long categoryId;
}
