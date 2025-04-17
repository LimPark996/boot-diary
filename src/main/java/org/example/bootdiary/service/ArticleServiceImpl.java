package org.example.bootdiary.service;

import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.example.bootdiary.model.entity.Article;
import org.example.bootdiary.model.form.ArticleForm;
import org.example.bootdiary.model.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Component
@Service // Spring
@Transactional
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public void save(ArticleForm article, String filename) throws Exception {
        if (article.title().isEmpty()) throw new BadRequestException("제목 없음");
        if (article.content().isEmpty()) throw new BadRequestException("내용 없음");
        Article articleEntity = new Article();
        articleEntity.setTitle(article.title());
        articleEntity.setContent(article.content());
        articleEntity.setFilename(filename);
        articleRepository.save(articleEntity);
    }
}
