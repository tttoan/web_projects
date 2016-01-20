package com.home.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.home.dao.CustomerHome;
import com.home.dao.StatisticHome;
import com.home.model.Customer;
import com.home.model.Statistic;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class StatisticAction implements Action, ModelDriven<Statistic>, ServletContextAware {
	private Statistic statistic = new Statistic();
	public List<Statistic> statistics = new ArrayList<Statistic>();
	private ServletContext ctx;

	@Override
	public Statistic getModel() {
		return statistic;
	}

	@Override
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	public String listInvoice() throws Exception {
		try {
		
			SessionFactory sf = (SessionFactory) ctx
					.getAttribute("SessionFactory");
			StatisticHome sttHome = new StatisticHome(sf);
			statistics = sttHome.getListInvoice();
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}
	public String addInvoice() throws Exception {
		try {
			SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
			StatisticHome sttHome = new StatisticHome(sf);
			sttHome.attachDirty(statistic);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

}
