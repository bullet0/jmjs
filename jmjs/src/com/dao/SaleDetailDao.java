package com.dao;

import com.pojo.SaleDetail;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SaleDetailDao {
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

	public void delete(SaleDetail saleDetail) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("delete from sale_Detail where sd_Id = ?");
			
			ps.setInt(1, saleDetail.getSdId());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}

	public void update(SaleDetail saleDetail) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("update sale_Detail set sd_id=?,sale_id=?,goods_id=?,sale_price=?,sale_number=?,purchase_price=? where sd_Id = ?");
			ps.setObject(1, saleDetail.getSdId());
			ps.setObject(2, saleDetail.getSaleId());
			ps.setObject(3, saleDetail.getGoodsId());
			ps.setObject(4, saleDetail.getSalePrice());
			ps.setObject(5, saleDetail.getSaleNumber());
			ps.setObject(6, saleDetail.getPurchasePrice());
			ps.setObject(7, saleDetail.getSdId());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}

	public SaleDetail findOne(SaleDetail saleDetail1) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select * from sale_Detail where sd_Id=?");
			
			ps.setObject(1, saleDetail1.getSdId());
			
			rs = ps.executeQuery();
			conn.commit();
			SaleDetail saleDetail = null;
			while (rs.next()) {
				saleDetail = new SaleDetail();
				saleDetail.setSdId(rs.getInt("sd_id"));
				saleDetail.setSaleId(rs.getInt("sale_id"));
				saleDetail.setGoodsId(rs.getInt("goods_id"));
				saleDetail.setSalePrice(rs.getInt("sale_price"));
				saleDetail.setSaleNumber(rs.getInt("sale_number"));
				saleDetail.setPurchasePrice(rs.getInt("purchase_price"));
			}
			return saleDetail;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,rs);
		}
		return null;
	}

	public List<SaleDetail> findAll() {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<SaleDetail> list = new ArrayList<SaleDetail>();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select * from sale_Detail");
			rs = ps.executeQuery();
			conn.commit();
			while (rs.next()) {
				SaleDetail saleDetail = new SaleDetail();
				
				saleDetail.setSdId(rs.getInt("sd_id"));
				
				saleDetail.setSaleId(rs.getInt("sale_id"));
				
				saleDetail.setGoodsId(rs.getInt("goods_id"));
				
				saleDetail.setSalePrice(rs.getInt("sale_price"));
				
				saleDetail.setSaleNumber(rs.getInt("sale_number"));
				
				saleDetail.setPurchasePrice(rs.getInt("purchase_price"));
				list.add(saleDetail);
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
	
	public void add(SaleDetail saleDetail) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("insert into sale_Detail(sd_id,sale_id,goods_id,sale_price,sale_number,purchase_price) values (?,?,?,?,?,?)");
			ps.setObject(1,null);
			ps.setObject(2, saleDetail.getSaleId());
			ps.setObject(3, saleDetail.getGoodsId());
			ps.setObject(4, saleDetail.getSalePrice());
			ps.setObject(5, saleDetail.getSaleNumber());
			ps.setObject(6, saleDetail.getPurchasePrice());
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
