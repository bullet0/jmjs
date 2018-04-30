package com.dao;

import com.pojo.Goods;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GoodsDao {
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

	public void delete(Goods goods) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("delete from goods where g_Id = ?");
			
			ps.setInt(1, goods.getgId());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}

	public void update(Goods goods) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("update goods set g_id=?,g_name=?,g_number=?,g_produce=?,g_production_date=?,g_release_date=?,g_type=?,g_unit=?,g_remark=?,g_supplier=? where g_Id = ?");
			ps.setObject(1, goods.getgId());
			ps.setObject(2, goods.getgName());
			ps.setObject(3, goods.getgNumber());
			ps.setObject(4, goods.getgProduce());
			ps.setObject(5, goods.getgProductionDate());
			ps.setObject(6, goods.getgReleaseDate());
			ps.setObject(7, goods.getgType());
			ps.setObject(8, goods.getgUnit());
			ps.setObject(9, goods.getgRemark());
			ps.setObject(10, goods.getgSupplier());
			ps.setObject(11, goods.getgId());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}

	public Goods findOne(Goods goods1) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select * from goods where g_Id=?");
			
			ps.setObject(1, goods1.getgId());
			
			rs = ps.executeQuery();
			conn.commit();
			Goods goods = null;
			while (rs.next()) {
				goods = new Goods();
				goods.setgId(rs.getInt("g_id"));
				goods.setgName(rs.getString("g_name"));
				goods.setgNumber(rs.getInt("g_number"));
				goods.setgProduce(rs.getString("g_produce"));
				goods.setgProductionDate(rs.getDate("g_production_date"));
				goods.setgReleaseDate(rs.getDate("g_release_date"));
				goods.setgType(rs.getString("g_type"));
				goods.setgUnit(rs.getString("g_unit"));
				goods.setgRemark(rs.getString("g_remark"));
				goods.setgSupplier(rs.getString("g_supplier"));
			}
			return goods;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,rs);
		}
		return null;
	}

	public List<Goods> findAll() {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Goods> list = new ArrayList<Goods>();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select * from goods");
			rs = ps.executeQuery();
			conn.commit();
			while (rs.next()) {
				Goods goods = new Goods();
				
				goods.setgId(rs.getInt("g_id"));
				
				goods.setgName(rs.getString("g_name"));
				
				goods.setgNumber(rs.getInt("g_number"));
				
				goods.setgProduce(rs.getString("g_produce"));
				
				goods.setgProductionDate(rs.getDate("g_production_date"));
				
				goods.setgReleaseDate(rs.getDate("g_release_date"));
				
				goods.setgType(rs.getString("g_type"));
				
				goods.setgUnit(rs.getString("g_unit"));
				
				goods.setgRemark(rs.getString("g_remark"));
				
				goods.setgSupplier(rs.getString("g_supplier"));
				list.add(goods);
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
	
	public void add(Goods goods) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("insert into goods(g_id,g_name,g_number,g_produce,g_production_date,g_release_date,g_type,g_unit,g_remark,g_supplier) values (?,?,?,?,?,?,?,?,?,?)");
			ps.setObject(1,null);
			ps.setObject(2, goods.getgName());
			ps.setObject(3, goods.getgNumber());
			ps.setObject(4, goods.getgProduce());
			ps.setObject(5, goods.getgProductionDate());
			ps.setObject(6, goods.getgReleaseDate());
			ps.setObject(7, goods.getgType());
			ps.setObject(8, goods.getgUnit());
			ps.setObject(9, goods.getgRemark());
			ps.setObject(10, goods.getgSupplier());
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
