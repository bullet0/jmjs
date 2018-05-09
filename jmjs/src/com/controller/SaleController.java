package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import com.service.SaleService;
import com.pojo.Sale;
import java.sql.Date;
import java.util.List;
import java.text.ParseException;

public class SaleController extends HttpServlet{
	private SaleService service = new SaleService();
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
		
		String sId = req.getParameter("sId");
		
		if(sId != null){
			value = Integer.valueOf(sId);
		}
		
		
		Sale sale = new Sale();
		
		sale.setsId((Integer)value);
		service.delete(sale);
		try {
			resp.sendRedirect(req.getContextPath()+"/saleController?method=findAll");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void deleteAll(HttpServletRequest req, HttpServletResponse resp) {
		String[] sIds = req.getParameterValues("sId");
		if(sIds != null) {
			service.deleteAll(sIds);
		} 
		try {
			resp.sendRedirect(req.getContextPath()+"/saleController?method=findAll");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private void update(HttpServletRequest req, HttpServletResponse resp) {
		Sale sale = new Sale();
		Object value = null;
		
		
		String sId = req.getParameter("sId");
		if(sId != null){
			value = Integer.valueOf(sId);
		}
		
		sale.setsId((Integer)value);
		
		
		String sVarietyNum = req.getParameter("sVarietyNum");
		if(sVarietyNum != null){
			value = Integer.valueOf(sVarietyNum);
		}
		
		sale.setsVarietyNum((Integer)value);
		
		
		String sTotalPrice = req.getParameter("sTotalPrice");
		if(sTotalPrice != null){
			value = Integer.valueOf(sTotalPrice);
		}
		
		sale.setsTotalPrice((Integer)value);
		
		
		String sSaleDate = req.getParameter("sSaleDate");
		value = sSaleDate;
		
		
		sale.setsSaleDate((String)value);
		
		
		String sSettlementWay = req.getParameter("sSettlementWay");
		value = sSettlementWay;
		
		
		sale.setsSettlementWay((String)value);
		
		
		String customerId = req.getParameter("customerId");
		if(customerId != null){
			value = Integer.valueOf(customerId);
		}
		
		sale.setCustomerId((Integer)value);
		
		
		String customerName = req.getParameter("customerName");
		value = customerName;
		
		
		sale.setCustomerName((String)value);
		
		service.update(sale);
		try {
			resp.sendRedirect(req.getContextPath()+"/saleController?method=findAll");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void toUpdate(HttpServletRequest req, HttpServletResponse resp) {
		Object value = null;
		
		String sId = req.getParameter("sId");
		
		if(sId != null){
			value = Integer.valueOf(sId);
		}
		
		Sale sale = new Sale();
		
		sale.setsId((Integer)value);
		
		sale = service.findOne(sale);
		req.setAttribute("sale",sale);
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
		Sale sale = new Sale();
		Object value = null;
		
		String sId = req.getParameter("sId");
		if(sId != null){
			value = Integer.valueOf(sId);
		}
		
		sale.setsId((Integer)value);
		
		
		String sVarietyNum = req.getParameter("sVarietyNum");
		if(sVarietyNum != null){
			value = Integer.valueOf(sVarietyNum);
		}
		
		sale.setsVarietyNum((Integer)value);
		
		
		String sTotalPrice = req.getParameter("sTotalPrice");
		if(sTotalPrice != null){
			value = Integer.valueOf(sTotalPrice);
		}
		
		sale.setsTotalPrice((Integer)value);
		
		
		String sSaleDate = req.getParameter("sSaleDate");
		value = sSaleDate;
		
		
		sale.setsSaleDate((String)value);
		
		
		String sSettlementWay = req.getParameter("sSettlementWay");
		value = sSettlementWay;
		
		
		sale.setsSettlementWay((String)value);
		
		
		String customerId = req.getParameter("customerId");
		if(customerId != null){
			value = Integer.valueOf(customerId);
		}
		
		sale.setCustomerId((Integer)value);
		
		
		String customerName = req.getParameter("customerName");
		value = customerName;
		
		
		sale.setCustomerName((String)value);
		
		
		service.add(sale);
		try {
			resp.sendRedirect(req.getContextPath()+"/saleController?method=findAll");
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
		List<Sale> sales = service.findAll();
		req.setAttribute("sales",sales);
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
