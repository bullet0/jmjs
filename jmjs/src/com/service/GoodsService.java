package com.service;

import com.dao.GoodsDao;
import com.pojo.Goods;
import java.util.List;

public class GoodsService {
	private GoodsDao dao = new GoodsDao();
	
	public void delete(Goods goods) {
		dao.delete(goods);
	}

	public void update(Goods goods) {
		dao.update(goods);
	}

	public Goods findOne(Goods goods) {
		return dao.findOne(goods);
	}

	public void add(Goods goods) {
		dao.add(goods);
	}

	public List<Goods> findAll() {
		return dao.findAll();
	}
	public void deleteAll(String[] gIds) {
		dao.deleteAll(gIds);
	}
	
}
