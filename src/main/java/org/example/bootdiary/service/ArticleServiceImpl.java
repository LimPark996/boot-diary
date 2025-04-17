package org.example.bootdiary.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.bootdiary.model.entity.Article;
import org.example.bootdiary.model.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Component
@Service // Spring
@Transactional
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
