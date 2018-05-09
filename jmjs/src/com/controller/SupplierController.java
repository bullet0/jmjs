package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import com.service.SupplierService;
import com.pojo.Supplier;
import java.sql.Date;
import java.util.List;
import java.text.ParseException;
@WebServlet("/supplierController")
public class SupplierController extends HttpServlet{
	private SupplierService service = new SupplierService();
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
		
		
		Supplier supplier = new Supplier();
		
		supplier.setsId((Integer)value);
		service.delete(supplier);
		try {
			resp.sendRedirect(req.getContextPath()+"/supplierController?method=findAll");
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
			resp.sendRedirect(req.getContextPath()+"/supplierController?method=findAll");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private void update(HttpServletRequest req, HttpServletResponse resp) {
		Supplier supplier = new Supplier();
		Object value = null;
		
		
		String sId = req.getParameter("sId");
		if(sId != null){
			value = Integer.valueOf(sId);
		}
		
		supplier.setsId((Integer)value);
		
		
		String sName = req.getParameter("sName");
		value = sName;
		
		
		supplier.setsName((String)value);
		
		
		String sPhone = req.getParameter("sPhone");
		value = sPhone;
		
		
		supplier.setsPhone((String)value);
		
		
		String sAddress = req.getParameter("sAddress");
		value = sAddress;
		
		
		supplier.setsAddress((String)value);
		
		
		String sEmail = req.getParameter("sEmail");
		value = sEmail;
		
		
		supplier.setsEmail((String)value);
		
		
		String sConMobile = req.getParameter("sConMobile");
		value = sConMobile;
		
		
		supplier.setsConMobile((String)value);
		
		
		String sConName = req.getParameter("sConName");
		value = sConName;
		
		
		supplier.setsConName((String)value);
		
		
		String sPostCode = req.getParameter("sPostCode");
		value = sPostCode;
		
		
		supplier.setsPostCode((String)value);
		
		
		String sAccount = req.getParameter("sAccount");
		value = sAccount;
		
		
		supplier.setsAccount((String)value);
		
		service.update(supplier);
		try {
			resp.sendRedirect(req.getContextPath()+"/supplierController?method=findAll");
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
		
		Supplier supplier = new Supplier();
		
		supplier.setsId((Integer)value);
		
		supplier = service.findOne(supplier);
		req.setAttribute("supplier",supplier);
		try {
			req.getRequestDispatcher("/html/supplier_update.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void add(HttpServletRequest req, HttpServletResponse resp) {
		Supplier supplier = new Supplier();
		Object value = null;
		
		String sId = req.getParameter("sId");
		if(sId != null){
			value = Integer.valueOf(sId);
		}
		
		supplier.setsId((Integer)value);
		
		
		String sName = req.getParameter("sName");
		value = sName;
		
		
		supplier.setsName((String)value);
		
		
		String sPhone = req.getParameter("sPhone");
		value = sPhone;
		
		
		supplier.setsPhone((String)value);
		
		
		String sAddress = req.getParameter("sAddress");
		value = sAddress;
		
		
		supplier.setsAddress((String)value);
		
		
		String sEmail = req.getParameter("sEmail");
		value = sEmail;
		
		
		supplier.setsEmail((String)value);
		
		
		String sConMobile = req.getParameter("sConMobile");
		value = sConMobile;
		
		
		supplier.setsConMobile((String)value);
		
		
		String sConName = req.getParameter("sConName");
		value = sConName;
		
		
		supplier.setsConName((String)value);
		
		
		String sPostCode = req.getParameter("sPostCode");
		value = sPostCode;
		
		
		supplier.setsPostCode((String)value);
		
		
		String sAccount = req.getParameter("sAccount");
		value = sAccount;
		
		
		supplier.setsAccount((String)value);
		
		
		service.add(supplier);
		try {
			resp.sendRedirect(req.getContextPath()+"/supplierController?method=findAll");
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
		List<Supplier> suppliers = service.findAll();
		req.setAttribute("suppliers",suppliers);
		try {
			req.getRequestDispatcher("/html/supplier_list.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
