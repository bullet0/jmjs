package com.dao;

import com.pojo.Storage;
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

	public void delete(Storage storage) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("delete from storage where s_Id = ?");
			
			ps.setInt(1, storage.getsId());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}

	public void update(Storage storage) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("update storage set s_id=?,s_goods_name=?,s_supplier_name=?,s_price=?,s_type=?,s_stock_num=? where s_Id = ?");
			ps.setObject(1, storage.getsId());
			ps.setObject(2, storage.getsGoodsName());
			ps.setObject(3, storage.getsSupplierName());
			ps.setObject(4, storage.getsPrice());
			ps.setObject(5, storage.getsType());
			ps.setObject(6, storage.getsStockNum());
			ps.setObject(7, storage.getsId());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}

	public Storage findOne(Storage storage1) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select * from storage where s_Id=?");
			
			ps.setObject(1, storage1.getsId());
			
			rs = ps.executeQuery();
			conn.commit();
			Storage storage = null;
			while (rs.next()) {
				storage = new Storage();
				storage.setsId(rs.getInt("s_id"));
				storage.setsGoodsName(rs.getString("s_goods_name"));
				storage.setsSupplierName(rs.getString("s_supplier_name"));
				storage.setsPrice(rs.getInt("s_price"));
				storage.setsType(rs.getString("s_type"));
				storage.setsStockNum(rs.getInt("s_stock_num"));
			}
			return storage;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,rs);
		}
		return null;
	}

	public List<Storage> findAll() {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Storage> list = new ArrayList<Storage>();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select * from storage");
			rs = ps.executeQuery();
			conn.commit();
			while (rs.next()) {
				Storage storage = new Storage();
				
				storage.setsId(rs.getInt("s_id"));
				
				storage.setsGoodsName(rs.getString("s_goods_name"));
				
				storage.setsSupplierName(rs.getString("s_supplier_name"));
				
				storage.setsPrice(rs.getInt("s_price"));
				
				storage.setsType(rs.getString("s_type"));
				
				storage.setsStockNum(rs.getInt("s_stock_num"));
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
	
	public void add(Storage storage) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("insert into storage(s_id,s_goods_name,s_supplier_name,s_price,s_type,s_stock_num) values (?,?,?,?,?,?)");
			ps.setObject(1,null);
			ps.setObject(2, storage.getsGoodsName());
			ps.setObject(3, storage.getsSupplierName());
			ps.setObject(4, storage.getsPrice());
			ps.setObject(5, storage.getsType());
			ps.setObject(6, storage.getsStockNum());
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
