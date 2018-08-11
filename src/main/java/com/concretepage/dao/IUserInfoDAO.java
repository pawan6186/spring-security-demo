package com.concretepage.dao;
import com.concretepage.entity.UserInfo;


// User authentication related information in database and access
public interface IUserInfoDAO {
	UserInfo getActiveUser(String userName);
}