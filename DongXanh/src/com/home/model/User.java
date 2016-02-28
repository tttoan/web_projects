package com.home.model;

// Generated Feb 24, 2016 9:17:27 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {
	private Integer id;
	private Role role;
	private String userName;
	private String password;
	private String email;
	private String fullName;
	private byte[] picture;
	private Date birthDate;
	private String gender;
	private String mobilePhone;
	private String homePhone;
	private String address;
	private String passwordQuestion;
	private String passwordAnswer;
	private Short isAccountNonExpired;
	private Short isAccountNonLocked;
	private Date createdDate;
	private Date lastLoginDate;
	private Date lastLogoutDate;
	private Date lastPasswordChangedDate;
	private Integer failedPasswordCount;
	private String remarks;
	private Short isEnabled;
	private Set<Customer> customers = new HashSet<Customer>(0);
	private Set<WorkingPlan> workingPlans = new HashSet<WorkingPlan>(0);

	public User() {
	}

	public User(Role role, String userName, String password, String email, String fullName, byte[] picture, Date birthDate, String gender, String mobilePhone, String homePhone, String address,
			String passwordQuestion, String passwordAnswer, Short isAccountNonExpired, Short isAccountNonLocked, Date createdDate, Date lastLoginDate, Date lastLogoutDate,
			Date lastPasswordChangedDate, Integer failedPasswordCount, String remarks, Short isEnabled, Set<Customer> customers, Set<WorkingPlan> workingPlans) {
		this.role = role;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
		this.picture = picture;
		this.birthDate = birthDate;
		this.gender = gender;
		this.mobilePhone = mobilePhone;
		this.homePhone = homePhone;
		this.address = address;
		this.passwordQuestion = passwordQuestion;
		this.passwordAnswer = passwordAnswer;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.createdDate = createdDate;
		this.lastLoginDate = lastLoginDate;
		this.lastLogoutDate = lastLogoutDate;
		this.lastPasswordChangedDate = lastPasswordChangedDate;
		this.failedPasswordCount = failedPasswordCount;
		this.remarks = remarks;
		this.isEnabled = isEnabled;
		this.customers = customers;
		this.workingPlans = workingPlans;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public byte[] getPicture() {
		return this.picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPasswordQuestion() {
		return this.passwordQuestion;
	}

	public void setPasswordQuestion(String passwordQuestion) {
		this.passwordQuestion = passwordQuestion;
	}

	public String getPasswordAnswer() {
		return this.passwordAnswer;
	}

	public void setPasswordAnswer(String passwordAnswer) {
		this.passwordAnswer = passwordAnswer;
	}

	public Short getIsAccountNonExpired() {
		return this.isAccountNonExpired;
	}

	public void setIsAccountNonExpired(Short isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	public Short getIsAccountNonLocked() {
		return this.isAccountNonLocked;
	}

	public void setIsAccountNonLocked(Short isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getLastLogoutDate() {
		return this.lastLogoutDate;
	}

	public void setLastLogoutDate(Date lastLogoutDate) {
		this.lastLogoutDate = lastLogoutDate;
	}

	public Date getLastPasswordChangedDate() {
		return this.lastPasswordChangedDate;
	}

	public void setLastPasswordChangedDate(Date lastPasswordChangedDate) {
		this.lastPasswordChangedDate = lastPasswordChangedDate;
	}

	public Integer getFailedPasswordCount() {
		return this.failedPasswordCount;
	}

	public void setFailedPasswordCount(Integer failedPasswordCount) {
		this.failedPasswordCount = failedPasswordCount;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Short getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(Short isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Set<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	public Set<WorkingPlan> getWorkingPlans() {
		return this.workingPlans;
	}

	public void setWorkingPlans(Set<WorkingPlan> workingPlans) {
		this.workingPlans = workingPlans;
	}

}
