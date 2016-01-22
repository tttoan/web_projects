package com.home.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.Session;

import com.home.dao.PromotionHome;
import com.home.model.Promotion;
import com.home.util.HibernateUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 
 * @author USER
 *
 */
public class PromotionAction implements Action, ModelDriven<Promotion>, ServletContextAware{

	private ServletContext ctx;
	private Promotion promotion = new Promotion();
	public List<Promotion> listPromotions = new ArrayList<Promotion>();
	
	public static void main(String[] args) {
		try {
			PromotionAction promotion = new PromotionAction();
			promotion.addPromotion();
//			 System.out.println("Maven + Hibernate + MySQL");
//		        Session session = HibernateUtil.getSessionFactory().openSession();
//		        session.beginTransaction();
//		        
//		        Promotion promotion = new Promotion();
//		    	promotion.setPromotionName("Sale Off 2016");
//				promotion.setStatus(true);
//				promotion.setStartDate(new Date());
//				promotion.setEndDate(new Date());
//				promotion.setRemarks("Remart");
//				
//		        session.save(promotion);
//		        session.getTransaction().commit();
//		        session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	@Override
	public void setServletContext(ServletContext ctx) {
		this.ctx = ctx;
	}

	@Override
	public Promotion getModel() {
		// TODO Auto-generated method stub
		return promotion;
	}
	
	public String listPromotion() throws Exception {
		try {
			//SessionFactory sf = (SessionFactory) ctx.getAttribute(MyConts.KEY_NAME);
			PromotionHome promotionHome = new PromotionHome();
			listPromotions = promotionHome.getListPromotion();
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	public String deletePromotionById() throws Exception {
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			Integer id = Integer.parseInt(request.getParameter("id"));
			//SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
			PromotionHome promotionHome = new PromotionHome();
			Promotion promotion = promotionHome.findById(id);
			promotionHome.delete(promotion);
			return SUCCESS;
		} catch (Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String addPromotion() throws Exception {
		try {
			//SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
			PromotionHome promotionHome = new PromotionHome(HibernateUtil.getSessionFactory());
			promotion.setPromotionName("Sale Off 2017");
			promotion.setStatus(true);
			promotion.setStartDate(new Date());
			promotion.setEndDate(new Date());
			promotion.setRemarks("Remart");
			promotionHome.attachDirty(promotion);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

}
