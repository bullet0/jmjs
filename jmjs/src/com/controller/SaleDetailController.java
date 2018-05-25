package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import com.service.SaleDetailService;
import com.pojo.SaleDetail;
import java.sql.Date;
import java.util.List;
import java.text.ParseException;

public class SaleDetailController extends HttpServlet{
	private SaleDetailService service = new SaleDetailService();
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
		
		String sdId = req.getParameter("sdId");
		
		if(sdId != null){
			value = Integer.valueOf(sdId);
		}
		
		
		SaleDetail saleDetail = new SaleDetail();
		
		saleDetail.setSdId((Integer)value);
		service.delete(saleDetail);
		try {
			resp.sendRedirect(req.getContextPath()+"/saleDetailController?method=findAll");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void deleteAll(HttpServletRequest req, HttpServletResponse resp) {
		String[] sdIds = req.getParameterValues("sdId");
		if(sdIds != null) {
			service.deleteAll(sdIds);
		} 
		try {
			resp.sendRedirect(req.getContextPath()+"/saleDetailController?method=findAll");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private void update(HttpServletRequest req, HttpServletResponse resp) {
		SaleDetail saleDetail = new SaleDetail();
		Object value = null;
		
		
		String sdId = req.getParameter("sdId");
		if(sdId != null){
			value = Integer.valueOf(sdId);
		}
		
		saleDetail.setSdId((Integer)value);
		
		
		String saleId = req.getParameter("saleId");
		if(saleId != null){
			value = Integer.valueOf(saleId);
		}
		
		
		
		String goodsId = req.getParameter("goodsId");
		if(goodsId != null){
			value = Integer.valueOf(goodsId);
		}
		
		
		
		String salePrice = req.getParameter("salePrice");
		if(salePrice != null){
			value = Integer.valueOf(salePrice);
		}
		
		
		
		String saleNumber = req.getParameter("saleNumber");
		if(saleNumber != null){
			value = Integer.valueOf(saleNumber);
		}
		
		saleDetail.setSaleNumber((Integer)value);
		
		
		String purchasePrice = req.getParameter("purchasePrice");
		if(purchasePrice != null){
			value = Integer.valueOf(purchasePrice);
		}
		
		
		service.update(saleDetail);
		try {
			resp.sendRedirect(req.getContextPath()+"/saleDetailController?method=findAll");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void toUpdate(HttpServletRequest req, HttpServletResponse resp) {
		Object value = null;
		
		String sdId = req.getParameter("sdId");
		
		if(sdId != null){
			value = Integer.valueOf(sdId);
		}
		
		SaleDetail saleDetail = new SaleDetail();
		
		saleDetail.setSdId((Integer)value);
		
		saleDetail = service.findOne(saleDetail);
		req.setAttribute("saleDetail",saleDetail);
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
		SaleDetail saleDetail = new SaleDetail();
		Object value = null;
		
		String sdId = req.getParameter("sdId");
		if(sdId != null){
			value = Integer.valueOf(sdId);
		}
		
		saleDetail.setSdId((Integer)value);
		
		
		String saleId = req.getParameter("saleId");
		if(saleId != null){
			value = Integer.valueOf(saleId);
		}
		
		
		
		String goodsId = req.getParameter("goodsId");
		if(goodsId != null){
			value = Integer.valueOf(goodsId);
		}
		
		
		
		String salePrice = req.getParameter("salePrice");
		if(salePrice != null){
			value = Integer.valueOf(salePrice);
		}
		
		
		
		String saleNumber = req.getParameter("saleNumber");
		if(saleNumber != null){
			value = Integer.valueOf(saleNumber);
		}
		
		saleDetail.setSaleNumber((Integer)value);
		
		
		String purchasePrice = req.getParameter("purchasePrice");
		if(purchasePrice != null){
			value = Integer.valueOf(purchasePrice);
		}
		
		
		
		service.add(saleDetail);
		try {
			resp.sendRedirect(req.getContextPath()+"/saleDetailController?method=findAll");
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
		List<SaleDetail> saleDetails = service.findAll();
		req.setAttribute("saleDetails",saleDetails);
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
