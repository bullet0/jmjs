package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import com.service.DepotService;
import com.pojo.Depot;
import java.sql.Date;
import java.util.List;
import java.text.ParseException;

public class DepotController extends HttpServlet{
	private DepotService service = new DepotService();
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
		}else{
			System.out.println("用户请求路径有误");
			resp.sendRedirect("404.jsp");
		}
		
		
		
	}



	private void delete(HttpServletRequest req, HttpServletResponse resp) {
		Object value = null;
		
		String dId = req.getParameter("dId");
		
		if(dId != null){
			value = Integer.valueOf(dId);
		}
		
		
		Depot depot = new Depot();
		
		depot.setdId((Integer)value);
		service.delete(depot);
		try {
			resp.sendRedirect(req.getContextPath()+"/depotController?method=findAll");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) {
		Depot depot = new Depot();
		Object value = null;
		
		
		String dId = req.getParameter("dId");
		if(dId != null){
			value = Integer.valueOf(dId);
		}
		
		depot.setdId((Integer)value);
		
		
		String dVarietyNum = req.getParameter("dVarietyNum");
		if(dVarietyNum != null){
			value = Integer.valueOf(dVarietyNum);
		}
		
		depot.setdVarietyNum((Integer)value);
		
		
		String dTotalPrice = req.getParameter("dTotalPrice");
		if(dTotalPrice != null){
			value = Integer.valueOf(dTotalPrice);
		}
		
		depot.setdTotalPrice((Integer)value);
		
		
		String dDate = req.getParameter("dDate");
		value = dDate;
		
		
		depot.setdDate((String)value);
		
		
		String dSettlementWay = req.getParameter("dSettlementWay");
		value = dSettlementWay;
		
		
		depot.setdSettlementWay((String)value);
		
		
		String supplierId = req.getParameter("supplierId");
		if(supplierId != null){
			value = Integer.valueOf(supplierId);
		}
		
		depot.setSupplierId((Integer)value);
		
		
		String supplierName = req.getParameter("supplierName");
		value = supplierName;
		
		
		depot.setSupplierName((String)value);
		
		service.update(depot);
		try {
			resp.sendRedirect(req.getContextPath()+"/depotController?method=findAll");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void toUpdate(HttpServletRequest req, HttpServletResponse resp) {
		Object value = null;
		
		String dId = req.getParameter("dId");
		
		if(dId != null){
			value = Integer.valueOf(dId);
		}
		
		Depot depot = new Depot();
		
		depot.setdId((Integer)value);
		
		depot = service.findOne(depot);
		req.setAttribute("depot",depot);
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
		Depot depot = new Depot();
		Object value = null;
		
		String dId = req.getParameter("dId");
		if(dId != null){
			value = Integer.valueOf(dId);
		}
		
		depot.setdId((Integer)value);
		
		
		String dVarietyNum = req.getParameter("dVarietyNum");
		if(dVarietyNum != null){
			value = Integer.valueOf(dVarietyNum);
		}
		
		depot.setdVarietyNum((Integer)value);
		
		
		String dTotalPrice = req.getParameter("dTotalPrice");
		if(dTotalPrice != null){
			value = Integer.valueOf(dTotalPrice);
		}
		
		depot.setdTotalPrice((Integer)value);
		
		
		String dDate = req.getParameter("dDate");
		value = dDate;
		
		
		depot.setdDate((String)value);
		
		
		String dSettlementWay = req.getParameter("dSettlementWay");
		value = dSettlementWay;
		
		
		depot.setdSettlementWay((String)value);
		
		
		String supplierId = req.getParameter("supplierId");
		if(supplierId != null){
			value = Integer.valueOf(supplierId);
		}
		
		depot.setSupplierId((Integer)value);
		
		
		String supplierName = req.getParameter("supplierName");
		value = supplierName;
		
		
		depot.setSupplierName((String)value);
		
		
		service.add(depot);
		try {
			resp.sendRedirect(req.getContextPath()+"/depotController?method=findAll");
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
		List<Depot> depots = service.findAll();
		req.setAttribute("depots",depots);
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
