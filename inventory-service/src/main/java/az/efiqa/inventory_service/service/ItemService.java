package az.efiqa.inventory_service.service;

import az.efiqa.inventory_service.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    ItemDTO addNewItem(ItemDTO itemDTO);
    List<ItemDTO> getAllItems();
    ItemDTO getItemById(Long id);
    ItemDTO updateItemById(Long id,ItemDTO itemDTO);
    void deleteItemById(Long id);
}
