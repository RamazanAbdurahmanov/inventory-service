package az.efiqa.inventory_service.mapper;

import az.efiqa.inventory_service.dto.ItemDTO;
import az.efiqa.inventory_service.entity.Item;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);
    @Mapping(source = "category.id", target = "categoryId")
    ItemDTO itemToItemDTO(Item item);

    @Mapping(target = "category", ignore = true)
    Item itemDTOToItem(ItemDTO itemDTO);
}
