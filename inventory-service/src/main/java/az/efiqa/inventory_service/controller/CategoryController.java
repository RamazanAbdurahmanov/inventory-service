package az.efiqa.inventory_service.controller;

import az.efiqa.inventory_service.dto.ItemCategoryDTO;
import az.efiqa.inventory_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<ItemCategoryDTO> addNewCategory(@RequestBody ItemCategoryDTO itemCategoryDTO) {
        return new ResponseEntity<>(categoryService.addNewCategory(itemCategoryDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ItemCategoryDTO>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCategoryDTO> getOneCategory(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCategoryDTO> updateCategoryById(@PathVariable Long id, @RequestBody ItemCategoryDTO itemCategoryDTO) {
        return new ResponseEntity<>(categoryService.updateCategoryById(id, itemCategoryDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryBtId(id);
        return "Ugurla Silindi";
    }

}
