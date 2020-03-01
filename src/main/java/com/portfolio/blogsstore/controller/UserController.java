package com.portfolio.blogsstore.controller;

import com.portfolio.blogsstore.domain.User;
import com.portfolio.blogsstore.service.ImageService;
import com.portfolio.blogsstore.service.UserService;
import com.portfolio.blogsstore.validator.UserProfileValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;


@Slf4j
@Controller
public class UserController {

    private final UserService userService;
    private final UserProfileValidator userProfileValidator;
    private final ImageService imageService;

    public UserController(UserService userService, UserProfileValidator userProfileValidator, ImageService imageService) {
        this.userService = userService;
        this.userProfileValidator = userProfileValidator;
        this.imageService = imageService;
    }

    @GetMapping("/user/{userId}")
    public String showUser(@AuthenticationPrincipal User user,
                           @PathVariable("userId") User userProfile,
                           Model model) {

        model.addAttribute("user", user);
        model.addAttribute("userProfile", userProfile);
        return "user/index";
    }

    @GetMapping("/user")
    public String showMyProfile(@AuthenticationPrincipal User user,
                                Model model) {

        model.addAttribute("userForm", user);
        return "user/form";
    }

    @PostMapping("/user/edit")
    public String editProfile(@AuthenticationPrincipal User user,
                              @ModelAttribute("userForm") @Valid User form,
                              BindingResult result,
                              Model model) {
        userProfileValidator.validate(form, result);

        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            model.addAttribute("user", user);
            return "user/form";
        }

        userService.updateProfile(user, form);
        return "redirect:/user";
    }

    @PostMapping("/user/image")
    public String setImage(@AuthenticationPrincipal User user,
                           @RequestParam("image") MultipartFile image){
        imageService.saveImageFile(user,image);
        log.debug("user _id: " + user.getId() + " set new image");
        return "redirect:/user";
    }
}
