package com.service;

import com.dao.PurchaseDao;
import com.pojo.Purchase;
import java.util.List;

public class PurchaseService {
	private PurchaseDao dao = new PurchaseDao();
	
	public void delete(Purchase purchase) {
		dao.delete(purchase);
	}

	public void update(Purchase purchase) {
		dao.update(purchase);
	}

	public Purchase findOne(Purchase purchase) {
		return dao.findOne(purchase);
	}

	public void add(Purchase purchase) {
		dao.add(purchase);
	}

	public List<Purchase> findAll() {
		return dao.findAll();
	}
	public void deleteAll(String[] pIds) {
		dao.deleteAll(pIds);
	}
	
}
