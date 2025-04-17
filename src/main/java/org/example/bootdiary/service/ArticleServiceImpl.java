package org.example.bootdiary.service;

import org.example.bootdiary.model.entity.Article;
import org.example.bootdiary.model.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Component
@Service // Spring
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
