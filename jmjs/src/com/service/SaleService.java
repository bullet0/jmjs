package com.service;

import com.dao.SaleDao;
import com.pojo.Sale;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SaleService {
	private SaleDao dao = new SaleDao();
	
	public void delete(Sale sale) {
		dao.delete(sale);
	}

	public void update(Sale sale) {
		dao.update(sale);
	}

	public Sale findOne(Sale sale) {
		return dao.findOne(sale);
	}

	public void add(Sale sale) {
		String no = this.getNo();
		sale.setsNo(no);
		dao.add(sale);
	}

	public List<Sale> findAll() {
		return dao.findAll();
	}
	public void deleteAll(String[] sIds) {
		dao.deleteAll(sIds);
	}
	
	private String getNo() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmssSSS"); // 时间字符串产生方式
        String uid_pfix = "NO.S" + format.format(new Date());
        return uid_pfix;
	}
	
}
