package com.home.entities;

import com.home.model.User;
public interface UserAware {
	public int ROLE_ADMIN = 1;
	public int ROLE_LEADER = 3;
    public void setUserSes(User user);
}
