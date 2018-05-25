package com.service;

import com.dao.GoodsDao;
import com.pojo.Goods;
import com.pojo.GoodsVO;
import com.util.PageUtil;

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

	public List<Goods> findAll(PageUtil page) {
		
		
		List<Goods> list = dao.findAll();
		return list;
	}
	public List<Goods> findAll() {
		
		return dao.findAll();
	}
	public void deleteAll(String[] gIds) {
		dao.deleteAll(gIds);
	}

	public double getAdvisePrice(String gId) {
		// TODO Auto-generated method stub
		return dao.getAdvisePrice(gId);
	}

	public List<Goods> findAllGoodIdAndName() {
		// TODO Auto-generated method stub
		return dao.findAllGoodIdAndName();
	}


	public int changePrice(Goods goods) {
		return dao.changePrice(goods);
	}

	public PageUtil findAllByPage(PageUtil page) {
		int count = dao.getTotalCount(page);
		page.setTotalCount(count);
		
		page.setTotalPage();
		
		List<Goods> list = dao.findAllByPage(page);
		page.setList(list);
		return page;
	}

	public PageUtil findAllPriceByPage(PageUtil page) {
		int count = dao.getPriceTotalCount(page);
		page.setTotalCount(count);
		
		page.setTotalPage();
		
		List<GoodsVO> list = dao.findAllPriceByPage(page);
		page.setList(list);
		return page;
	}
	
}
