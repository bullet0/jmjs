package com.dao;

import com.pojo.StorageVO;

import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StorageDao {
	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql:///jmjs?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Hongkong","root","123456");
		} catch (ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void close(Connection conn,Statement stm,ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stm != null) {
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



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
	
}
