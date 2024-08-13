package org.softuin.mobilele.web;


import org.softuin.mobilele.model.dto.UserLoginDTO;
import org.softuin.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {


    private final UserService userService;
    public UserLoginController(UserService userService, UserService userService1){

        this.userService = userService1;
    }


    @GetMapping("/users/login")
    public String login(){

        return "auth-login";

    }

    @PostMapping("/users/login")
    public String login(UserLoginDTO userLoginDTO){
        boolean loginSuccessful = this.userService.login(userLoginDTO);

        return loginSuccessful ? "index" : "auth-login";

    }

    @GetMapping("/users/logout")
    public String logout(){

        userService.logout();
        return "index";

    }
}
