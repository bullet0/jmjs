package com.dao;

import com.pojo.Customer;
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

public class CustomerDao extends BaseDao {
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


	public List<Customer> findAllCusIdAndName() {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Customer> list = new ArrayList<Customer>();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("SELECT c_id,c_name FROM customer");
			rs = ps.executeQuery();
			conn.commit();
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setcId(rs.getInt("c_id"));
				customer.setcName(rs.getString("c_name"));
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


	public int getTotalCount(PageUtil page) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("SELECT count(*) c FROM customer where LOCATE(?, `c_name`)>0 or LOCATE(?, `c_phone`)>0 or LOCATE(?, `c_address`)>0 or LOCATE(?, `c_email`)>0 or LOCATE(?, `c_con_mobile`)>0  or LOCATE(?, `c_con_name`)>0");
			ps.setString(1, page.getCondition());
			ps.setString(2, page.getCondition());
			ps.setString(3, page.getCondition());
			ps.setString(4, page.getCondition());
			ps.setString(5, page.getCondition());
			ps.setString(6, page.getCondition());
			rs = ps.executeQuery();
			conn.commit();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("c");
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


	public List<Customer> findAllByPage(PageUtil page) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Customer> list = new ArrayList<Customer>();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select * from customer where LOCATE(?, `c_name`)>0 or LOCATE(?, `c_phone`)>0 or LOCATE(?, `c_address`)>0 or LOCATE(?, `c_email`)>0 or LOCATE(?, `c_con_mobile`)>0  or LOCATE(?, `c_con_name`)>0 limit ?,?");
			ps.setString(1, page.getCondition());
			ps.setString(2, page.getCondition());
			ps.setString(3, page.getCondition());
			ps.setString(4, page.getCondition());
			ps.setString(5, page.getCondition());
			ps.setString(6, page.getCondition());
			ps.setInt(7, (page.getCurPage()-1)*page.getPageSize());
			ps.setInt(8, page.getPageSize());
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
}
