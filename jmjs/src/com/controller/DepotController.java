package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pojo.Depot;
import com.pojo.Goods;
import com.pojo.Purchase;
import com.service.DepotService;
import com.service.GoodsService;
@WebServlet("/depotController")
public class DepotController extends HttpServlet{
	private DepotService service = new DepotService();
	private GoodsService goodsService = new GoodsService();
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
		
		String dId = req.getParameter("dId");
		
		Depot depot = new Depot();
		
		depot.setdId(dId);
		service.delete(depot);
		try {
			resp.sendRedirect(req.getContextPath()+"/depotController?method=findAll");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void deleteAll(HttpServletRequest req, HttpServletResponse resp) {
		String[] dIds = req.getParameterValues("dId");
		if(dIds != null) {
			service.deleteAll(dIds);
		} 
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
		depot.setdId(dId);
		
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
		
		String[] gIds = req.getParameterValues("gId");
		String[] goodsPrice = req.getParameterValues("goodsPrice");
		String[] goodsNumber = req.getParameterValues("goodsNumber");
		if(gIds != null) {
			for (int i=0;i< gIds.length;i++) {
				Purchase p = new Purchase();
				
				Goods g = new Goods();
				g.setgId(Integer.parseInt(gIds[i]));
				p.setGoodsId(g);
				
				p.setGoodsPrice((int) (Double.valueOf(goodsPrice[i]) * 100));
				p.setGoodsNumber(Integer.valueOf(goodsNumber[i]));
				depot.getPurchases().add(p);
			}
		}
		
		
		service.update(depot);
		try {
			resp.sendRedirect(req.getContextPath()+"/depotController?method=findAll");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void toUpdate(HttpServletRequest req, HttpServletResponse resp) {
		
		String dId = req.getParameter("dId");
		
		Depot depot = new Depot();
		
		depot.setdId(dId);
		
		depot = service.findOne(depot);
		req.setAttribute("depot",depot);
		
		List<Goods> goods = goodsService.findAll();
		req.setAttribute("goods", goods);
		try {
			req.getRequestDispatcher("/html/depot_update.jsp").forward(req, resp);
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
		
		String[] gIds = req.getParameterValues("gId");
		String[] goodsPrice = req.getParameterValues("goodsPrice");
		String[] goodsNumber = req.getParameterValues("goodsNumber");
		if(gIds != null) {
			for (int i=0;i< gIds.length;i++) {
				Purchase p = new Purchase();
				
				Goods g = new Goods();
				g.setgId(Integer.parseInt(gIds[i]));
				p.setGoodsId(g);
				
				p.setGoodsPrice((int) (Double.valueOf(goodsPrice[i]) * 100));
				p.setGoodsNumber(Integer.valueOf(goodsNumber[i]));
				depot.getPurchases().add(p);
			}
		}
		
		
		
		service.add(depot);
		try {
			resp.sendRedirect(req.getContextPath()+"/depotController?method=findAll");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void toAdd(HttpServletRequest req, HttpServletResponse resp) {
		List<Goods> goods = goodsService.findAll();
		req.setAttribute("goods", goods);
		try {
			req.getRequestDispatcher("/html/depot_add.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void findAll(HttpServletRequest req, HttpServletResponse resp) {
		List<Depot> depots = service.findAll();
		req.setAttribute("depots",depots);
		try {
			req.getRequestDispatcher("/html/depot_list.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
