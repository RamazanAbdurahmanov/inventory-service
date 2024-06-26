package az.efiqa.inventory_service.service.impl;

import az.efiqa.inventory_service.dto.ItemCategoryDTO;
import az.efiqa.inventory_service.entity.ItemCategory;
import az.efiqa.inventory_service.exceptions.CategoryAlreadyExistsException;
import az.efiqa.inventory_service.exceptions.CategoryNotFoundException;
import az.efiqa.inventory_service.mapper.ItemCategoryMapper;
import az.efiqa.inventory_service.repository.CategoryRepository;
import az.efiqa.inventory_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final ItemCategoryMapper itemCategoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ItemCategoryMapper itemCategoryMapper) {
        this.categoryRepository = categoryRepository;
        this.itemCategoryMapper = itemCategoryMapper;
    }

    @Override
    public ItemCategoryDTO addNewCategory(ItemCategoryDTO itemCategoryDTO) {
        ItemCategory itemCategory = itemCategoryMapper.mapToEntity(itemCategoryDTO);
        Optional<ItemCategory> existingCategory = categoryRepository.findByName(itemCategoryDTO.getName());
        if (existingCategory.isPresent()) {
            throw new CategoryAlreadyExistsException(itemCategoryDTO.getName() + " adli kategoriya artiq bazada movcuddur.");
        } else {
            ItemCategory savedCategory = categoryRepository.save(itemCategory);
            return itemCategoryMapper.mapToDto(savedCategory);
        }
    }

    @Override
    public List<ItemCategoryDTO> getAllCategories() {
        List<ItemCategory> itemCategories = categoryRepository.findAll();
        if (itemCategories.isEmpty() == false) {
            List<ItemCategoryDTO> itemCategoryDTOS = new ArrayList<>();
            itemCategories.forEach(itemCategory -> itemCategoryDTOS.add(itemCategoryMapper.mapToDto(itemCategory)));
            return itemCategoryDTOS;
        }
        throw new CategoryNotFoundException("Hec bir kategoriya tapilmadi");
    }

    @Override
    public ItemCategoryDTO getCategoryById(Long id) {
        Optional<ItemCategory> optionalItemCategory = categoryRepository.findById(id);
        if (optionalItemCategory.isPresent()) {
            return itemCategoryMapper.mapToDto(optionalItemCategory.get());
        }
        throw new CategoryNotFoundException(id + " bu idili category tapilmadi");
    }

    @Override
    public ItemCategoryDTO updateCategoryById(Long id, ItemCategoryDTO itemCategoryDTO) {
        Optional<ItemCategory> optionalItemCategory = categoryRepository.findById(id);
        if (optionalItemCategory.isPresent()) {
            ItemCategory updatedItemCategory = optionalItemCategory.get();
            updatedItemCategory.setName(itemCategoryDTO.getName());
            updatedItemCategory.setDescription(itemCategoryDTO.getDescription());
            categoryRepository.save(updatedItemCategory);
            return itemCategoryMapper.mapToDto(updatedItemCategory);
        }
        throw new CategoryNotFoundException(id + " Bu id-li categoriya bazada yoxdur");
    }

    @Override
    public void deleteCategoryBtId(Long id) {
        Optional<ItemCategory> deletedItemCategory = categoryRepository.findById(id);
        if (deletedItemCategory.isPresent()) {
            categoryRepository.deleteById(id);
        }
        throw new CategoryNotFoundException(id + " bu id-li kategoriya bazada yoxdur");
    }
}
