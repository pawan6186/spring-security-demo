package com.concretepage.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.concretepage.entity.Article;

// Create service methods for CRUD operation with secured methods
public interface IArticleService {
	/*
	 Enable method level security
	 All the methods can be accessed by the user with the role ADMIN. 
	 User with the role USER can access only getAllArticles() and getArticleById()
	 service methods
	 */
	 @Secured ({"ROLE_ADMIN", "ROLE_USER"})
     List<Article> getAllArticles();
	 @Secured ({"ROLE_ADMIN", "ROLE_USER"})
     Article getArticleById(int articleId);
	 @Secured ({"ROLE_ADMIN"})
     boolean addArticle(Article article);
	 @Secured ({"ROLE_ADMIN"})
     void updateArticle(Article article);
	 @Secured ({"ROLE_ADMIN"})
     void deleteArticle(int articleId);
}
