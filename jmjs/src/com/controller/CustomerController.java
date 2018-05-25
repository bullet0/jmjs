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
import com.util.PageUtil;
import com.pojo.Customer;
import java.sql.Date;
import java.util.List;
import java.text.ParseException;
@WebServlet("/customerController")
public class CustomerController extends HttpServlet{
	private CustomerService service = new CustomerService();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		
		if("findAllByPage".equals(method)) {
			this.findAllByPage(req,resp);
		}else if("toAdd".equals(method)){
			this.toAdd(req,resp);
		}else if("findAll".equals(method)){
			this.findAll(req,resp);
		}else if("add".equals(method)){
			this.add(req,resp);
		}else if("toUpdate".equals(method)){
			this.toUpdate(req,resp);
		}else if("update".equals(method)){
			this.update(req,resp);
		}else if("deleteAll".equals(method)){
			this.deleteAll(req,resp);
		}else{
			System.out.println("用户请求路径有误");
			resp.sendRedirect("404.jsp");
		}
		
		
		
	}


	private void findAll(HttpServletRequest req, HttpServletResponse resp) {
		List<Customer> customers = service.findAll();
		req.setAttribute("customers",customers);
		try {
			req.getRequestDispatcher("/html/customers_list.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			req.getRequestDispatcher("/html/customers_list.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void deleteAll(HttpServletRequest req, HttpServletResponse resp) {
		String[] cIds = req.getParameterValues("cId");
		if(cIds != null) {
			service.deleteAll(cIds);
		} 
		try {
			resp.sendRedirect(req.getContextPath()+"/customerController?method=findAllByPage");
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
			resp.sendRedirect(req.getContextPath()+"/customerController?method=findAllByPage");
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
			req.getRequestDispatcher("/html/customer_update.jsp").forward(req, resp);
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
			resp.sendRedirect(req.getContextPath()+"/customerController?method=findAllByPage");
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

}
