package az.efiqa.inventory_service.mapper;

import az.efiqa.inventory_service.dto.ItemCategoryDTO;
import az.efiqa.inventory_service.entity.ItemCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ItemCategoryMapper {
    ItemCategoryMapper INSTANCE = Mappers.getMapper(ItemCategoryMapper.class);

    @Mapping(source = "items", target = "itemIds")
    ItemCategoryDTO itemCategoryToItemCategoryDTO(ItemCategory itemCategory);

    @Mapping(source ="categories",target = "categoriesIds")
    ItemCategory itemCategoryDtoToItemCategory(ItemCategoryDTO itemCategoryDTO);
}
