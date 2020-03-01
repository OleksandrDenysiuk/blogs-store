package com.portfolio.blogsstore.service;


import com.portfolio.blogsstore.domain.Article;
import com.portfolio.blogsstore.domain.User;

public interface LikeService {
    void like(User user, Article article);
}
