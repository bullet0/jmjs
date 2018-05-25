package com.dao;

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

public class SupplierDao extends BaseDao  {
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


	public int getTotalCount(PageUtil page) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select count(*) from supplier where LOCATE(?, `s_name`)>0 or LOCATE(?, `s_phone`) or LOCATE(?, `s_address`) or LOCATE(?, `s_email`) or LOCATE(?, `s_con_mobile`)  or LOCATE(?, `s_con_name`) ");
			
			ps.setObject(1, page.getCondition());
			ps.setObject(2, page.getCondition());
			ps.setObject(3, page.getCondition());
			ps.setObject(4, page.getCondition());
			ps.setObject(5, page.getCondition());
			ps.setObject(6, page.getCondition());
			
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


	public List<Supplier> findAllByPage(PageUtil page) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Supplier> list = new ArrayList<Supplier>();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select * from supplier   where LOCATE(?, `s_name`)>0 or LOCATE(?, `s_phone`) or LOCATE(?, `s_address`) or LOCATE(?, `s_email`) or LOCATE(?, `s_con_mobile`)  or LOCATE(?, `s_con_name`) limit ?,?");
			ps.setObject(1, page.getCondition());
			ps.setObject(2, page.getCondition());
			ps.setObject(3, page.getCondition());
			ps.setObject(4, page.getCondition());
			ps.setObject(5, page.getCondition());
			ps.setObject(6, page.getCondition());
			ps.setObject(7, (page.getCurPage()-1)*page.getPageSize());
			ps.setObject(8, page.getPageSize());
		
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
}
