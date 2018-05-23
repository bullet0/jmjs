package com.dao;

import com.pojo.Goods;
import com.pojo.GoodsVO;
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


	public void deleteAll(String[] gIds) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("delete from goods where g_Id = ?");
			for (String gId : gIds) {
				ps.setObject(1, gId);
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

	public void update(Goods goods) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("update goods set g_id=?,g_name=?,g_produce=?,g_production_date=?,g_release_date=?,g_type=?,g_unit=?,g_remark=?,g_supplier=?,g_Advise_Price=?,g_Sale_Price=?,g_Promotion_Price=? where g_Id = ?");
			ps.setObject(1, goods.getgId());
			ps.setObject(2, goods.getgName());
			ps.setObject(3, goods.getgProduce());
			ps.setObject(4, goods.getgProductionDate());
			ps.setObject(5, goods.getgReleaseDate());
			ps.setObject(6, goods.getgType());
			ps.setObject(7, goods.getgUnit());
			ps.setObject(8, goods.getgRemark());
			ps.setObject(9, goods.getgSupplier());
			ps.setObject(10, goods.getgAdvisePrice());
			ps.setObject(11, goods.getgSalePrice());
			ps.setObject(12, goods.getgPromotionPrice());
			ps.setObject(13, goods.getgId());
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
				goods.setgProduce(rs.getString("g_produce"));
				goods.setgProductionDate(rs.getDate("g_production_date"));
				goods.setgReleaseDate(rs.getDate("g_release_date"));
				goods.setgType(rs.getString("g_type"));
				goods.setgUnit(rs.getString("g_unit"));
				goods.setgRemark(rs.getString("g_remark"));
				goods.setgSupplier(rs.getString("g_supplier"));
				goods.setgAdvisePrice(rs.getDouble("g_Advise_Price"));
				goods.setgSalePrice(rs.getDouble("g_Sale_Price"));
				goods.setgPromotionPrice(rs.getDouble("g_Promotion_Price"));
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

	public List<Goods> findAll(PageUtil page) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Goods> list = new ArrayList<Goods>();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select * from goods where g_name like ? limit ?,? ");
			ps.setString(1, "%"+page.getCondition()+"%");
			ps.setInt(2, (page.getCurPage() - 1)*page.getPageSize());
			ps.setInt(3, page.getPageSize());
			rs = ps.executeQuery();
			conn.commit();
			while (rs.next()) {
				Goods goods = new Goods();
				
				goods.setgId(rs.getInt("g_id"));
				
				goods.setgName(rs.getString("g_name"));
				
				goods.setgProduce(rs.getString("g_produce"));
				
				goods.setgProductionDate(rs.getDate("g_production_date"));
				
				goods.setgReleaseDate(rs.getDate("g_release_date"));
				
				goods.setgType(rs.getString("g_type"));
				
				goods.setgUnit(rs.getString("g_unit"));
				
				goods.setgRemark(rs.getString("g_remark"));
				
				goods.setgSupplier(rs.getString("g_supplier"));
				
				goods.setgAdvisePrice(rs.getDouble("g_Advise_Price"));
				goods.setgSalePrice(rs.getDouble("g_Sale_Price"));
				goods.setgPromotionPrice(rs.getDouble("g_Promotion_Price"));
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
				
				goods.setgProduce(rs.getString("g_produce"));
				
				goods.setgProductionDate(rs.getDate("g_production_date"));
				
				goods.setgReleaseDate(rs.getDate("g_release_date"));
				
				goods.setgType(rs.getString("g_type"));
				
				goods.setgUnit(rs.getString("g_unit"));
				
				goods.setgRemark(rs.getString("g_remark"));
				
				goods.setgSupplier(rs.getString("g_supplier"));
				
				goods.setgAdvisePrice(rs.getDouble("g_Advise_Price"));
				goods.setgSalePrice(rs.getDouble("g_Sale_Price"));
				goods.setgPromotionPrice(rs.getDouble("g_Promotion_Price"));
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
			ps = conn.prepareStatement("insert into goods(g_id,g_name,g_produce,g_production_date,g_release_date,g_type,g_unit,g_remark,g_supplier,g_Advise_Price,g_Sale_Price,g_Promotion_Price) values (?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setObject(1,null);
			ps.setObject(2, goods.getgName());
			ps.setObject(3, goods.getgProduce());
			ps.setObject(4, goods.getgProductionDate());
			ps.setObject(5, goods.getgReleaseDate());
			ps.setObject(6, goods.getgType());
			ps.setObject(7, goods.getgUnit());
			ps.setObject(8, goods.getgRemark());
			ps.setObject(9, goods.getgSupplier());
			ps.setObject(10, goods.getgAdvisePrice());
			ps.setObject(11, goods.getgSalePrice());
			ps.setObject(12, goods.getgPromotionPrice());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}

	public int getCount(PageUtil page) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select count(1) from goods where g_name like ?");
			
			ps.setObject(1, "%"+page.getCondition()+"%");
			
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

	public double getAdvisePrice(String gId) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("SELECT g_advise_price FROM `goods` WHERE g_id=?");
			ps.setObject(1,gId);
			rs = ps.executeQuery();
			conn.commit();
			double price = -1;
			while (rs.next()) {
				price = rs.getDouble(1);
				
			}
			
			return price;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,rs);
		}
		return -1;
	}

	public List<Goods> findAllGoodIdAndName() {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Goods> list = new ArrayList<Goods>();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select g_id,g_name from goods");
			rs = ps.executeQuery();
			conn.commit();
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setgId(rs.getInt("g_id"));
				goods.setgName(rs.getString("g_name"));
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

	public List<GoodsVO> findAllPrice() {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("SELECT g.g_id,g.g_name,g.g_supplier,g.g_unit,s.num ,g.g_advise_price,g.g_sale_price,g.g_promotion_price FROM goods g LEFT JOIN (SELECT `s_id`, `s_goods_name`, `s_supplier_name`, ROUND(SUM(`s_price`*`s_stock_num`) / SUM(`s_stock_num`),2) avg_price,`s_type`, SUM(`s_stock_num`) num,SUM(`s_price`*`s_stock_num`) sum_price  FROM `storage` GROUP BY s_goods_name) s ON g.g_name=s.s_goods_name");
			rs = ps.executeQuery();
			conn.commit();
			while (rs.next()) {
				GoodsVO goods = new GoodsVO();
				goods.setgId(rs.getInt("g_id"));
				goods.setgName(rs.getString("g_name"));
				goods.setgSupplier(rs.getString("g_supplier"));
				goods.setgUnit(rs.getString("g_unit"));
				goods.setStorageNumber(rs.getInt("num"));
				goods.setgAdvisePrice(rs.getDouble("g_Advise_Price"));
				goods.setgSalePrice(rs.getDouble("g_Sale_Price"));
				goods.setgPromotionPrice(rs.getDouble("g_Promotion_Price"));
				
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

	public int changePrice(Goods goods) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("update goods set g_Advise_Price=?,g_Sale_Price=?,g_Promotion_Price=? where g_Id = ?");
			ps.setObject(1, goods.getgAdvisePrice());
			ps.setObject(2, goods.getgSalePrice());
			ps.setObject(3, goods.getgPromotionPrice());
			ps.setObject(4, goods.getgId());
			int i = ps.executeUpdate();
			conn.commit();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
		return 0;
	}
}
