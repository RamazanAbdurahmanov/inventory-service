package az.efiqa.inventory_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class ItemCategoryDTO {
    private Long id;
    private String name;
    private String description;
    private List<Long> itemIds;
}
