package com.portfolio.blogsstore.service;

import com.portfolio.blogsstore.domain.Article;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImageFile(Article article, MultipartFile file);
}
