package com.portfolio.blogsstore.controller;

import com.portfolio.blogsstore.domain.User;
import com.portfolio.blogsstore.service.ArticleService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
The controller is responsible for showing main page.
 */
@Controller
public class IndexController {

    private final ArticleService articleService;

    public IndexController(ArticleService articleService) {
        this.articleService = articleService;
    }

    //show main page
    @GetMapping("/")
    public String show(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("articles",articleService.getAll());
        return "index";
    }
}
