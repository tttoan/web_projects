package com.home.action;

import java.sql.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.home.dao.StatisticHome;
import com.home.model.Statistic;
import com.home.util.DateUtils;
import com.home.util.HibernateUtil;
import com.home.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class StatisticAction2  implements Action, ServletContextAware{
	private ServletContext ctx;
	private List<Statistic> data;
	private int recordsFiltered ;
	private int recordsTotal  ;
	private int draw;
	private String order;
	private String search;


	public String listStatisticJson() throws Exception {
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			// Fetch Data from User Table
			int statistic_type = 0;
			Date startDate1 = null;
			Date endDate1 = null;
			try {
				String startday = StringUtil.notNull(request.getParameter("startday"));
				String endday = StringUtil.notNull(request.getParameter("endday"));
				statistic_type = Integer.parseInt(StringUtil.notNull(request.getParameter("statistic_type")));
				if(startday.length() == 10 && endday.length() == 10){
					 startDate1 = new Date(DateUtils.getDateFromString(startday, "dd/MM/yyyy").getTime());
					 endDate1 = new Date(DateUtils.getDateFromString(endday, "dd/MM/yyyy").getTime());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			String start  = (request.getParameter("start"));
			String length  = (request.getParameter("length"));
			String strDraw  = (request.getParameter("draw"));
			draw = StringUtil.notNull(strDraw).isEmpty()?0:Integer.parseInt(strDraw);
			order = StringUtil.notNull((request.getParameter("order[i][dir]")));
			System.out.println("order = " + order);
			search = StringUtil.replaceInvalidChar(StringUtil.notNull(request.getParameter("search[value]")));
			System.out.println("search = " + search);

			int pageSize = length != null? Integer.parseInt(length) : 0;
			int skip = start != null ? Integer.parseInt(start) : 0;

			StatisticHome sttHome = new StatisticHome(HibernateUtil.getSessionFactory());
			data = sttHome.getListStatistic(skip, pageSize, search, startDate1, endDate1, statistic_type);
			// Get Total Record Count for Pagination
			recordsTotal = sttHome.getTotalRecords();
			recordsFiltered = recordsTotal;

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.ctx = context;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public List<Statistic> getData() {
		return data;
	}

	public void setData(List<Statistic> data) {
		this.data = data;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
