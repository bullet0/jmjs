package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;

import com.service.CustomerService;
import com.service.GoodsService;
import com.service.SaleService;
import com.service.StorageService;
import com.util.PageUtil;
import com.pojo.Customer;
import com.pojo.Depot;
import com.pojo.Goods;
import com.pojo.Purchase;
import com.pojo.Sale;
import com.pojo.SaleDetail;
import com.pojo.StorageVO;
import com.pojo.Supplier;

import java.sql.Date;
import java.util.List;
import java.text.ParseException;
@WebServlet("/saleController")
public class SaleController extends HttpServlet{
	private SaleService service = new SaleService();
	private GoodsService goodsService = new GoodsService();
	private CustomerService customerService = new CustomerService();
	private StorageService storageService = new StorageService();
	
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
		}
		else{
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
			req.getRequestDispatcher("/html/sale_list.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	private void findOne(HttpServletRequest req, HttpServletResponse resp) {
		
		Object value = null;
		
		String sId = req.getParameter("sId");
		
		if(sId != null){
			value = Integer.valueOf(sId);
		}
		
		Sale sale = new Sale();
		
		sale.setsId((Integer)value);
		
		sale = service.findOne(sale);
		req.setAttribute("sale",sale);
		
		
		List<Goods> goods = goodsService.findAllGoodIdAndName();
		req.setAttribute("goods", goods);
		List<Customer> customers = customerService.findAllCusIdAndName();
		req.setAttribute("customers", customers);
		try {
			req.getRequestDispatcher("/html/sale_details.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			resp.sendRedirect(req.getContextPath()+"/saleController?method=findAllByPage");
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
		String sNo = req.getParameter("sNo");
		
		sale.setsNo(sNo);
		
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
		
		
		String customerId = req.getParameter("cId");
		if(customerId != null){
			Customer c = new Customer();
			c.setcId(Integer.valueOf(customerId));
			sale.setCustomerId(c);
		}
		
		String[] gIds = req.getParameterValues("gId");
		String[] goodsPrice = req.getParameterValues("goodsPrice");
		String[] goodsNumber = req.getParameterValues("goodsNumber");
		if(gIds != null) {
			for (int i=0;i< gIds.length;i++) {
				SaleDetail saleDetail = new SaleDetail();
				
				Goods g = new Goods();
				g.setgId(Integer.parseInt(gIds[i]));
				saleDetail.setGoodsId(g);
				
				saleDetail.setSalePrice(Double.valueOf(goodsPrice[i]));
				saleDetail.setSaleNumber(Integer.valueOf(goodsNumber[i]));
				sale.getSaleDetails().add(saleDetail);
			}
		}
		
		
		service.update(sale);
		try {
			resp.sendRedirect(req.getContextPath()+"/saleController?method=findAllByPage");
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
		
		
		List<Goods> goods = goodsService.findAllGoodIdAndName();
		req.setAttribute("goods", goods);
		List<Customer> customers = customerService.findAllCusIdAndName();
		req.setAttribute("customers", customers);
		
		try {
			req.getRequestDispatcher("/html/sale_update.jsp").forward(req, resp);
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
			Customer customer = new Customer();
			customer.setcId((int)value);
			sale.setCustomerId(customer);
		}
		
		
		String[] gIds = req.getParameterValues("gId");
		String[] goodsPrice = req.getParameterValues("goodsPrice");
		String[] goodsNumber = req.getParameterValues("goodsNumber");
		if(gIds != null) {
			for (int i=0;i< gIds.length;i++) {
				SaleDetail saleDetail = new SaleDetail();
				
				Goods g = new Goods();
				g.setgId(Integer.parseInt(gIds[i]));
				saleDetail.setGoodsId(g);
				
				saleDetail.setSalePrice(Double.valueOf(goodsPrice[i]));
				saleDetail.setSaleNumber(Integer.valueOf(goodsNumber[i]));
				sale.getSaleDetails().add(saleDetail);
			}
		}
		
		
		service.add(sale);
		try {
			resp.sendRedirect(req.getContextPath()+"/saleController?method=findAllByPage");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void toAdd(HttpServletRequest req, HttpServletResponse resp) {
		List<Goods> goods = goodsService.findAllGoodIdAndName();
		req.setAttribute("goods", goods);
		List<Customer> customers = customerService.findAllCusIdAndName();
		req.setAttribute("customers", customers);
		
		try {
			req.getRequestDispatcher("/html/sale_add.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void findAll(HttpServletRequest req, HttpServletResponse resp) {
		List<Sale> sales = service.findAll();
		req.setAttribute("sales",sales);
		try {
			req.getRequestDispatcher("/html/sale_list.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
