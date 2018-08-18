package com.example.trainingschedulerback.controller;

import com.example.trainingschedulerback.model.dto.user.RegisterUserDto;
import com.example.trainingschedulerback.model.dto.user.UserWithNoPasswordDto;
import com.example.trainingschedulerback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserWithNoPasswordDto> registerUser(@RequestBody RegisterUserDto dto) {

        Optional<UserWithNoPasswordDto> userOptional = userService.registerUser(dto);
        if (userOptional.isPresent()) {
            if (!userOptional.get().getMessages().isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(userOptional.get());
            }

            return ResponseEntity.ok(userOptional.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserWithNoPasswordDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserWithNoPasswordDto> getUserById(@PathVariable Long id) {
        Optional<UserWithNoPasswordDto> userByIdOptional = userService.getUserById(id);

        if (userByIdOptional.isPresent()) {
            UserWithNoPasswordDto user = userByIdOptional.get();

            if (!user.getMessages().isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(user);
            }

            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserWithNoPasswordDto> removeUserById(@PathVariable Long id) {
        Optional<UserWithNoPasswordDto> userOptional = userService.removeUserById(id);

        if (userOptional.isPresent()) {
            if (userOptional.get().getEmail() == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(userOptional.get());
            }

            return ResponseEntity.ok(userOptional.get());
        }
        return ResponseEntity.badRequest().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserWithNoPasswordDto> updateUser(@RequestBody UserWithNoPasswordDto dto, @PathVariable Long id){
        Optional<UserWithNoPasswordDto> optionalUser = userService.updateUser(dto, id);

        if(optionalUser.isPresent()){
            UserWithNoPasswordDto user = optionalUser.get();
            if (user.getEmail() == null){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(user);
            }

            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }
}
