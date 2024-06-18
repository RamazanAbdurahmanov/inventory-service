package az.efiqa.inventory_service.mapper;

import az.efiqa.inventory_service.dto.ItemDTO;
import az.efiqa.inventory_service.entity.Item;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public ItemMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public ItemDTO mapToDto(Item item) {
        return modelMapper.map(item, ItemDTO.class);
    }

    public Item mapToEntity(ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, Item.class);
    }


}
