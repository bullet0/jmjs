package com.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.dao.DepotDao;
import com.pojo.Depot;

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
		
		//生成订单号
		String dNo = this.getNo();
		depot.setdNo(dNo);
		
		dao.add(depot);
	}

	public List<Depot> findAll() {
		return dao.findAll();
	}
	public void deleteAll(String[] dIds) {
		dao.deleteAll(dIds);
	}
	private String getNo() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmssSSS"); // 时间字符串产生方式
        String uid_pfix = "NO." + format.format(new Date());
        return uid_pfix;
	}
}
