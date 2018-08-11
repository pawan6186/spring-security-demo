package com.concretepage.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.entity.UserInfo;

//stereotype for persistence layer
@Repository
//annotation is to make DAO methods transactional
@Transactional

public class UserInfoDAO implements IUserInfoDAO {
	// JPA API for database transaction and dependency injection 
	@PersistenceContext	
	private EntityManager entityManager;
	public UserInfo getActiveUser(String userName) {
		UserInfo activeUserInfo = new UserInfo();
		short enabled = 1;
		//Get userInformation using HQL with parameter
		List<?> list = entityManager.createQuery("SELECT u FROM UserInfo u WHERE userName= :name and enabled = :enable")
				.setParameter("name", userName).setParameter("enable", enabled)
				.getResultList();
		// Check user existance
		if(!list.isEmpty()) {
			activeUserInfo = (UserInfo)list.get(0);
		}
		return activeUserInfo;
	}
}