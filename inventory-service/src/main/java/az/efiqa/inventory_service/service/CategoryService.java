package az.efiqa.inventory_service.service;

import az.efiqa.inventory_service.dto.ItemCategoryDTO;

import java.util.List;

public interface CategoryService {
    ItemCategoryDTO addNewCategory(ItemCategoryDTO itemCategoryDTO);

    List<ItemCategoryDTO> getAllCategories();

    ItemCategoryDTO getCategoryById(Long id);

    ItemCategoryDTO updateCategoryById(Long id, ItemCategoryDTO itemCategoryDTO);
    void deleteCategoryBtId(Long id);


}
