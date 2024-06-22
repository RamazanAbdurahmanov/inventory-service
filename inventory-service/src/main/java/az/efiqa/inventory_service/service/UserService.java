package az.efiqa.inventory_service.service;

import az.efiqa.inventory_service.dto.UserDTO;

import java.util.List;
public interface UserService {
    UserDTO addNewUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO getUserByName(String name);
    UserDTO updateUserById(Long id,UserDTO userDTO);
    String deleteUserById(Long id);


}
