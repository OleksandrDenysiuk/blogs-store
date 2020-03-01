package com.portfolio.blogsstore.controller;

import com.portfolio.blogsstore.domain.Article;
import com.portfolio.blogsstore.domain.User;
import com.portfolio.blogsstore.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }


    @GetMapping("article/{articleId}/like")
    public String like(@AuthenticationPrincipal User user,
                       @PathVariable("articleId") Article article){

        if(!article.isLikedUser(user)){
            likeService.like(user,article);
        }

        log.debug("user _id: " + user.getId() + " liked article _id: " + article.getId());

        return "redirect:/article/read/" + article.getId();
    }
}
