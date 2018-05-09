package com.dao;

import com.pojo.Depot;
import com.pojo.Goods;
import com.pojo.Purchase;

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
			
			ps.setString(1, depot.getdId());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}


	public void deleteAll(String[] dIds) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("DELETE FROM purchase WHERE depot_id=?");
			for (String dId : dIds) {
				ps.setObject(1, dId);
				ps.addBatch();
			}
			ps.executeBatch();
			ps = conn.prepareStatement("delete from depot where d_Id = ?");
			for (String dId : dIds) {
				ps.setObject(1, dId);
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
				depot.setdId(rs.getString("d_id"));
				depot.setdVarietyNum(rs.getInt("d_variety_num"));
				depot.setdTotalPrice(rs.getInt("d_total_price"));
				depot.setdDate(rs.getString("d_date"));
				depot.setdSettlementWay(rs.getString("d_settlement_way"));
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
			ps = conn.prepareStatement("SELECT * FROM `depot` d LEFT JOIN purchase p ON d.d_id = p.depot_id  LEFT JOIN goods g ON g.g_id = p.goods_id");
			rs = ps.executeQuery();
			conn.commit();
			while (rs.next()) {
				//先循环遍历之前数据，如果能添加进去，就不再重复添加了，如果添加不进去，说明要新增一个数据，这才新增数据
				//当前数据添加状态，默认false，未添加
				boolean flag = false;
				//先循环遍历之前数据
				for (Depot depot : list) {
					if(rs.getString("d_id").equals(depot.getdId())) {
						//如果当前遍历到的进货单已经被添加了，就只添加其他属性
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
						
						
						Purchase purchase = new Purchase();
						purchase.setpId(rs.getInt("p_id"));
						purchase.setGoodsPrice(rs.getInt("goods_price"));
						purchase.setGoodsNumber(rs.getInt("goods_number"));
						purchase.setGoodsId(goods);
						
						depot.getPurchases().add(purchase);
						flag = true;
						//填完就退出循环，因为当前这一条数据已经被添加了
						break;
					}
				}
				
				//当第一次循环时，list还没有数据，会进入这个方法
				//当上面循环过后，还没有添加，就进入这个方法
				if(list == null || list.size()==0 || flag == false) {
					//如果当前遍历到的进货单还没有被添加，添加进货单
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
					
					
					
					Purchase purchase = new Purchase();
					purchase.setpId(rs.getInt("p_id"));
					purchase.setGoodsPrice(rs.getInt("goods_price"));
					purchase.setGoodsNumber(rs.getInt("goods_number"));
					purchase.setGoodsId(goods);
					
					
					Depot depot1 = new Depot();
					depot1.setdId(rs.getString("d_id"));
					depot1.setdVarietyNum(rs.getInt("d_variety_num"));
					depot1.setdTotalPrice(rs.getInt("d_total_price"));
					depot1.setdDate(rs.getString("d_date"));
					depot1.setdSettlementWay(rs.getString("d_settlement_way"));
					depot1.getPurchases().add(purchase);
					list.add(depot1);
				}
				
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
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("insert into depot(d_id,d_variety_num,d_total_price,d_date,d_settlement_way) values (?,?,?,?,?)");
			ps.setObject(1, depot.getdId());
			ps.setObject(2, depot.getdVarietyNum());
			ps.setObject(3, depot.getdTotalPrice());
			ps.setObject(4, depot.getdDate());
			ps.setObject(5, depot.getdSettlementWay());
			ps.executeUpdate();
			
			List<Purchase> purchases = depot.getPurchases();
			for (int i = 0; i < purchases.size(); i++) {
				ps = conn.prepareStatement("insert into purchase(depot_id,goods_id,goods_price,goods_number) values (?,?,?,?)");
				ps.setObject(1,depot.getdId());
				ps.setObject(2,purchases.get(i).getGoodsId().getgId());
				ps.setObject(3, purchases.get(i).getGoodsPrice());
				ps.setObject(4, purchases.get(i).getGoodsNumber());
				ps.executeUpdate();
			}
			
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,rs);
		}
	}
}
