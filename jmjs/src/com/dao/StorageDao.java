package com.dao;

import com.pojo.StorageVO;
import com.pojo.Supplier;
import com.util.BaseDao;
import com.util.PageUtil;

import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StorageDao extends BaseDao  {

	public List<StorageVO> findAll() {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<StorageVO> list = new ArrayList<StorageVO>();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("SELECT `s_id`, `s_goods_name`, `s_supplier_name`, ROUND(SUM(`s_price`*`s_stock_num`) / SUM(`s_stock_num`),2) avg_price,`s_type`, SUM(`s_stock_num`) num,SUM(`s_price`*`s_stock_num`) sum_price  FROM `storage` GROUP BY s_goods_name ORDER BY num");
			rs = ps.executeQuery();
			conn.commit();
			while (rs.next()) {
				StorageVO storage = new StorageVO();
				
				storage.setsId(rs.getInt("s_id"));
				
				storage.setsGoodsName(rs.getString("s_goods_name"));
				
				storage.setsSupplierName(rs.getString("s_supplier_name"));
				
				storage.setsAvgPrice(rs.getDouble("avg_price"));
				
				storage.setsType(rs.getString("s_type"));
				
				storage.setsSumStockNum(rs.getInt("num"));
				storage.setsSumPrice(rs.getDouble("sum_price"));
				list.add(storage);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,rs);
		}
		return null;
	}

	public int getDangerCount() {
		// TODO Auto-generated method stub
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<StorageVO> list = new ArrayList<StorageVO>();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("SELECT COUNT(*) FROM (SELECT SUM(`s_stock_num`) num  FROM `storage`  GROUP BY s_goods_name HAVING num<100) a ");
			rs = ps.executeQuery();
			conn.commit();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,rs);
		}
		return 0;
	}

	public int getTotalCount(PageUtil page) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("SELECT COUNT(*) c FROM (SELECT * FROM `storage` WHERE LOCATE(?, `s_goods_name`) or  LOCATE(?, `s_supplier_name`) GROUP BY s_goods_name) a ");
			
			ps.setObject(1, page.getCondition());
			ps.setObject(2, page.getCondition());
			
			rs = ps.executeQuery();
			conn.commit();
			rs.next();
			
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,rs);
		}
		return -1;
	}

	public List<StorageVO> findAllByPage(PageUtil page) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<StorageVO> list = new ArrayList<StorageVO>();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("SELECT `s_id`, `s_goods_name`, `s_supplier_name`, ROUND(SUM(`s_price`*`s_stock_num`) / SUM(`s_stock_num`),2) avg_price,`s_type`, SUM(`s_stock_num`) num,SUM(`s_price`*`s_stock_num`) sum_price  FROM `storage` WHERE LOCATE(?, `s_goods_name`) or  LOCATE(?, `s_supplier_name`) GROUP BY s_goods_name ORDER BY num limit ?,?");
			ps.setObject(1, page.getCondition());
			ps.setObject(2, page.getCondition());
			ps.setObject(3, (page.getCurPage()-1)*page.getPageSize());
			ps.setObject(4, page.getPageSize());
			rs = ps.executeQuery();
			conn.commit();
			while (rs.next()) {
				StorageVO storage = new StorageVO();
				
				storage.setsId(rs.getInt("s_id"));
				
				storage.setsGoodsName(rs.getString("s_goods_name"));
				
				storage.setsSupplierName(rs.getString("s_supplier_name"));
				
				storage.setsAvgPrice(rs.getDouble("avg_price"));
				
				storage.setsType(rs.getString("s_type"));
				
				storage.setsSumStockNum(rs.getInt("num"));
				storage.setsSumPrice(rs.getDouble("sum_price"));
				list.add(storage);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,rs);
		}
		return null;
	}
	
}
