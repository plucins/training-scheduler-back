package com.example.trainingschedulerback.model.dto.user;

import com.example.trainingschedulerback.factory.MessagesFactory;
import com.example.trainingschedulerback.model.ServiceMessage;
import com.example.trainingschedulerback.model.User;
import com.example.trainingschedulerback.model.dto.messages.ServiceMessages;

import java.util.ArrayList;
import java.util.List;

public class UserWithNoPasswordDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<ServiceMessage> messages = new ArrayList<>();

    public UserWithNoPasswordDto() {
    }

    public UserWithNoPasswordDto(Long id, String firstName, String lastName, String email, List<ServiceMessage> messages) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.messages = messages;
    }

    public UserWithNoPasswordDto(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public static UserWithNoPasswordDto create(User user){
        return new UserWithNoPasswordDto(user.getId(),user.getFirstName(),user.getLastName(),user.getEmail());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ServiceMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ServiceMessage> messages) {
        this.messages = messages;
    }

    public void addMessage(ServiceMessages serviceMessages){
        this.messages.add(MessagesFactory.message(serviceMessages));
    }
}
