package com.example.article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.article.domain.Article;
import com.example.article.repository.ArticleRepository;

import lombok.NonNull;

@RestController
@RequestMapping("/articles")
public class ArticleController {
	
	private final ArticleRepository articleRepository;
	
	@Autowired
	public ArticleController(@NonNull ArticleRepository articleRepository) {
	    this.articleRepository = articleRepository;
	}
	
	@GetMapping
	public List<Article> getAll() {
	    return (List<Article>) articleRepository.findAll();
	}	

}
