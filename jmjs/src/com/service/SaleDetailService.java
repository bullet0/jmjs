package com.service;

import com.dao.SaleDetailDao;
import com.pojo.SaleDetail;
import java.util.List;

public class SaleDetailService {
	private SaleDetailDao dao = new SaleDetailDao();
	
	public void delete(SaleDetail saleDetail) {
		dao.delete(saleDetail);
	}

	public void update(SaleDetail saleDetail) {
		dao.update(saleDetail);
	}

	public SaleDetail findOne(SaleDetail saleDetail) {
		return dao.findOne(saleDetail);
	}

	public void add(SaleDetail saleDetail) {
		dao.add(saleDetail);
	}

	public List<SaleDetail> findAll() {
		return dao.findAll();
	}
}
