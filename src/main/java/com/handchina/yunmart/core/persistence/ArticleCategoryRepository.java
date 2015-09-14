package com.handchina.yunmart.core.persistence;

import com.handchina.yunmart.core.domain.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by markfredchen on 9/12/15.
 */
public interface ArticleCategoryRepository extends JpaRepository<Article, Long> {
}
