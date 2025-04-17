package org.example.bootdiary.model.repository;

import org.example.bootdiary.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Spring
// 일반적으로는 Long(long -> seq(++)을 쓰지만... uuid)
public interface ArticleRepository extends JpaRepository<Article, String> {
}
