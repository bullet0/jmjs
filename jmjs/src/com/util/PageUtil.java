package com.util;

import java.util.List;

public class PageUtil {
	private Integer pageSize = 8;
	private Integer totalCount;
	private Integer totalPage;
	private Integer curPage;
	private String condition;
	private List list;
	
	
	
	/**
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}
	/**
	 * @param condition the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
	/**
	 * @return the list
	 */
	public List getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List list) {
		this.list = list;
	}
	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the totalCount
	 */
	public Integer getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return the totalPage
	 */
	public Integer getTotalPage() {
		return totalPage;
	}
	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage() {
		if(this.totalCount%this.pageSize == 0 ) {
			this.totalPage = this.totalCount/this.pageSize;
		}else {
			this.totalPage = this.totalCount/this.pageSize + 1;
		}
		
	}
	/**
	 * @return the curPage
	 */
	public Integer getCurPage() {
		return curPage;
	}
	/**
	 * @param curPage the curPage to set
	 */
	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}
	
	
}
