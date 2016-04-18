package com.home.action;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.home.entities.RevenuesComparison;
import com.opensymphony.xwork2.Action;

public class ReportRevenuesAction  implements Action, ServletContextAware{
	private ServletContext ctx;
	private List<RevenuesComparison> revenuesComparisons;

	@Override
	public void setServletContext(ServletContext context) {
		this.ctx = context;
	}

	@Override
	public String execute() throws Exception {
		return Action.SUCCESS;
	}
	
	public String compareRevenues(){
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}

	
	public List<RevenuesComparison> getRevenuesComparisons() {
		return revenuesComparisons;
	}

	public void setRevenuesComparisons(List<RevenuesComparison> revenuesComparisons) {
		this.revenuesComparisons = revenuesComparisons;
	}
}
