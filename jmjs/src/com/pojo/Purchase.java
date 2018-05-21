package com.pojo;

import java.sql.Date;

public class Purchase{

	private Integer pId;
	private Depot depotId;
	private Goods goodsId;
	private Double goodsPrice;
	private Integer goodsNumber;


	
	
	
	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}
	
	
	
	/**
	 * @return the depotId
	 */
	public Depot getDepotId() {
		return depotId;
	}

	/**
	 * @param depotId the depotId to set
	 */
	public void setDepotId(Depot depotId) {
		this.depotId = depotId;
	}

	/**
	 * @return the goodsId
	 */
	public Goods getGoodsId() {
		return goodsId;
	}

	/**
	 * @param goodsId the goodsId to set
	 */
	public void setGoodsId(Goods goodsId) {
		this.goodsId = goodsId;
	}

	
	/**
	 * @return the goodsPrice
	 */
	public Double getGoodsPrice() {
		return goodsPrice;
	}

	/**
	 * @param goodsPrice the goodsPrice to set
	 */
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Integer getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(Integer goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	
	
	
	
	

}









