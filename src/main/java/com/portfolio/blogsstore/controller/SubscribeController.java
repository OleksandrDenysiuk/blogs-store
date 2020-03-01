package com.portfolio.blogsstore.controller;

import com.portfolio.blogsstore.domain.User;
import com.portfolio.blogsstore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class SubscribeController {

    private final UserService userService;

    public SubscribeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{userId}/subscribe")
    public String subscribe(@AuthenticationPrincipal User subscriber,
                            @PathVariable("userId") User user){

        if(!user.isSubscriber(subscriber)){
            userService.subscribe(subscriber,user);

            log.debug("user _id: " + subscriber.getId() + " subscribe to the user _id: " + user.getId());
            log.debug("user _id: " + subscriber.getId() + " subscribers count: " + user.getSubscribers().size());
        }
      return "redirect:/user/{userId}";
    }

    @GetMapping("/user/{userId}/unsubscribe")
    public String unsubscribe(@AuthenticationPrincipal User subscriber,
                            @PathVariable("userId") User user){
        if(user.isSubscriber(subscriber)) {
            userService.unsubscribe(subscriber, user);

            log.debug("user _id: " + subscriber.getId() + " subscribe to the user _id: " + user.getId());
            log.debug("user _id: " + subscriber.getId() + " subscribers count: " + user.getSubscribers().size());
        }
      return "redirect:/user/{userId}";
    }
}
