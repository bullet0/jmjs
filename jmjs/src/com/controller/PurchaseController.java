package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import com.service.PurchaseService;
import com.pojo.Purchase;
import java.sql.Date;
import java.util.List;
import java.text.ParseException;

public class PurchaseController extends HttpServlet{
	private PurchaseService service = new PurchaseService();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		String method = req.getParameter("method");
		
		if("findAll".equals(method)) {
			this.findAll(req,resp);
		}else if("toAdd".equals(method)){
			this.toAdd(req,resp);
		}else if("add".equals(method)){
			this.add(req,resp);
		}else if("toUpdate".equals(method)){
			this.toUpdate(req,resp);
		}else if("update".equals(method)){
			this.update(req,resp);
		}else if("delete".equals(method)){
			this.delete(req,resp);
		}else if("deleteAll".equals(method)){
			this.deleteAll(req,resp);
		}else{
			System.out.println("用户请求路径有误");
			resp.sendRedirect("404.jsp");
		}
		
		
		
	}



	private void delete(HttpServletRequest req, HttpServletResponse resp) {
		Object value = null;
		
		String pId = req.getParameter("pId");
		
		if(pId != null){
			value = Integer.valueOf(pId);
		}
		
		
		Purchase purchase = new Purchase();
		
		purchase.setpId((Integer)value);
		service.delete(purchase);
		try {
			resp.sendRedirect(req.getContextPath()+"/purchaseController?method=findAll");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void deleteAll(HttpServletRequest req, HttpServletResponse resp) {
		String[] pIds = req.getParameterValues("pId");
		if(pIds != null) {
			service.deleteAll(pIds);
		} 
		try {
			resp.sendRedirect(req.getContextPath()+"/purchaseController?method=findAll");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private void update(HttpServletRequest req, HttpServletResponse resp) {
		Purchase purchase = new Purchase();
		Object value = null;
		
		
		String pId = req.getParameter("pId");
		if(pId != null){
			value = Integer.valueOf(pId);
		}
		
		purchase.setpId((Integer)value);
		
		
		String depotId = req.getParameter("depotId");
		if(depotId != null){
			value = Integer.valueOf(depotId);
		}
		
		
		
		String goodsId = req.getParameter("goodsId");
		if(goodsId != null){
			value = Integer.valueOf(goodsId);
		}
		
		
		
		String goodsPrice = req.getParameter("goodsPrice");
		if(goodsPrice != null){
			value = Integer.valueOf(goodsPrice);
		}
		
		purchase.setGoodsPrice((Integer)value);
		
		
		String goodsNumber = req.getParameter("goodsNumber");
		if(goodsNumber != null){
			value = Integer.valueOf(goodsNumber);
		}
		
		purchase.setGoodsNumber((Integer)value);
		
		service.update(purchase);
		try {
			resp.sendRedirect(req.getContextPath()+"/purchaseController?method=findAll");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void toUpdate(HttpServletRequest req, HttpServletResponse resp) {
		Object value = null;
		
		String pId = req.getParameter("pId");
		
		if(pId != null){
			value = Integer.valueOf(pId);
		}
		
		Purchase purchase = new Purchase();
		
		purchase.setpId((Integer)value);
		
		purchase = service.findOne(purchase);
		req.setAttribute("purchase",purchase);
		try {
			req.getRequestDispatcher("/修改页面").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void add(HttpServletRequest req, HttpServletResponse resp) {
		Purchase purchase = new Purchase();
		Object value = null;
		
		String pId = req.getParameter("pId");
		if(pId != null){
			value = Integer.valueOf(pId);
		}
		
		purchase.setpId((Integer)value);
		
		
		String depotId = req.getParameter("depotId");
		if(depotId != null){
			value = Integer.valueOf(depotId);
		}
		
		
		
		String goodsId = req.getParameter("goodsId");
		if(goodsId != null){
			value = Integer.valueOf(goodsId);
		}
		
		
		
		String goodsPrice = req.getParameter("goodsPrice");
		if(goodsPrice != null){
			value = Integer.valueOf(goodsPrice);
		}
		
		purchase.setGoodsPrice((Integer)value);
		
		
		String goodsNumber = req.getParameter("goodsNumber");
		if(goodsNumber != null){
			value = Integer.valueOf(goodsNumber);
		}
		
		purchase.setGoodsNumber((Integer)value);
		
		
		service.add(purchase);
		try {
			resp.sendRedirect(req.getContextPath()+"/purchaseController?method=findAll");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void toAdd(HttpServletRequest req, HttpServletResponse resp) {
		try {
			resp.sendRedirect(req.getContextPath()+"/添加页面");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void findAll(HttpServletRequest req, HttpServletResponse resp) {
		List<Purchase> purchases = service.findAll();
		req.setAttribute("purchases",purchases);
		try {
			req.getRequestDispatcher("/查询页面").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
