package org.example.bootdiary.service;

import org.example.bootdiary.model.entity.Article;
import org.example.bootdiary.model.form.ArticleForm;

import java.util.List;

// 타입
public interface ArticleService {
    List<Article> findAll();

//    void save(Article article) throws Exception;
    void save(ArticleForm form, String filename) throws Exception;
}
