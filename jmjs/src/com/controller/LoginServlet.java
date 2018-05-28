package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pojo.UsersDTO;
import com.service.LoginService;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private LoginService service = new LoginService();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		if("login".equals(method)) {
			this.login(req,resp);
		}else if("logout".equals(method)) {
			this.logout(req,resp);
		}
	}

	private void logout(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().invalidate();
		try {
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) {
		String uName = req.getParameter("uName");
		String uPwd = req.getParameter("uPwd");
		UsersDTO user = new UsersDTO();
		user.setuName(uName);
		user.setuPwd(uPwd);
		user = service.login(user);
		if(user != null) {
			//查询出当前用户的权限，放入session作用域
			user = service.queryAllPromission(user);
			HttpSession session = req.getSession();
			session.setAttribute("userInfo", user);
			try {
				resp.sendRedirect(req.getContextPath()+"/html/main.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				req.setAttribute("errorMsg",true);
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
