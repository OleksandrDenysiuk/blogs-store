package com.portfolio.blogsstore.service;

import com.portfolio.blogsstore.domain.Article;
import com.portfolio.blogsstore.domain.User;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImageFile(Article article, MultipartFile file);
    void saveImageFile(User user, MultipartFile file);
}
