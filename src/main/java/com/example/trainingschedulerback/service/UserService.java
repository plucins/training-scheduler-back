package com.example.trainingschedulerback.service;

import com.example.trainingschedulerback.model.User;
import com.example.trainingschedulerback.model.dto.messages.ServiceMessages;
import com.example.trainingschedulerback.model.dto.user.RegisterUserDto;
import com.example.trainingschedulerback.model.dto.user.UserWithNoPasswordDto;
import com.example.trainingschedulerback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserWithNoPasswordDto> registerUser(RegisterUserDto dto) {

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            UserWithNoPasswordDto user = new UserWithNoPasswordDto();
            user.addMessage(ServiceMessages.PASSWORD_NOT_MATCH_WITH_CONFIRM_PASSWORD);
            return Optional.of(user);
        }

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            UserWithNoPasswordDto user = new UserWithNoPasswordDto();
            user.addMessage(ServiceMessages.EMAIL_ALREADY_IN_USE);
            return Optional.of(user);
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return Optional.of(UserWithNoPasswordDto.create(userRepository.save(user)));

    }

    public List<UserWithNoPasswordDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserWithNoPasswordDto::create)
                .collect(Collectors.toList());
    }

    public Optional<UserWithNoPasswordDto> getUserById(Long id) {

        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            UserWithNoPasswordDto user = new UserWithNoPasswordDto();
            user.addMessage(ServiceMessages.POINTED_USER_NOT_PRESENT);
            return Optional.of(user);
        }

        return Optional.of(UserWithNoPasswordDto.create(userOptional.get()));
    }

    public Optional<UserWithNoPasswordDto> removeUserById(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()){
            UserWithNoPasswordDto user = UserWithNoPasswordDto.create(optionalUser.get());
            user.addMessage(ServiceMessages.DELETED_CORRECTLY);

            userRepository.deleteById(id);
            return Optional.of(user);
        }

        UserWithNoPasswordDto user = new UserWithNoPasswordDto();
        user.addMessage(ServiceMessages.POINTED_USER_NOT_PRESENT);

        return Optional.of(user);
    }

    public Optional<UserWithNoPasswordDto> updateUser(UserWithNoPasswordDto dto, Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();

            if(dto.getFirstName() != null){
                user.setFirstName(dto.getFirstName());
            }
            if(dto.getLastName() != null){
                user.setLastName(dto.getLastName());
            }
            if(dto.getEmail() != null){
                user.setEmail(dto.getEmail());
            }

            userRepository.save(user);

            return Optional.of(UserWithNoPasswordDto.create(user));
        }

        UserWithNoPasswordDto user = new UserWithNoPasswordDto();
        user.addMessage(ServiceMessages.POINTED_USER_NOT_PRESENT);

        return Optional.of(user);
    }
}
