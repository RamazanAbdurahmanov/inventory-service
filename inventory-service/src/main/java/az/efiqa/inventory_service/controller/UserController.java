package az.efiqa.inventory_service.controller;

import az.efiqa.inventory_service.dto.UserDTO;
import az.efiqa.inventory_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> registerNewUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.addNewUser(userDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

//    @GetMapping("/{username}")
//    private ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
//        return new ResponseEntity<>(userService.getUserByName(username), HttpStatus.OK);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateUserById(id, userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "Ugurla Silindi!";
    }


}
