package com.service;

import com.dao.StorageDao;
import com.pojo.Storage;
import java.util.List;

public class StorageService {
	private StorageDao dao = new StorageDao();
	
	public void delete(Storage storage) {
		dao.delete(storage);
	}

	public void update(Storage storage) {
		dao.update(storage);
	}

	public Storage findOne(Storage storage) {
		return dao.findOne(storage);
	}

	public void add(Storage storage) {
		dao.add(storage);
	}

	public List<Storage> findAll() {
		return dao.findAll();
	}
}
