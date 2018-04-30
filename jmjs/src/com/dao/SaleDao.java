package com.dao;

import com.pojo.Sale;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SaleDao {
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

	public void delete(Sale sale) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("delete from sale where s_Id = ?");
			
			ps.setInt(1, sale.getsId());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}

	public void update(Sale sale) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("update sale set s_id=?,s_variety_num=?,s_total_price=?,s_sale_date=?,s_settlement_way=?,customer_id=?,customer_name=? where s_Id = ?");
			ps.setObject(1, sale.getsId());
			ps.setObject(2, sale.getsVarietyNum());
			ps.setObject(3, sale.getsTotalPrice());
			ps.setObject(4, sale.getsSaleDate());
			ps.setObject(5, sale.getsSettlementWay());
			ps.setObject(6, sale.getCustomerId());
			ps.setObject(7, sale.getCustomerName());
			ps.setObject(8, sale.getsId());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}

	public Sale findOne(Sale sale1) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select * from sale where s_Id=?");
			
			ps.setObject(1, sale1.getsId());
			
			rs = ps.executeQuery();
			conn.commit();
			Sale sale = null;
			while (rs.next()) {
				sale = new Sale();
				sale.setsId(rs.getInt("s_id"));
				sale.setsVarietyNum(rs.getInt("s_variety_num"));
				sale.setsTotalPrice(rs.getInt("s_total_price"));
				sale.setsSaleDate(rs.getString("s_sale_date"));
				sale.setsSettlementWay(rs.getString("s_settlement_way"));
				sale.setCustomerId(rs.getInt("customer_id"));
				sale.setCustomerName(rs.getString("customer_name"));
			}
			return sale;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,rs);
		}
		return null;
	}

	public List<Sale> findAll() {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Sale> list = new ArrayList<Sale>();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select * from sale");
			rs = ps.executeQuery();
			conn.commit();
			while (rs.next()) {
				Sale sale = new Sale();
				
				sale.setsId(rs.getInt("s_id"));
				
				sale.setsVarietyNum(rs.getInt("s_variety_num"));
				
				sale.setsTotalPrice(rs.getInt("s_total_price"));
				
				sale.setsSaleDate(rs.getString("s_sale_date"));
				
				sale.setsSettlementWay(rs.getString("s_settlement_way"));
				
				sale.setCustomerId(rs.getInt("customer_id"));
				
				sale.setCustomerName(rs.getString("customer_name"));
				list.add(sale);
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
	
	public void add(Sale sale) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("insert into sale(s_id,s_variety_num,s_total_price,s_sale_date,s_settlement_way,customer_id,customer_name) values (?,?,?,?,?,?,?)");
			ps.setObject(1,null);
			ps.setObject(2, sale.getsVarietyNum());
			ps.setObject(3, sale.getsTotalPrice());
			ps.setObject(4, sale.getsSaleDate());
			ps.setObject(5, sale.getsSettlementWay());
			ps.setObject(6, sale.getCustomerId());
			ps.setObject(7, sale.getCustomerName());
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
