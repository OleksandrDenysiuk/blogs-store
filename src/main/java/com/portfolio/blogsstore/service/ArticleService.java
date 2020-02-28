package com.portfolio.blogsstore.service;

import com.portfolio.blogsstore.domain.Article;

import java.util.List;

public interface ArticleService {
    Article save(Article article);

    void delete(Article article);

    List<Article> getAll();
}
