package com.handchina.yunmart.core.persistence.article;

import com.handchina.yunmart.core.domain.article.Article;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by markfredchen on 9/12/15.
 */
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {
}
