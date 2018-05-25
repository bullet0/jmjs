package com.pojo;

import java.sql.Date;

public class SaleDetail{

	private Integer sdId;
	private Sale saleId;
	private Goods goodsId;
	private Double salePrice;
	private Integer saleNumber;

	public Integer getSdId() {
		return sdId;
	}

	public void setSdId(Integer sdId) {
		this.sdId = sdId;
	}
	
	
	/**
	 * @return the saleId
	 */
	public Sale getSaleId() {
		return saleId;
	}

	/**
	 * @param saleId the saleId to set
	 */
	public void setSaleId(Sale saleId) {
		this.saleId = saleId;
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
	 * @return the salePrice
	 */
	public Double getSalePrice() {
		return salePrice;
	}

	/**
	 * @param salePrice the salePrice to set
	 */
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Integer getSaleNumber() {
		return saleNumber;
	}

	public void setSaleNumber(Integer saleNumber) {
		this.saleNumber = saleNumber;
	}

	
}









