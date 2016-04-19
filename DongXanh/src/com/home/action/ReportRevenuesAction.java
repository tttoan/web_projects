package com.home.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.home.dao.StatisticHome;
import com.home.entities.RevenuesComparison;
import com.home.util.DateUtils;
import com.home.util.HibernateUtil;
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
			StatisticHome statisticHome = new StatisticHome(HibernateUtil.getSessionFactory());
			Date startDate1 = new Date(DateUtils.getDateFromString("18/04/2016", "dd/MM/yyyy").getTime());
			Date endDate1 = new Date(DateUtils.getDateFromString("18/04/2016", "dd/MM/yyyy").getTime());
			
			Calendar cal = Calendar.getInstance();
		    cal.setTime(startDate1);
		    int year = cal.get(Calendar.YEAR)-1;
		    cal.set(Calendar.YEAR, year);
			Date startDate2 = new Date(cal.getTimeInMillis());
			
			cal.setTime(endDate1);
		    year = cal.get(Calendar.YEAR)-1;
		    cal.set(Calendar.YEAR, year);
			Date endDate2 = new Date(cal.getTimeInMillis());
			float minRevenues = 1*1000000;
			revenuesComparisons = new ArrayList<RevenuesComparison>();
			
			LinkedHashMap<String, RevenuesComparison> results1 = statisticHome.getRevenuesComparison(startDate1, endDate1, 0);
			LinkedHashMap<String, RevenuesComparison> results2 = statisticHome.getRevenuesComparison(startDate2, endDate2, 0);
			
			Set<String> set = results1.keySet();
			for (String customerCode : set) {
				RevenuesComparison revenues = results1.get(customerCode);
				if(results2.containsKey(customerCode)){
					RevenuesComparison revenues2 = results2.get(customerCode);
					
					if(revenues.getRevenues1().floatValue() >= minRevenues || 
							revenues2.getRevenues1().floatValue() >= minRevenues){
						
						revenues.setRevenues2(revenues2.getRevenues1());
						String arrProvider[] = revenues2.getProvider().split(";");
						for (String provider : arrProvider) {
							if(provider.length() > 0 && !revenues.getProvider().contains(provider)){
								revenues.setProvider(revenues.getProvider() + ";" + provider);
							}
						}
						String arrSellMan[] = revenues2.getSellMan().split(";");
						for (String sellMan : arrSellMan) {
							if(sellMan.length() > 0 && !revenues.getSellMan().contains(sellMan)){
								revenues.setSellMan(revenues.getSellMan() + ";" + sellMan);
							}
						}
						float r = revenues.getRevenues1().floatValue()-revenues.getRevenues2().floatValue();
						if(r > 0 && ((r*100)/revenues.getRevenues2().floatValue()) >= 30){
							revenues.setIncrease30(true);
						}
						else if(r < 0 && ((-r*100)/revenues.getRevenues2().floatValue()) >= 30){
							revenues.setDecrease30(true);
						}
						if(revenues.getProvider().split(";").length > 1){
							revenues.setMultiProvide(true);
						}
						revenuesComparisons.add(revenues);
					}
					
				}else{
					if(revenues.getRevenues1().floatValue() >= minRevenues){
						if(revenues.getProvider().split(";").length > 1){
							revenues.setMultiProvide(true);
						}
						revenues.setNotBuy(true);
						revenuesComparisons.add(revenues);
					}
				}
			}
			
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
