package com.portfolio.blogsstore.service;

import com.portfolio.blogsstore.domain.Article;
import com.portfolio.blogsstore.domain.User;
import com.portfolio.blogsstore.repository.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService{

    private final ArticleRepository articleRepository;

    public LikeServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void like(User user, Article article) {
        article.getLikedUsersId().add(user.getId());
        articleRepository.save(article);
    }
}
