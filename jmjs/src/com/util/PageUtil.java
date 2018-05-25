package com.util;

import java.util.List;

public class PageUtil {
	private Integer pageSize = 8;
	private Integer totalCount;
	private Integer totalPage;
	private Integer begin;
	private Integer end;
	private Integer curPage;
	private String condition;
	private List list;
	
	
	
	
	/**
	 * 页码显示5条，如果不足五页，则有几页显示几页，
	 * 			否则只显示5页
	 * 			显示5页时，只显示当前页-2到当前页+2的页码
	 */
	public Integer getBegin() {
		if(this.totalPage <= 5) {
			this.begin = 1;
		}else {
			if(this.curPage - 2 < 1 ) {
				this.begin = 1;
			}else {
				this.begin = this.curPage - 2;
			}
		}
		return this.begin;
		
	}
	public int getEnd() {
		if(this.totalPage <= 5) {
			this.end = this.totalPage;
		}else {
			if(this.curPage + 2 > this.totalPage ) {
				this.end = this.totalPage;
			}else {
				this.end = this.curPage + 2;
			}
		}
		return this.end;
	}


	@Override
	public String toString() {
		return "PageUtil [pageSize=" + pageSize + ", totalCount=" + totalCount + ", totalPage=" + totalPage
				+ ", curPage=" + curPage + ", condition=" + condition + ", list=" + list + "]";
	}
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
