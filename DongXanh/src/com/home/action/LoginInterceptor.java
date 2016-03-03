package com.home.action;

import java.util.Map;

import com.home.conts.MyConts;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor  extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext().getSession();

		String loginId = (String) session.get(MyConts.LOGIN_SESSION);

		if (loginId == null) {
			return Action.LOGIN;
		} 
		else {
			return invocation.invoke();
		}
	}
}