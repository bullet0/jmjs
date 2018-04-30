package com.service;

import com.dao.SupplierDao;
import com.pojo.Supplier;
import java.util.List;

public class SupplierService {
	private SupplierDao dao = new SupplierDao();
	
	public void delete(Supplier supplier) {
		dao.delete(supplier);
	}

	public void update(Supplier supplier) {
		dao.update(supplier);
	}

	public Supplier findOne(Supplier supplier) {
		return dao.findOne(supplier);
	}

	public void add(Supplier supplier) {
		dao.add(supplier);
	}

	public List<Supplier> findAll() {
		return dao.findAll();
	}
}
