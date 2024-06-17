package az.efiqa.inventory_service.controller;

import az.efiqa.inventory_service.dto.ItemDTO;
import az.efiqa.inventory_service.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("items")
public class ItemController {
    @Autowired
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemDTO> addNewItem(@RequestBody ItemDTO itemDTO) {
        return new ResponseEntity<>(itemService.addNewItem(itemDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getOneProduct(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(itemService.getItemById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> updateItemById(@PathVariable Long id, @RequestBody ItemDTO itemDTO) {
        return new ResponseEntity<>(itemService.updateItemById(id, itemDTO), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public String deleteItemById(@PathVariable Long id) {
        itemService.deleteItemById(id);
        return "Ugurla Silindi";
    }


}
