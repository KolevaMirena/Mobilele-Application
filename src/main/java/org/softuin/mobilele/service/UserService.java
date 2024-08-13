package org.softuin.mobilele.service;

import org.softuin.mobilele.model.dto.UserLoginDTO;
import org.softuin.mobilele.model.dto.UserRegistrationDTO;

public interface UserService {

    void registerUser(UserRegistrationDTO userRegistrationDTO);

    boolean login(UserLoginDTO userLoginDTO);

    void logout();
}
