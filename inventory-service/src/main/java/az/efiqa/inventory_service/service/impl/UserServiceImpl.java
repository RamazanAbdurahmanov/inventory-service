package az.efiqa.inventory_service.service.impl;

import az.efiqa.inventory_service.dto.UserDTO;
import az.efiqa.inventory_service.entity.User;
import az.efiqa.inventory_service.exceptions.UserAlreadyExistsException;
import az.efiqa.inventory_service.exceptions.UserNotFoundException;
import az.efiqa.inventory_service.mapper.UserMapper;
import az.efiqa.inventory_service.repository.UserRepository;
import az.efiqa.inventory_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO addNewUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        Optional<User> foundUser = userRepository.findByUsername(userDTO.getUsername());
        if (foundUser.isPresent()) {
            throw new UserAlreadyExistsException("Bele bir istifadeci artiq var ");
        } else {
            User newUser = foundUser.get();
            newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            return userMapper.toDto(newUser);
        }

    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("Hech bir istifadeci tapilmadi");
        }
        List<UserDTO> userDTOS = new ArrayList<>();
        users.forEach(user -> userDTOS.add(userMapper.toDto(user)));

        return userDTOS;
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User foundUser = optionalUser.get();
            return userMapper.toDto(foundUser);
        }
        throw new UserNotFoundException(id + " id-li User tyapilmadi");
    }

    @Override
    public UserDTO getUserByName(String name) {
        Optional<User> optionalUser = userRepository.findByUsername(name);
        if (optionalUser.isPresent()) {
            User foundUser = optionalUser.get();
            return userMapper.toDto(foundUser);
        }
        throw new UserNotFoundException(name + " bu adda User tapilmadi");
    }

    @Override
    public UserDTO updateUserById(Long id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User updatedUser = optionalUser.get();
            updatedUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            updatedUser.setRoles(userDTO.getRoles());
            updatedUser.setUsername(userDTO.getUsername());
            return userMapper.toDto(updatedUser);
        }
        throw new UserNotFoundException(id + " id-li User tapilmadi");
    }

    @Override
    public String deleteUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
        }
        throw new UserNotFoundException(id + " id-li User tapilmadi");
    }
}
