package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import com.service.GoodsService;
import com.service.SupplierService;
import com.util.PageUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.pojo.Goods;
import com.pojo.GoodsVO;
import com.pojo.ResponseObj;
import com.pojo.Supplier;

import java.sql.Date;
import java.util.List;
import java.text.DecimalFormat;
import java.text.ParseException;
@WebServlet("/goodsController")
public class GoodsController extends HttpServlet{
	private GoodsService service = new GoodsService();
	private SupplierService supplierService = new SupplierService();
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
		}else if("getAdvisePrice".equals(method)){
			this.getAdvisePrice(req,resp);
		}else if("findAllPriceByPage".equals(method)){
			this.findAllPriceByPage(req,resp);
		}else if("changePrice".equals(method)){
			this.changePrice(req,resp);
		}else{
			System.out.println("用户请求路径有误");
			resp.sendRedirect("404.jsp");
		}
		
		
		
	}


	private void findAllPriceByPage(HttpServletRequest req, HttpServletResponse resp) {
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
		
		page = service.findAllPriceByPage(page);
		req.setAttribute("pageUtil",page);
		
		try {
			req.getRequestDispatcher("/html/goods_price_list.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
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
			req.getRequestDispatcher("/html/goods_list.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	private void changePrice(HttpServletRequest req, HttpServletResponse resp) {
		Goods goods = new Goods();
		Object value = null;
		
		
		String gId = req.getParameter("gId");
		if(gId != null){
			value = Integer.valueOf(gId);
		}
		
		goods.setgId((Integer)value);
		
		
		String gAdvisePrice = req.getParameter("gAdvisePrice");
		goods.setgAdvisePrice(Double.valueOf(gAdvisePrice));
		String gSalePrice = req.getParameter("gSalePrice");
		goods.setgSalePrice(Double.valueOf(gSalePrice));
		String gPromotionPrice = req.getParameter("gPromotionPrice");
		goods.setgPromotionPrice(Double.valueOf(gPromotionPrice));
		
		int count = service.changePrice(goods);
		try {
			PrintWriter out = resp.getWriter();
			if(count == 0) {
				ResponseObj obj = new ResponseObj();
				obj.setMsg("error");
				Gson gson = new Gson();
				String json = gson.toJson(obj);
				out.print(json);
			}else {
				ResponseObj obj = new ResponseObj();
				obj.setMsg("success");
				obj.setObject(goods);
				//在javascript中20和20.0其实是相等的，都是number类型，即javascript中没有整数类型一说。
				//就会把数字类型的值都转换成了Double类型(此时map中key为“id”的值是一个Double类型，为20.0)
				//我们将double转成字符串格式化一下，就好了
				Gson gson = new GsonBuilder().
				        registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
				            @Override
				            public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
				            	DecimalFormat fmt = new DecimalFormat("0.00"); 
				              return new JsonPrimitive(fmt.format(src));
				            }
				          }).create();
				String json = gson.toJson(obj);
				out.print(json);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}






	private void getAdvisePrice(HttpServletRequest req, HttpServletResponse resp) {
		String gId = req.getParameter("gId");
		double advisePrice = service.getAdvisePrice(gId);
		try {
			PrintWriter out = resp.getWriter();
			out.print(advisePrice);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteAll(HttpServletRequest req, HttpServletResponse resp) {
		String[] gIds = req.getParameterValues("gId");
		if(gIds != null) {
			service.deleteAll(gIds);
		} 
		try {
			resp.sendRedirect(req.getContextPath()+"/goodsController?method=findAllByPage");
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
		
		
		String gAdvisePrice = req.getParameter("gAdvisePrice");
		goods.setgAdvisePrice(Double.valueOf(gAdvisePrice));
		String gSalePrice = req.getParameter("gSalePrice");
		goods.setgSalePrice(Double.valueOf(gSalePrice));
		String gPromotionPrice = req.getParameter("gPromotionPrice");
		goods.setgPromotionPrice(Double.valueOf(gPromotionPrice));
		
		service.update(goods);
		try {
			resp.sendRedirect(req.getContextPath()+"/goodsController?method=findAllByPage");
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
		
		List<Supplier> suppliers = supplierService.findAll();
		req.setAttribute("suppliers", suppliers);
		try {
			req.getRequestDispatcher("/html/goods_update.jsp").forward(req, resp);
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
		
		String gAdvisePrice = req.getParameter("gAdvisePrice");
		if(gAdvisePrice != null && !gAdvisePrice.equals("")) {
			goods.setgAdvisePrice(Double.valueOf(gAdvisePrice));
		}
		String gSalePrice = req.getParameter("gSalePrice");
		if(gSalePrice != null  && !gSalePrice.equals("")) {
			goods.setgSalePrice(Double.valueOf(gSalePrice));
		}
		String gPromotionPrice = req.getParameter("gPromotionPrice");
		if(gPromotionPrice != null  && !gPromotionPrice.equals("")) {
			goods.setgPromotionPrice(Double.valueOf(gPromotionPrice));
		}
		
		service.add(goods);
		try {
			resp.sendRedirect(req.getContextPath()+"/goodsController?method=findAllByPage");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void toAdd(HttpServletRequest req, HttpServletResponse resp) {
		List<Supplier> suppliers = supplierService.findAll();
		req.setAttribute("suppliers", suppliers);
		
		try {
			req.getRequestDispatcher("/html/goods_add.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
