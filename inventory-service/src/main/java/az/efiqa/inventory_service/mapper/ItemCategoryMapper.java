package az.efiqa.inventory_service.mapper;

import az.efiqa.inventory_service.dto.ItemCategoryDTO;
import az.efiqa.inventory_service.entity.ItemCategory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemCategoryMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public ItemCategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ItemCategoryDTO mapToDto(ItemCategory itemCategory) {
        return modelMapper.map(itemCategory, ItemCategoryDTO.class);
    }

    public ItemCategory mapToEntity(ItemCategoryDTO itemCategoryDTO) {
        return modelMapper.map(itemCategoryDTO, ItemCategory.class);


    }
}