package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import com.service.StorageService;
import com.pojo.StorageVO;

import java.sql.Date;
import java.util.List;
import java.text.ParseException;
@WebServlet("/storageController")
public class StorageController extends HttpServlet{
	private StorageService service = new StorageService();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		String method = req.getParameter("method");
		
		if("findAll".equals(method)) {
			this.findAll(req,resp);
		}else{
			System.out.println("用户请求路径有误");
			resp.sendRedirect("404.jsp");
		}
		
		
		
	}

	private void findAll(HttpServletRequest req, HttpServletResponse resp) {
		List<StorageVO> storages = service.findAll();
		req.setAttribute("storages",storages);
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
	
}
