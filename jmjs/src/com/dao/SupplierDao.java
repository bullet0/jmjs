package com.dao;

import com.pojo.Supplier;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SupplierDao {
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

	public void delete(Supplier supplier) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("delete from supplier where s_Id = ?");
			
			ps.setInt(1, supplier.getsId());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}


	public void deleteAll(String[] sIds) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("delete from supplier where s_Id = ?");
			for (String sId : sIds) {
				ps.setObject(1, sId);
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

	public void update(Supplier supplier) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("update supplier set s_id=?,s_name=?,s_phone=?,s_address=?,s_email=?,s_con_mobile=?,s_con_name=?,s_post_code=?,s_account=? where s_Id = ?");
			ps.setObject(1, supplier.getsId());
			ps.setObject(2, supplier.getsName());
			ps.setObject(3, supplier.getsPhone());
			ps.setObject(4, supplier.getsAddress());
			ps.setObject(5, supplier.getsEmail());
			ps.setObject(6, supplier.getsConMobile());
			ps.setObject(7, supplier.getsConName());
			ps.setObject(8, supplier.getsPostCode());
			ps.setObject(9, supplier.getsAccount());
			ps.setObject(10, supplier.getsId());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}

	public Supplier findOne(Supplier supplier1) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select * from supplier where s_Id=?");
			
			ps.setObject(1, supplier1.getsId());
			
			rs = ps.executeQuery();
			conn.commit();
			Supplier supplier = null;
			while (rs.next()) {
				supplier = new Supplier();
				supplier.setsId(rs.getInt("s_id"));
				supplier.setsName(rs.getString("s_name"));
				supplier.setsPhone(rs.getString("s_phone"));
				supplier.setsAddress(rs.getString("s_address"));
				supplier.setsEmail(rs.getString("s_email"));
				supplier.setsConMobile(rs.getString("s_con_mobile"));
				supplier.setsConName(rs.getString("s_con_name"));
				supplier.setsPostCode(rs.getString("s_post_code"));
				supplier.setsAccount(rs.getString("s_account"));
			}
			return supplier;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,rs);
		}
		return null;
	}

	public List<Supplier> findAll() {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Supplier> list = new ArrayList<Supplier>();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select * from supplier");
			rs = ps.executeQuery();
			conn.commit();
			while (rs.next()) {
				Supplier supplier = new Supplier();
				
				supplier.setsId(rs.getInt("s_id"));
				
				supplier.setsName(rs.getString("s_name"));
				
				supplier.setsPhone(rs.getString("s_phone"));
				
				supplier.setsAddress(rs.getString("s_address"));
				
				supplier.setsEmail(rs.getString("s_email"));
				
				supplier.setsConMobile(rs.getString("s_con_mobile"));
				
				supplier.setsConName(rs.getString("s_con_name"));
				
				supplier.setsPostCode(rs.getString("s_post_code"));
				
				supplier.setsAccount(rs.getString("s_account"));
				list.add(supplier);
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
	
	public void add(Supplier supplier) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("insert into supplier(s_id,s_name,s_phone,s_address,s_email,s_con_mobile,s_con_name,s_post_code,s_account) values (?,?,?,?,?,?,?,?,?)");
			ps.setObject(1,null);
			ps.setObject(2, supplier.getsName());
			ps.setObject(3, supplier.getsPhone());
			ps.setObject(4, supplier.getsAddress());
			ps.setObject(5, supplier.getsEmail());
			ps.setObject(6, supplier.getsConMobile());
			ps.setObject(7, supplier.getsConName());
			ps.setObject(8, supplier.getsPostCode());
			ps.setObject(9, supplier.getsAccount());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}

	public List<Supplier> findAllSupIdAndName() {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Supplier> list = new ArrayList<Supplier>();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select s_id,s_name from supplier");
			rs = ps.executeQuery();
			conn.commit();
			while (rs.next()) {
				Supplier supplier = new Supplier();
				
				supplier.setsId(rs.getInt("s_id"));
				
				supplier.setsName(rs.getString("s_name"));
				list.add(supplier);
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
