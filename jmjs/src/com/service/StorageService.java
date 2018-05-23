package com.service;

import com.dao.StorageDao;
import com.pojo.StorageVO;

import java.util.List;

public class StorageService {
	private StorageDao dao = new StorageDao();
	
	public List<StorageVO> findAll() {
		return dao.findAll();
	}
	
	
}
