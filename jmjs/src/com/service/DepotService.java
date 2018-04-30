package com.service;

import com.dao.DepotDao;
import com.pojo.Depot;
import java.util.List;

public class DepotService {
	private DepotDao dao = new DepotDao();
	
	public void delete(Depot depot) {
		dao.delete(depot);
	}

	public void update(Depot depot) {
		dao.update(depot);
	}

	public Depot findOne(Depot depot) {
		return dao.findOne(depot);
	}

	public void add(Depot depot) {
		dao.add(depot);
	}

	public List<Depot> findAll() {
		return dao.findAll();
	}
}
