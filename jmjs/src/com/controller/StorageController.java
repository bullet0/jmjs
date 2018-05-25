package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import com.service.StorageService;
import com.util.PageUtil;
import com.google.gson.Gson;
import com.pojo.ResponseObj;
import com.pojo.StorageVO;

import java.sql.Date;
import java.util.List;
import java.text.ParseException;
@WebServlet("/storageController")
public class StorageController extends HttpServlet{
	private StorageService service = new StorageService();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String method = req.getParameter("method");
		
		if("findAllByPage".equals(method)) {
			this.findAllByPage(req,resp);
		}else if("getDangerCount".equals(method)) {
			this.getDangerCount(req,resp);
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
			req.getRequestDispatcher("/html/storage_list.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getDangerCount(HttpServletRequest req, HttpServletResponse resp) {
		int count = service.getDangerCount();
		try {
			PrintWriter out = resp.getWriter();
			ResponseObj obj = new ResponseObj();
			obj.setMsg("success");
			obj.setObject(count);
			Gson gson = new Gson();
			String json = gson.toJson(obj);
			out.print(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
