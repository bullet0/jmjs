package com.service;

import com.dao.SupplierDao;
import com.pojo.Goods;
import com.pojo.Supplier;
import com.util.PageUtil;

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
	public void deleteAll(String[] sIds) {
		dao.deleteAll(sIds);
	}

	public List<Supplier> findAllSupIdAndName() {
		// TODO Auto-generated method stub
		return dao.findAllSupIdAndName();
	}

	public PageUtil findAllByPage(PageUtil page) {
		int count = dao.getTotalCount(page);
		page.setTotalCount(count);
		
		page.setTotalPage();
		
		List<Supplier> list = dao.findAllByPage(page);
		page.setList(list);
		return page;
	}
	
}
