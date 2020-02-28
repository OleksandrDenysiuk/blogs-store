package com.portfolio.blogsstore.service;

import com.portfolio.blogsstore.domain.Article;
import com.portfolio.blogsstore.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article save(Article article) {
        article.setDate(new Date());
        return articleRepository.save(article);
    }


    @Override
    public void delete(Article article) {
        articleRepository.delete(article);
    }

    @Override
    public List<Article> getAll() {
        return (List<Article>) articleRepository.findAll();
    }
}
