package com.dao;

import com.pojo.Customer;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDao {
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

	public void delete(Customer customer) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("delete from customer where c_Id = ?");
			
			ps.setInt(1, customer.getcId());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}


	public void deleteAll(String[] cIds) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("delete from customer where c_Id = ?");
			for (String cId : cIds) {
				ps.setObject(1, cId);
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

	public void update(Customer customer) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("update customer set c_id=?,c_name=?,c_phone=?,c_address=?,c_email=?,c_con_mobile=?,c_con_name=?,c_post_code=?,c_account=? where c_Id = ?");
			ps.setObject(1, customer.getcId());
			ps.setObject(2, customer.getcName());
			ps.setObject(3, customer.getcPhone());
			ps.setObject(4, customer.getcAddress());
			ps.setObject(5, customer.getcEmail());
			ps.setObject(6, customer.getcConMobile());
			ps.setObject(7, customer.getcConName());
			ps.setObject(8, customer.getcPostCode());
			ps.setObject(9, customer.getcAccount());
			ps.setObject(10, customer.getcId());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}

	public Customer findOne(Customer customer1) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select * from customer where c_Id=?");
			
			ps.setObject(1, customer1.getcId());
			
			rs = ps.executeQuery();
			conn.commit();
			Customer customer = null;
			while (rs.next()) {
				customer = new Customer();
				customer.setcId(rs.getInt("c_id"));
				customer.setcName(rs.getString("c_name"));
				customer.setcPhone(rs.getString("c_phone"));
				customer.setcAddress(rs.getString("c_address"));
				customer.setcEmail(rs.getString("c_email"));
				customer.setcConMobile(rs.getString("c_con_mobile"));
				customer.setcConName(rs.getString("c_con_name"));
				customer.setcPostCode(rs.getString("c_post_code"));
				customer.setcAccount(rs.getString("c_account"));
			}
			return customer;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,rs);
		}
		return null;
	}

	public List<Customer> findAll() {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Customer> list = new ArrayList<Customer>();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select * from customer");
			rs = ps.executeQuery();
			conn.commit();
			while (rs.next()) {
				Customer customer = new Customer();
				
				customer.setcId(rs.getInt("c_id"));
				
				customer.setcName(rs.getString("c_name"));
				
				customer.setcPhone(rs.getString("c_phone"));
				
				customer.setcAddress(rs.getString("c_address"));
				
				customer.setcEmail(rs.getString("c_email"));
				
				customer.setcConMobile(rs.getString("c_con_mobile"));
				
				customer.setcConName(rs.getString("c_con_name"));
				
				customer.setcPostCode(rs.getString("c_post_code"));
				
				customer.setcAccount(rs.getString("c_account"));
				
				list.add(customer);
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
	
	public void add(Customer customer) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("insert into customer(c_id,c_name,c_phone,c_address,c_email,c_con_mobile,c_con_name,c_post_code,c_account) values (?,?,?,?,?,?,?,?,?)");
			ps.setObject(1,null);
			ps.setObject(2, customer.getcName());
			ps.setObject(3, customer.getcPhone());
			ps.setObject(4, customer.getcAddress());
			ps.setObject(5, customer.getcEmail());
			ps.setObject(6, customer.getcConMobile());
			ps.setObject(7, customer.getcConName());
			ps.setObject(8, customer.getcPostCode());
			ps.setObject(9, customer.getcAccount());
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
