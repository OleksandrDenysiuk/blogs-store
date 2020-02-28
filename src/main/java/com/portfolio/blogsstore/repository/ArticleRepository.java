package com.portfolio.blogsstore.repository;

import com.portfolio.blogsstore.domain.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface ArticleRepository extends CrudRepository<Article, String> {
    Optional<Article> findById(String id);
}
