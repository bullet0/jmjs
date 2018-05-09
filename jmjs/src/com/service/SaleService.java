package com.service;

import com.dao.SaleDao;
import com.pojo.Sale;
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
		dao.add(sale);
	}

	public List<Sale> findAll() {
		return dao.findAll();
	}
	public void deleteAll(String[] sIds) {
		dao.deleteAll(sIds);
	}
	
}
