package com.dao;

import com.pojo.Purchase;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PurchaseDao {
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

	public void delete(Purchase purchase) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("delete from purchase where p_Id = ?");
			
			ps.setInt(1, purchase.getpId());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}


	public void deleteAll(String[] pIds) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("delete from purchase where p_Id = ?");
			for (String pId : pIds) {
				ps.setObject(1, pId);
				ps.addBatch();
			}
			ps.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}

	public void update(Purchase purchase) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("update purchase set p_id=?,depot_id=?,goods_id=?,goods_price=?,goods_number=? where p_Id = ?");
			ps.setObject(1, purchase.getpId());
			ps.setObject(2, purchase.getDepotId());
			ps.setObject(3, purchase.getGoodsId());
			ps.setObject(4, purchase.getGoodsPrice());
			ps.setObject(5, purchase.getGoodsNumber());
			ps.setObject(6, purchase.getpId());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}

	public Purchase findOne(Purchase purchase1) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select * from purchase where p_Id=?");
			
			ps.setObject(1, purchase1.getpId());
			
			rs = ps.executeQuery();
			conn.commit();
			Purchase purchase = null;
			while (rs.next()) {
				purchase = new Purchase();
				purchase.setpId(rs.getInt("p_id"));
//				purchase.setDepotId(rs.getInt("depot_id"));
//				purchase.setGoodsId(rs.getInt("goods_id"));
				purchase.setGoodsPrice(rs.getDouble("goods_price"));
				purchase.setGoodsNumber(rs.getInt("goods_number"));
			}
			return purchase;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,rs);
		}
		return null;
	}

	public List<Purchase> findAll() {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Purchase> list = new ArrayList<Purchase>();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select * from purchase");
			rs = ps.executeQuery();
			conn.commit();
			while (rs.next()) {
				Purchase purchase = new Purchase();
				
				purchase.setpId(rs.getInt("p_id"));
				
//				purchase.setDepotId(rs.getInt("depot_id"));
//				
//				purchase.setGoodsId(rs.getInt("goods_id"));
				
				purchase.setGoodsPrice(rs.getDouble("goods_price"));
				
				purchase.setGoodsNumber(rs.getInt("goods_number"));
				list.add(purchase);
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
	
	public void add(Purchase purchase) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("insert into purchase(p_id,depot_id,goods_id,goods_price,goods_number) values (?,?,?,?,?)");
			ps.setObject(1,null);
			ps.setObject(2, purchase.getDepotId());
			ps.setObject(3, purchase.getGoodsId());
			ps.setObject(4, purchase.getGoodsPrice());
			ps.setObject(5, purchase.getGoodsNumber());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}
}
