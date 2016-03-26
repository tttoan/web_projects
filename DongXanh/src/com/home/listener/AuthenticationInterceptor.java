package com.home.listener;
import java.util.Map;

import com.home.conts.MyConts;
import com.home.entities.UserAware;
import com.home.model.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
 
public class AuthenticationInterceptor implements Interceptor {
	private static final long serialVersionUID = 1L;

	@Override
    public void destroy() {
        //release resources here
    }
 
    @Override
    public void init() {
        // create resources here
    }
 
    @Override
    public String intercept(ActionInvocation actionInvocation)
            throws Exception {
        Map<String, Object> sessionAttributes = actionInvocation.getInvocationContext().getSession();
        User user = (User) sessionAttributes.get(MyConts.LOGIN_SESSION);
        if(user == null){
            return Action.LOGIN;
        }else{
            Action action = (Action) actionInvocation.getAction();
            if(action instanceof UserAware){
                ((UserAware) action).setUserSes(user);
            }
            return actionInvocation.invoke();
        }
    }
 
}
