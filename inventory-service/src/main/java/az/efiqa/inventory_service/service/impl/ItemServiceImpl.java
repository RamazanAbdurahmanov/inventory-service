package az.efiqa.inventory_service.service.impl;

import az.efiqa.inventory_service.dto.ItemDTO;
import az.efiqa.inventory_service.entity.Item;
import az.efiqa.inventory_service.mapper.ItemMapper;
import az.efiqa.inventory_service.repository.ItemRepository;
import az.efiqa.inventory_service.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private final ItemRepository itemRepository;


    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @Override
    public ItemDTO addNewItem(ItemDTO itemDTO) {
        Item newItem = ItemMapper.INSTANCE.itemDTOToItem(itemDTO);
        Optional<Item> existingItem = itemRepository.findByName(itemDTO.getName());
        if (existingItem.isEmpty()) {
            newItem = itemRepository.save(newItem);
        }
        return ItemMapper.INSTANCE.itemToItemDTO(newItem);
    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();
        if (items.isEmpty() == false) {
            List<ItemDTO> itemDTOS = new ArrayList<>();
            items.forEach(item -> itemDTOS.add(ItemMapper.INSTANCE.itemToItemDTO(item)));
            return itemDTOS;

        }
        throw new RuntimeException();
    }

    @Override
    public ItemDTO getItemById(Long id) {
        Optional<Item>  optionalItem =itemRepository.findById(id);
        if(optionalItem.isPresent()){
            Item foundItem=optionalItem.get();
            return ItemMapper.INSTANCE.itemToItemDTO(foundItem);
        }
        return null;
    }

    @Override
    public ItemDTO updateItemById(Long id, ItemDTO itemDTO) {
        Optional<Item> optionalItem=itemRepository.findById( id);
        if(optionalItem.isPresent()){
            Item updatedItem=optionalItem.get();
            updatedItem.setName(itemDTO.getName());
            updatedItem.setDescription(itemDTO.getDescription());
            updatedItem.setQuantity(itemDTO.getQuantity());
            return ItemMapper.INSTANCE.itemToItemDTO(updatedItem);
        }
       return null;
    }

    @Override
    public void deleteItemById(Long id) {
        Optional<Item> deletedItem=itemRepository.findById(id);
        if (deletedItem.isPresent()){
            itemRepository.deleteById(id);
        }
        else {
            throw new RuntimeException();
        }
    }

}
