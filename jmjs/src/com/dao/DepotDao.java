package com.dao;

import com.pojo.Depot;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DepotDao {
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

	public void delete(Depot depot) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("delete from depot where d_Id = ?");
			
			ps.setInt(1, depot.getdId());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}

	public void update(Depot depot) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("update depot set d_id=?,d_variety_num=?,d_total_price=?,d_date=?,d_settlement_way=?,supplier_id=?,supplier_name=? where d_Id = ?");
			ps.setObject(1, depot.getdId());
			ps.setObject(2, depot.getdVarietyNum());
			ps.setObject(3, depot.getdTotalPrice());
			ps.setObject(4, depot.getdDate());
			ps.setObject(5, depot.getdSettlementWay());
			ps.setObject(6, depot.getSupplierId());
			ps.setObject(7, depot.getSupplierName());
			ps.setObject(8, depot.getdId());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}

	public Depot findOne(Depot depot1) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select * from depot where d_Id=?");
			
			ps.setObject(1, depot1.getdId());
			
			rs = ps.executeQuery();
			conn.commit();
			Depot depot = null;
			while (rs.next()) {
				depot = new Depot();
				depot.setdId(rs.getInt("d_id"));
				depot.setdVarietyNum(rs.getInt("d_variety_num"));
				depot.setdTotalPrice(rs.getInt("d_total_price"));
				depot.setdDate(rs.getString("d_date"));
				depot.setdSettlementWay(rs.getString("d_settlement_way"));
				depot.setSupplierId(rs.getInt("supplier_id"));
				depot.setSupplierName(rs.getString("supplier_name"));
			}
			return depot;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,rs);
		}
		return null;
	}

	public List<Depot> findAll() {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Depot> list = new ArrayList<Depot>();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select * from depot");
			rs = ps.executeQuery();
			conn.commit();
			while (rs.next()) {
				Depot depot = new Depot();
				
				depot.setdId(rs.getInt("d_id"));
				
				depot.setdVarietyNum(rs.getInt("d_variety_num"));
				
				depot.setdTotalPrice(rs.getInt("d_total_price"));
				
				depot.setdDate(rs.getString("d_date"));
				
				depot.setdSettlementWay(rs.getString("d_settlement_way"));
				
				depot.setSupplierId(rs.getInt("supplier_id"));
				
				depot.setSupplierName(rs.getString("supplier_name"));
				list.add(depot);
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
	
	public void add(Depot depot) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("insert into depot(d_id,d_variety_num,d_total_price,d_date,d_settlement_way,supplier_id,supplier_name) values (?,?,?,?,?,?,?)");
			ps.setObject(1,null);
			ps.setObject(2, depot.getdVarietyNum());
			ps.setObject(3, depot.getdTotalPrice());
			ps.setObject(4, depot.getdDate());
			ps.setObject(5, depot.getdSettlementWay());
			ps.setObject(6, depot.getSupplierId());
			ps.setObject(7, depot.getSupplierName());
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
