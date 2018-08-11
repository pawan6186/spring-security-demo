package com.concretepage.dao;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.entity.Article;
@Transactional
@Repository

public class ArticleDAO implements IArticleDAO {
	// JPA API for database transaction and dependency injection 
	@PersistenceContext	
	private EntityManager entityManager;	
	@Override
	/**
     * Find by primary key, using the specified properties.
     * Search for an entity of the specified class and primary key.
     **/
	public Article getArticleById(int articleId) {
		return entityManager.find(Article.class, articleId);
	}
	@SuppressWarnings("unchecked")
	@Override
	/**
     * Create an instance of Query for executing a JPQL statement.
     */
	public List<Article> getAllArticles() {
		String hql = "FROM Article as atcl ORDER BY atcl.articleId";
		return (List<Article>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	/**
     * Managed and persistent.Entity  entity instance
     * if the entity already exists.
     * Must be called within Transaction boundary
     * Synchronize the persistence context to the underlying database
     */
	public void addArticle(Article article) {
		entityManager.merge(article);
	}
	@Override
	 /**
     * Synchronize the persistence context to the underlying database.
     * */
	public void updateArticle(Article article) {
		Article artcl = getArticleById(article.getArticleId());
		artcl.setTitle(article.getTitle());
		artcl.setCategory(article.getCategory());
		entityManager.flush();
	}
	@Override
	/**
     * Remove the entity instance.
     */
	public void deleteArticle(int articleId) {
		entityManager.remove(getArticleById(articleId));
	}
	@Override
	/**
     * Create an instance of Query for executing a JPQL statement.
     */
	public boolean articleExists(String title, String category) {
		String hql = "FROM Article as atcl WHERE atcl.title = :title and atcl.category = :cat";
		int count = entityManager.createQuery(hql).setParameter("title", title)
		              .setParameter("cat", category).getResultList().size();
		return count > 0 ? true : false;
	}
	public int getId() {
		String hql = "select MAX(atcl.articleId) from Article atcl";		
		int count = 0;
		List<?> list = entityManager.createQuery(hql)				
				.getResultList();
		if(!list.isEmpty()) {
			count = (int) list.get(0);
		}
		return count;		
		
	}
}
