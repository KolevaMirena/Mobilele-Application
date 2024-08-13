package org.softuin.mobilele.service.impl;
import org.softuin.mobilele.model.dto.UserLoginDTO;
import org.softuin.mobilele.model.dto.UserRegistrationDTO;
import org.softuin.mobilele.model.entity.UserEntity;
import org.softuin.mobilele.repository.UserRepository;
import org.softuin.mobilele.service.UserService;
import org.softuin.mobilele.util.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser){

        this.userRepository= userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }
    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {

        userRepository.save(map(userRegistrationDTO));


    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {

        UserEntity userEntity = userRepository.findByEmail(userLoginDTO.getEmail())
                            .orElse(null);

        boolean loginSuccess = false;

        String rawPassword = userLoginDTO.getPassword();
        String encodedPassword = userEntity.getPassword();

         loginSuccess = encodedPassword != null && passwordEncoder.matches(rawPassword, encodedPassword);

         if(loginSuccess){
             currentUser.setLogged(true);
             currentUser.setFirstName(userEntity.getFirstName());
             currentUser.setLastName(userEntity.getLastName());
         }else {
             currentUser.logout();
         }

        return loginSuccess;
    }

    @Override
    public void logout() {

        currentUser.logout();

    }

    private  UserEntity map(UserRegistrationDTO userRegistrationDTO){

        UserEntity userEntity = new UserEntity();

        userEntity.setActive(true);
        userEntity.setFirstName(userRegistrationDTO.firstName());
        userEntity.setLastName(userRegistrationDTO.lastName());
        userEntity.setEmail(userRegistrationDTO.email());
        userEntity.setPassword(this.passwordEncoder.encode(userRegistrationDTO.password()));

        return userEntity;

    }
}
