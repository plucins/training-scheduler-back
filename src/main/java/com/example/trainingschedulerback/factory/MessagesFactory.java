package com.example.trainingschedulerback.factory;

import com.example.trainingschedulerback.model.ServiceMessage;
import com.example.trainingschedulerback.model.dto.messages.MessageType;
import com.example.trainingschedulerback.model.dto.messages.ServiceMessages;

public class MessagesFactory {

    public static ServiceMessage message(ServiceMessages serviceMessages) {
        switch (serviceMessages) {
            case PASSWORD_NOT_MATCH_WITH_CONFIRM_PASSWORD:
                return new ServiceMessage("U0001", MessageType.ERROR, "Pointed password and confirm password didn't match");

            case EMAIL_ALREADY_IN_USE:
                return new ServiceMessage("U0002", MessageType.ERROR, "Pointed email is already in use");

            case POINTED_USER_NOT_PRESENT:
                return new ServiceMessage("U0003", MessageType.ERROR, "Pointed id of user is not present");

            case DELETED_CORRECTLY:
                return new ServiceMessage("U0004", MessageType.INFO, "User removed correctly");
        }

        return new ServiceMessage("X0", MessageType.ERROR, "UNKNOWN");
    }
}
