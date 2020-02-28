package com.portfolio.blogsstore.controller;

import com.portfolio.blogsstore.domain.User;
import com.portfolio.blogsstore.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public String showUser(@AuthenticationPrincipal User user,
                           Model model){

        model.addAttribute("user", user);
        return "user/index";
    }
}
