package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import com.service.CustomerService;
import com.pojo.Customer;
import java.sql.Date;
import java.util.List;
import java.text.ParseException;

public class CustomerController extends HttpServlet{
	private CustomerService service = new CustomerService();
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
		
		String cId = req.getParameter("cId");
		
		if(cId != null){
			value = Integer.valueOf(cId);
		}
		
		
		Customer customer = new Customer();
		
		customer.setcId((Integer)value);
		service.delete(customer);
		try {
			resp.sendRedirect(req.getContextPath()+"/customerController?method=findAll");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) {
		Customer customer = new Customer();
		Object value = null;
		
		
		String cId = req.getParameter("cId");
		if(cId != null){
			value = Integer.valueOf(cId);
		}
		
		customer.setcId((Integer)value);
		
		
		String cName = req.getParameter("cName");
		value = cName;
		
		
		customer.setcName((String)value);
		
		
		String cPhone = req.getParameter("cPhone");
		value = cPhone;
		
		
		customer.setcPhone((String)value);
		
		
		String cAddress = req.getParameter("cAddress");
		value = cAddress;
		
		
		customer.setcAddress((String)value);
		
		
		String cEmail = req.getParameter("cEmail");
		value = cEmail;
		
		
		customer.setcEmail((String)value);
		
		
		String cConMobile = req.getParameter("cConMobile");
		value = cConMobile;
		
		
		customer.setcConMobile((String)value);
		
		
		String cConName = req.getParameter("cConName");
		value = cConName;
		
		
		customer.setcConName((String)value);
		
		
		String cPostCode = req.getParameter("cPostCode");
		value = cPostCode;
		
		
		customer.setcPostCode((String)value);
		
		
		String cAccount = req.getParameter("cAccount");
		value = cAccount;
		
		
		customer.setcAccount((String)value);
		
		service.update(customer);
		try {
			resp.sendRedirect(req.getContextPath()+"/customerController?method=findAll");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void toUpdate(HttpServletRequest req, HttpServletResponse resp) {
		Object value = null;
		
		String cId = req.getParameter("cId");
		
		if(cId != null){
			value = Integer.valueOf(cId);
		}
		
		Customer customer = new Customer();
		
		customer.setcId((Integer)value);
		
		customer = service.findOne(customer);
		req.setAttribute("customer",customer);
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
		Customer customer = new Customer();
		Object value = null;
		
		String cId = req.getParameter("cId");
		if(cId != null){
			value = Integer.valueOf(cId);
		}
		
		customer.setcId((Integer)value);
		
		
		String cName = req.getParameter("cName");
		value = cName;
		
		
		customer.setcName((String)value);
		
		
		String cPhone = req.getParameter("cPhone");
		value = cPhone;
		
		
		customer.setcPhone((String)value);
		
		
		String cAddress = req.getParameter("cAddress");
		value = cAddress;
		
		
		customer.setcAddress((String)value);
		
		
		String cEmail = req.getParameter("cEmail");
		value = cEmail;
		
		
		customer.setcEmail((String)value);
		
		
		String cConMobile = req.getParameter("cConMobile");
		value = cConMobile;
		
		
		customer.setcConMobile((String)value);
		
		
		String cConName = req.getParameter("cConName");
		value = cConName;
		
		
		customer.setcConName((String)value);
		
		
		String cPostCode = req.getParameter("cPostCode");
		value = cPostCode;
		
		
		customer.setcPostCode((String)value);
		
		
		String cAccount = req.getParameter("cAccount");
		value = cAccount;
		
		
		customer.setcAccount((String)value);
		
		
		service.add(customer);
		try {
			resp.sendRedirect(req.getContextPath()+"/customerController?method=findAll");
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
		List<Customer> customers = service.findAll();
		req.setAttribute("customers",customers);
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
