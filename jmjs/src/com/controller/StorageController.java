package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import com.service.StorageService;
import com.pojo.Storage;
import java.sql.Date;
import java.util.List;
import java.text.ParseException;

public class StorageController extends HttpServlet{
	private StorageService service = new StorageService();
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
		
		
		Storage storage = new Storage();
		
		storage.setsId((Integer)value);
		service.delete(storage);
		try {
			resp.sendRedirect(req.getContextPath()+"/storageController?method=findAll");
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
			resp.sendRedirect(req.getContextPath()+"/storageController?method=findAll");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private void update(HttpServletRequest req, HttpServletResponse resp) {
		Storage storage = new Storage();
		Object value = null;
		
		
		String sId = req.getParameter("sId");
		if(sId != null){
			value = Integer.valueOf(sId);
		}
		
		storage.setsId((Integer)value);
		
		
		String sGoodsName = req.getParameter("sGoodsName");
		value = sGoodsName;
		
		
		storage.setsGoodsName((String)value);
		
		
		String sSupplierName = req.getParameter("sSupplierName");
		value = sSupplierName;
		
		
		storage.setsSupplierName((String)value);
		
		
		String sPrice = req.getParameter("sPrice");
		if(sPrice != null){
			value = Integer.valueOf(sPrice);
		}
		
		storage.setsPrice((Integer)value);
		
		
		String sType = req.getParameter("sType");
		value = sType;
		
		
		storage.setsType((String)value);
		
		
		String sStockNum = req.getParameter("sStockNum");
		if(sStockNum != null){
			value = Integer.valueOf(sStockNum);
		}
		
		storage.setsStockNum((Integer)value);
		
		service.update(storage);
		try {
			resp.sendRedirect(req.getContextPath()+"/storageController?method=findAll");
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
		
		Storage storage = new Storage();
		
		storage.setsId((Integer)value);
		
		storage = service.findOne(storage);
		req.setAttribute("storage",storage);
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
		Storage storage = new Storage();
		Object value = null;
		
		String sId = req.getParameter("sId");
		if(sId != null){
			value = Integer.valueOf(sId);
		}
		
		storage.setsId((Integer)value);
		
		
		String sGoodsName = req.getParameter("sGoodsName");
		value = sGoodsName;
		
		
		storage.setsGoodsName((String)value);
		
		
		String sSupplierName = req.getParameter("sSupplierName");
		value = sSupplierName;
		
		
		storage.setsSupplierName((String)value);
		
		
		String sPrice = req.getParameter("sPrice");
		if(sPrice != null){
			value = Integer.valueOf(sPrice);
		}
		
		storage.setsPrice((Integer)value);
		
		
		String sType = req.getParameter("sType");
		value = sType;
		
		
		storage.setsType((String)value);
		
		
		String sStockNum = req.getParameter("sStockNum");
		if(sStockNum != null){
			value = Integer.valueOf(sStockNum);
		}
		
		storage.setsStockNum((Integer)value);
		
		
		service.add(storage);
		try {
			resp.sendRedirect(req.getContextPath()+"/storageController?method=findAll");
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
		List<Storage> storages = service.findAll();
		req.setAttribute("storages",storages);
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
