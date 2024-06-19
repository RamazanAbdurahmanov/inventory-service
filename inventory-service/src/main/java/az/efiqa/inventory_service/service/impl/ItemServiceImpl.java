package az.efiqa.inventory_service.service.impl;

import az.efiqa.inventory_service.dto.ItemDTO;
import az.efiqa.inventory_service.entity.Item;
import az.efiqa.inventory_service.exceptions.ItemAlreadyExistsException;
import az.efiqa.inventory_service.exceptions.ItemNotFoundException;
import az.efiqa.inventory_service.mapper.ItemMapper;
import az.efiqa.inventory_service.repository.ItemRepository;
import az.efiqa.inventory_service.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ModelMapper modelMapper, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }


    @Override
    public ItemDTO addNewItem(ItemDTO itemDTO) {
        Item newItem = itemMapper.mapToEntity(itemDTO);
        Optional<Item> existingItem = itemRepository.findByName(itemDTO.getName());
        if (existingItem.isPresent()) {
            throw new ItemAlreadyExistsException(itemDTO.getName() + "Bu adda esya artiq var");
        } else {
            return itemMapper.mapToDto(itemRepository.save(newItem));
        }
    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();
        if (items.isEmpty() == false) {
            List<ItemDTO> itemDTOS = new ArrayList<>();
            items.forEach(item -> itemDTOS.add(itemMapper.mapToDto(item)));
            return itemDTOS;

        }
        throw new ItemNotFoundException("Hec bir esya tapilmadi");
    }

    @Override
    public ItemDTO getItemById(Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            Item foundItem = optionalItem.get();
            return itemMapper.mapToDto(foundItem);
        }
        throw new ItemNotFoundException("id : " + id + " tapilmadi");
    }

    @Override
    public ItemDTO updateItemById(Long id, ItemDTO itemDTO) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            Item updatedItem = optionalItem.get();
            updatedItem.setName(itemDTO.getName());
            updatedItem.setDescription(itemDTO.getDescription());
            updatedItem.setQuantity(itemDTO.getQuantity());
            return itemMapper.mapToDto(updatedItem);
        }
        throw new ItemNotFoundException("Id : " + id + " tapilmadi");
    }

    @Override
    public void deleteItemById(Long id) {
        Optional<Item> deletedItem = itemRepository.findById(id);
        if (deletedItem.isPresent()) {
            itemRepository.deleteById(id);
        } else {
            throw new ItemNotFoundException("Id : " + id + " Tapilmadi");
        }
    }

}
