package com.home.action;
import org.hibernate.SessionFactory;
import com.home.dao.UserHome;
import com.home.entities.UserAware;
import com.home.model.User;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class RoleAction implements Action, ModelDriven<User>, UserAware {
	private User user = new User();
	private User userSes;

	@Override
	public User getModel() {
		return user;
	}
	public User getUserSes() {
		return userSes;
	}
	@Override
	public void setUserSes(User user) {
		this.userSes = user;
	}
	public SessionFactory getSessionFactory(){
		return HibernateUtil.getSessionFactory();
	}
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String addUser() throws Exception {
		try {
			UserHome userHome = new UserHome(getSessionFactory());
			userHome.attachDirty(user);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	

}
