package com.home.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.SessionFactory;

import com.home.dao.RoleHome;
import com.home.dao.UserHome;
import com.home.entities.UserAware;
import com.home.model.Role;
import com.home.model.User;
import com.home.util.HibernateUtil;
import com.home.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements Action, ModelDriven<User>, UserAware {
	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
	private static final long serialVersionUID = 1L;
	private boolean edit = false;
	private boolean update = false;
	private String varFullName;
	private String varUserName;
	public int userId;
	public int roleId;
	public User user = new User();
	public Role role = new Role();
	public List<User> listEmployee = new ArrayList<>();
	private List<Role> listRole = new ArrayList<>();
	private ServletContext ctx;
	public String genUserName = "sss";
	private User userSes;
	private String varbirthDate = SDF.format(new Date());
	public User getUserSes() {
		return userSes;
	}

	@Override
	public void setUserSes(User userSes) {
		this.userSes = userSes;
	}

	@Override
	public User getModel() {
		user = new User();
		user.setBirthDate(new Date());
		return user;
	}

	public SessionFactory getSessionFactory() {
		return HibernateUtil.getSessionFactory();
	}

	@Override
	public String execute() throws Exception {
		try {
			if (userId != 0) {
				UserHome userHome = new UserHome(getSessionFactory());
				user = userHome.findById(userId);
				varbirthDate = SDF.format(user.getBirthDate());
				setEdit(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}

	@Override
	public void validate() {
		try {
			findAllRole();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String testUser() throws Exception {
		try {
			System.out.println(getGenUserName());
			setGenUserName("ok men");
			System.out.println(genUserName);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	public String generateUserName() {
		try {
			UserHome userHome = new UserHome(getSessionFactory());
			varUserName = StringUtil.generateUserName(varFullName).toLowerCase();
			int count = userHome.countUserByUserName(varUserName);
			if (count > 0)
				varUserName += "_" + (count++);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	public String findAllRole() throws Exception {
		try {
			RoleHome roleHome = new RoleHome(getSessionFactory());
			listRole = roleHome.findAllRole();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
			return INPUT;
		}
		
	}

	public String listEmployee() throws Exception {
		try {
			UserHome userHome = new UserHome(getSessionFactory());
			listEmployee = userHome.getListUser();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return SUCCESS;
	}
	public String addEmployee1() {
		return SUCCESS;
	}
	public String addEmployee() {
		try {
			UserHome userHome = new UserHome(getSessionFactory());
			if (!edit) {
				int count = userHome.countUserByUserName(user.getUserName());
				if (count == 1)
					throw new Exception("Tên tài khoản đã tồn tại!");
			}
			user.setBirthDate(SDF.parse(varbirthDate));
			Role rl = new Role();
			rl.setRoleId(getRoleId());
			user.setRole(rl);
			if (user.getId() == 0) {
				userHome.attachDirty(user);
				addActionMessage("Tạo thành công");
				getModel();
			} else {
				userHome.updateDirty(user);
				return SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	public String deleteEmployeeById() throws Exception {
		try {
			UserHome userHome = new UserHome(getSessionFactory());
			User user = userHome.findById(getUserId());
			userHome.delete(user);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public int getUserId() {
		return userId;
	}

	public List<Role> getListRole() {
		return listRole;
	}

	public Role getRole() {
		return role;
	}

	public int getRoleId() {
		return roleId;
	}

	public ServletContext getCtx() {
		return ctx;
	}

	public void setCtx(ServletContext ctx) {
		this.ctx = ctx;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public String getGenUserName() {
		return genUserName;
	}

	public void setGenUserName(String genUserName) {
		this.genUserName = genUserName;
	}

	public String getVarFullName() {
		return varFullName;
	}

	public void setVarFullName(String varFullName) {
		this.varFullName = varFullName;
	}

	public String getVarUserName() {
		return varUserName;
	}

	public void setVarUserName(String varUserName) {
		this.varUserName = varUserName;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public String getVarbirthDate() {
		return varbirthDate;
	}

	public void setVarbirthDate(String varbirthDate) {
		this.varbirthDate = varbirthDate;
	}
}
