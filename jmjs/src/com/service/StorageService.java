package com.service;

import com.dao.StorageDao;
import com.pojo.StorageVO;
import com.pojo.Supplier;
import com.util.PageUtil;

import java.util.List;

public class StorageService {
	private StorageDao dao = new StorageDao();
	
	public List<StorageVO> findAll() {
		return dao.findAll();
	}

	public int getDangerCount() {
		// TODO Auto-generated method stub
		return dao.getDangerCount();
	}

	public PageUtil findAllByPage(PageUtil page) {
		int count = dao.getTotalCount(page);
		page.setTotalCount(count);
		
		page.setTotalPage();
		
		List<StorageVO> list = dao.findAllByPage(page);
		page.setList(list);
		return page;
	}
	
	
}
