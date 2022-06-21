package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import user.service.UserService;
import user.service.model.User;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/register")
    public void register(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        userService.save(user);
    }

    @GetMapping("/test")
    public String test() {
        return "/test is accessed";
    }
}
