package com.example.article.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.article.domain.Article;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> { 

}
