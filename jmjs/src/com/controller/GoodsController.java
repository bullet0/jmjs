package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import com.service.GoodsService;
import com.pojo.Goods;
import java.sql.Date;
import java.util.List;
import java.text.ParseException;

public class GoodsController extends HttpServlet{
	private GoodsService service = new GoodsService();
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
		
		String gId = req.getParameter("gId");
		
		if(gId != null){
			value = Integer.valueOf(gId);
		}
		
		
		Goods goods = new Goods();
		
		goods.setgId((Integer)value);
		service.delete(goods);
		try {
			resp.sendRedirect(req.getContextPath()+"/goodsController?method=findAll");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) {
		Goods goods = new Goods();
		Object value = null;
		
		
		String gId = req.getParameter("gId");
		if(gId != null){
			value = Integer.valueOf(gId);
		}
		
		goods.setgId((Integer)value);
		
		
		String gName = req.getParameter("gName");
		value = gName;
		
		
		goods.setgName((String)value);
		
		
		String gNumber = req.getParameter("gNumber");
		if(gNumber != null){
			value = Integer.valueOf(gNumber);
		}
		
		goods.setgNumber((Integer)value);
		
		
		String gProduce = req.getParameter("gProduce");
		value = gProduce;
		
		
		goods.setgProduce((String)value);
		
		
		String gProductionDate = req.getParameter("gProductionDate");
		if(gProductionDate != null){
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date parse = null;
			try {
				parse = fmt.parse(gProductionDate);
				value = new java.sql.Date( parse.getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		goods.setgProductionDate((Date)value);
		
		
		String gReleaseDate = req.getParameter("gReleaseDate");
		if(gReleaseDate != null){
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date parse = null;
			try {
				parse = fmt.parse(gReleaseDate);
				value = new java.sql.Date( parse.getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		goods.setgReleaseDate((Date)value);
		
		
		String gType = req.getParameter("gType");
		value = gType;
		
		
		goods.setgType((String)value);
		
		
		String gUnit = req.getParameter("gUnit");
		value = gUnit;
		
		
		goods.setgUnit((String)value);
		
		
		String gRemark = req.getParameter("gRemark");
		value = gRemark;
		
		
		goods.setgRemark((String)value);
		
		
		String gSupplier = req.getParameter("gSupplier");
		value = gSupplier;
		
		
		goods.setgSupplier((String)value);
		
		service.update(goods);
		try {
			resp.sendRedirect(req.getContextPath()+"/goodsController?method=findAll");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void toUpdate(HttpServletRequest req, HttpServletResponse resp) {
		Object value = null;
		
		String gId = req.getParameter("gId");
		
		if(gId != null){
			value = Integer.valueOf(gId);
		}
		
		Goods goods = new Goods();
		
		goods.setgId((Integer)value);
		
		goods = service.findOne(goods);
		req.setAttribute("goods",goods);
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
		Goods goods = new Goods();
		Object value = null;
		
		String gId = req.getParameter("gId");
		if(gId != null){
			value = Integer.valueOf(gId);
		}
		
		goods.setgId((Integer)value);
		
		
		String gName = req.getParameter("gName");
		value = gName;
		
		
		goods.setgName((String)value);
		
		
		String gNumber = req.getParameter("gNumber");
		if(gNumber != null){
			value = Integer.valueOf(gNumber);
		}
		
		goods.setgNumber((Integer)value);
		
		
		String gProduce = req.getParameter("gProduce");
		value = gProduce;
		
		
		goods.setgProduce((String)value);
		
		
		String gProductionDate = req.getParameter("gProductionDate");
		if(gProductionDate != null){
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date parse;
			try {
				parse = parse = fmt.parse(gProductionDate);
				value = new java.sql.Date( parse.getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		goods.setgProductionDate((Date)value);
		
		
		String gReleaseDate = req.getParameter("gReleaseDate");
		if(gReleaseDate != null){
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date parse;
			try {
				parse = parse = fmt.parse(gReleaseDate);
				value = new java.sql.Date( parse.getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		goods.setgReleaseDate((Date)value);
		
		
		String gType = req.getParameter("gType");
		value = gType;
		
		
		goods.setgType((String)value);
		
		
		String gUnit = req.getParameter("gUnit");
		value = gUnit;
		
		
		goods.setgUnit((String)value);
		
		
		String gRemark = req.getParameter("gRemark");
		value = gRemark;
		
		
		goods.setgRemark((String)value);
		
		
		String gSupplier = req.getParameter("gSupplier");
		value = gSupplier;
		
		
		goods.setgSupplier((String)value);
		
		
		service.add(goods);
		try {
			resp.sendRedirect(req.getContextPath()+"/goodsController?method=findAll");
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
		List<Goods> goodss = service.findAll();
		req.setAttribute("goodss",goodss);
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
