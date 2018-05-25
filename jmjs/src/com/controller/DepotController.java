package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pojo.Depot;
import com.pojo.Goods;
import com.pojo.Purchase;
import com.pojo.Supplier;
import com.service.DepotService;
import com.service.GoodsService;
import com.service.SupplierService;
import com.util.PageUtil;
@WebServlet("/depotController")
public class DepotController extends HttpServlet{
	private DepotService service = new DepotService();
	private GoodsService goodsService = new GoodsService();
	private SupplierService supplierService = new SupplierService();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		
		if("findAllByPage".equals(method)) {
			this.findAllByPage(req,resp);
		}else if("toAdd".equals(method)){
			this.toAdd(req,resp);
		}else if("add".equals(method)){
			this.add(req,resp);
		}else if("toUpdate".equals(method)){
			this.toUpdate(req,resp);
		}else if("update".equals(method)){
			this.update(req,resp);
		}else if("deleteAll".equals(method)){
			this.deleteAll(req,resp);
		}else if("findOne".equals(method)){
			this.findOne(req,resp);
		}else{
			System.out.println("用户请求路径有误");
			resp.sendRedirect("404.jsp");
		}
		
		
		
	}



	private void findAllByPage(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		PageUtil page = new PageUtil();
		String condition = req.getParameter("condition");
		if(condition == null) {
			if(session.getAttribute("condition") == null) {
				page.setCondition("");
			}else {
				page.setCondition(session.getAttribute("condition").toString());
			}
		}else {
			page.setCondition(condition);
			session.setAttribute("condition",condition);
		}
		
		String curPage = req.getParameter("curPage");
		if(curPage == null) {
			page.setCurPage(1);
		}else {
			page.setCurPage(Integer.valueOf(curPage));
		}
		
		
		
		page = service.findAllByPage(page);
		req.setAttribute("pageUtil",page);
		
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



	private void findOne(HttpServletRequest req, HttpServletResponse resp) {
		String dId = req.getParameter("dId");
		
		Depot depot = new Depot();
		
		depot.setdId(dId);
		
		depot = service.findOne(depot);
		req.setAttribute("depot",depot);
		 
		List<Goods> goods = goodsService.findAll();
		req.setAttribute("goods", goods);
		
		List<Supplier> suppliers = supplierService.findAllSupIdAndName();
		req.setAttribute("suppliers", suppliers);
		try {
			req.getRequestDispatcher("/html/depot_details.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			resp.sendRedirect(req.getContextPath()+"/depotController?method=findAllByPage");
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
		
		String dNo = req.getParameter("dNo");
		depot.setdNo(dNo);
		
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
		String sId = req.getParameter("sId");
		Supplier supplier = new Supplier();
		supplier.setsId(Integer.valueOf(sId));
		depot.setSupplierId(supplier);
		
		String[] gIds = req.getParameterValues("gId");
		String[] goodsPrice = req.getParameterValues("goodsPrice");
		String[] goodsNumber = req.getParameterValues("goodsNumber");
		if(gIds != null) {
			for (int i=0;i< gIds.length;i++) {
				Purchase p = new Purchase();
				
				Goods g = new Goods();
				g.setgId(Integer.parseInt(gIds[i]));
				p.setGoodsId(g);
				
				p.setGoodsPrice(Double.valueOf(goodsPrice[i]));
				p.setGoodsNumber(Integer.valueOf(goodsNumber[i]));
				depot.getPurchases().add(p);
			}
		}
		
		
		service.update(depot);
		try {
			resp.sendRedirect(req.getContextPath()+"/depotController?method=findAllByPage");
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
		
		List<Supplier> suppliers = supplierService.findAllSupIdAndName();
		req.setAttribute("suppliers", suppliers);
		
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
		
		
		String sId = req.getParameter("sId");
		Supplier s = new Supplier();
		s.setsId(Integer.valueOf(sId));
		depot.setSupplierId(s);
		
		
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
				
				p.setGoodsPrice(Double.valueOf(goodsPrice[i]));
				p.setGoodsNumber(Integer.valueOf(goodsNumber[i]));
				depot.getPurchases().add(p);
			}
		}
		
		service.add(depot);
		try {
			resp.sendRedirect(req.getContextPath()+"/depotController?method=findAllByPage");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void toAdd(HttpServletRequest req, HttpServletResponse resp) {
		List<Goods> goods = goodsService.findAllGoodIdAndName();
		req.setAttribute("goods", goods);
		List<Supplier> suppliers = supplierService.findAllSupIdAndName();
		req.setAttribute("suppliers", suppliers);
		
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
