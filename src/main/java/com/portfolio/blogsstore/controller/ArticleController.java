package com.portfolio.blogsstore.controller;

import com.portfolio.blogsstore.domain.Article;
import com.portfolio.blogsstore.domain.User;
import com.portfolio.blogsstore.service.ArticleService;
import com.portfolio.blogsstore.service.ImageService;
import com.portfolio.blogsstore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;
    private final ImageService imageService;

    public ArticleController(ArticleService articleService, UserService userService, ImageService imageService) {
        this.articleService = articleService;
        this.userService = userService;
        this.imageService = imageService;
    }

    @GetMapping("/user_articles")
    public String list(@AuthenticationPrincipal User user,
                       Model model) {
        model.addAttribute("user", user);
        return "article/list";
    }


    @GetMapping("/article/create")
    public String showForm(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("article", new Article());
        model.addAttribute("user", user);
        return "article/form";
    }

    @PostMapping("/article/create")
    public String create(@AuthenticationPrincipal User user,
                         @ModelAttribute("article") Article article,
                         @RequestParam("file") MultipartFile image,
                         BindingResult result) {
        //todo:validate article form
        imageService.saveImageFile(article,image);
        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            return "article/form";
        }
        user.getArticles().add(article);
        article.setAuthor(user);
        articleService.save(article);
        userService.update(user);
        return "redirect:/user_articles";
    }

    @GetMapping("article/read/{articleId}")
    public String showContent(@AuthenticationPrincipal User user,
                              @PathVariable("articleId") Article article,
                              Model model) {
        model.addAttribute("user",user);
        model.addAttribute("article", article);
        return "article/index";
    }

    @GetMapping("article/delete/{articleId}")
    public String delete(@AuthenticationPrincipal User user,
                         @PathVariable("articleId") Article article) {
        Set<Article> userArticles = new HashSet<>(user.getArticles());
        userArticles.remove(article);
        user.setArticles(userArticles);
        userService.update(user);
        articleService.delete(article);

        return "redirect:/user_articles";
    }


}
