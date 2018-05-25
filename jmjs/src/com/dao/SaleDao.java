package com.dao;

import com.pojo.Depot;
import com.pojo.Goods;
import com.pojo.Purchase;
import com.pojo.Sale;
import com.pojo.SaleDetail;
import com.pojo.Supplier;
import com.util.BaseDao;

import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SaleDao extends BaseDao {
	
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


	public void deleteAll(String[] sIds) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			//删除并更新数据库中的订单明细和商品数量
			ps = conn.prepareStatement("{CALL update_storage_sale(?)}");
			for (String sId : sIds) {
				ps.setObject(1, sId);
				ps.addBatch();
			}
			ps.executeBatch();
			
			
			ps = conn.prepareStatement("delete from sale where s_Id = ?");
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

	public void update(Sale sale) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("update sale set s_no=?,s_variety_num=?,s_total_price=?,s_sale_date=?,s_settlement_way=?,customer_id=?,customer_name=(SELECT c_name FROM customer WHERE c_id=?) where s_Id = ?");
			ps.setObject(1, sale.getsNo());
			ps.setObject(2, sale.getsVarietyNum());
			ps.setObject(3, sale.getsTotalPrice());
			ps.setObject(4, sale.getsSaleDate());
			ps.setObject(5, sale.getsSettlementWay());
			ps.setObject(6, sale.getCustomerId().getcId());
			ps.setObject(7, sale.getCustomerId().getcId());
			ps.setObject(8, sale.getsId());
			ps.executeUpdate();
			
			
			//删除并更新数据库中的订单明细和商品数量
			ps = conn.prepareStatement("{CALL update_storage_sale(?)}");
			ps.setObject(1, sale.getsId());
			ps.executeUpdate();
			
			List<SaleDetail> saleDetails = sale.getSaleDetails();
			for (int i = 0; i < saleDetails.size(); i++) {
				ps = conn.prepareStatement("INSERT INTO `jmjs`.`sale_detail` (`sale_id`,`goods_id`,`sale_price`,`sale_number`) VALUES( (SELECT s_id FROM `sale` WHERE s_no=?),?,?,?)" );
				ps.setObject(1,sale.getsNo());
				ps.setObject(2,saleDetails.get(i).getGoodsId().getgId());
				ps.setObject(3, saleDetails.get(i).getSalePrice());
				ps.setObject(4, saleDetails.get(i).getSaleNumber());
				ps.executeUpdate();
				
				//同时添加数量商品库存表中数据
				
				ps = conn.prepareStatement("{CALL insert_storage_sale(?,?,?)}");
				ps.setObject(1, saleDetails.get(i).getGoodsId().getgId());
				ps.setObject(2, saleDetails.get(i).getSalePrice());
				ps.setObject(3, saleDetails.get(i).getSaleNumber());
				ps.executeUpdate();
				
			}
			
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}

	public Sale findOne(Sale sale2) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("SELECT * FROM `sale` s LEFT JOIN `sale_detail` sd ON s.s_id = sd.sale_id LEFT JOIN goods g ON g.g_id = sd.goods_id where s.s_id=? order by s.s_sale_date ");
			ps.setInt(1, sale2.getsId());
			rs = ps.executeQuery();
			conn.commit();
			Sale sale = null;
			while (rs.next()) {
				if(sale == null) {
					sale = new Sale();
					sale.setsId(rs.getInt("s_id"));
					sale.setsNo(rs.getString("s_no"));
					sale.setsVarietyNum(rs.getInt("s_variety_num"));
					sale.setsTotalPrice(rs.getInt("s_total_price"));
					sale.setsSaleDate(rs.getString("s_sale_date"));
					sale.setsSettlementWay(rs.getString("s_settlement_way"));
					sale.setCustomerName(rs.getString("customer_name"));
					if(rs.getInt("sd_id") != 0) {
						SaleDetail saleDetail = new SaleDetail();
						saleDetail.setSdId(rs.getInt("sd_id"));
						saleDetail.setSalePrice(rs.getDouble("sale_price"));
						saleDetail.setSaleNumber(rs.getInt("sale_number"));
						
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
						goods.setgAdvisePrice(rs.getDouble("g_advise_price"));
						goods.setgPromotionPrice(rs.getDouble("g_promotion_price"));
						goods.setgSalePrice(rs.getDouble("g_sale_price"));
						saleDetail.setGoodsId(goods);
						
						sale.getSaleDetails().add(saleDetail);
					}
					
				}else {
					if(rs.getInt("sd_id") != 0) {
						SaleDetail saleDetail = new SaleDetail();
						saleDetail.setSdId(rs.getInt("sd_id"));
						saleDetail.setSalePrice(rs.getDouble("sale_price"));
						saleDetail.setSaleNumber(rs.getInt("sale_number"));
						
						Goods goods = new Goods();
						goods.setgId(rs.getInt("g_id"));
						saleDetail.setGoodsId(goods);
						
						sale.getSaleDetails().add(saleDetail);
					}
				}
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
			ps = conn.prepareStatement("SELECT * FROM `sale` s LEFT JOIN `sale_detail` sd ON s.s_id = sd.sale_id LEFT JOIN goods g ON g.g_id = sd.goods_id order by s.s_sale_date");
			rs = ps.executeQuery();
			conn.commit();
			while (rs.next()) {
				//先循环遍历之前数据，如果能添加进去，就不再重复添加了，如果添加不进去，说明要新增一个数据，这才新增数据
				//当前数据添加状态，默认false，未添加
				boolean flag = false;
				//先循环遍历之前数据
				
				for(Sale sale1:list) {
					if(sale1.getsId() == rs.getInt("s_id")) {
						//如果当前遍历到的进货单已经被添加了，就只添加其他属性
						if(rs.getInt("sd_id") != 0) {
							SaleDetail saleDetail = new SaleDetail();
							saleDetail.setSdId(rs.getInt("sd_id"));
							saleDetail.setSalePrice(rs.getDouble("sale_price"));
							saleDetail.setSaleNumber(rs.getInt("sale_number"));
							
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
							goods.setgAdvisePrice(rs.getDouble("g_advise_price"));
							goods.setgPromotionPrice(rs.getDouble("g_promotion_price"));
							goods.setgSalePrice(rs.getDouble("g_sale_price"));
							saleDetail.setGoodsId(goods);
							
							sale1.getSaleDetails().add(saleDetail);
						}
						flag = true;
						//填完就退出循环，因为当前这一条数据已经被添加了
						break;
					}
				}
				//当第一次循环时，list还没有数据，会进入这个方法
				//当上面循环过后，还没有添加，就进入这个方法
				if(list == null || list.size()==0 || flag == false) {
					//没添加
					Sale sale = new Sale();
					sale.setsId(rs.getInt("s_id"));
					sale.setsNo(rs.getString("s_no"));
					sale.setsVarietyNum(rs.getInt("s_variety_num"));
					sale.setsTotalPrice(rs.getInt("s_total_price"));
					sale.setsSaleDate(rs.getString("s_sale_date"));
					sale.setsSettlementWay(rs.getString("s_settlement_way"));
					sale.setCustomerName(rs.getString("customer_name"));
					if(rs.getInt("sd_id") != 0) {
						SaleDetail saleDetail = new SaleDetail();
						saleDetail.setSdId(rs.getInt("sd_id"));
						saleDetail.setSalePrice(rs.getDouble("sale_price"));
						saleDetail.setSaleNumber(rs.getInt("sale_number"));
						
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
						goods.setgAdvisePrice(rs.getDouble("g_advise_price"));
						goods.setgPromotionPrice(rs.getDouble("g_promotion_price"));
						goods.setgSalePrice(rs.getDouble("g_sale_price"));
						saleDetail.setGoodsId(goods);
						
						sale.getSaleDetails().add(saleDetail);
					}
					list.add(sale);
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
	
	public void add(Sale sale) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("insert into sale(s_no,s_variety_num,s_total_price,s_sale_date,s_settlement_way,customer_id,customer_name) values (?,?,?,?,?,?,(SELECT c_name FROM customer WHERE c_id=?))");
			ps.setObject(1,sale.getsNo());
			ps.setObject(2, sale.getsVarietyNum());
			ps.setObject(3, sale.getsTotalPrice());
			ps.setObject(4, sale.getsSaleDate());
			ps.setObject(5, sale.getsSettlementWay());
			ps.setObject(6, sale.getCustomerId().getcId());
			ps.setObject(7, sale.getCustomerId().getcId());
			ps.executeUpdate();
			
			List<SaleDetail> saleDetails = sale.getSaleDetails();
			for (int i = 0; i < saleDetails.size(); i++) {
				ps = conn.prepareStatement("INSERT INTO `jmjs`.`sale_detail` (`sale_id`,`goods_id`,`sale_price`,`sale_number`) VALUES( (SELECT s_id FROM `sale` WHERE s_no=?),?,?,?)" );
				ps.setObject(1,sale.getsNo());
				ps.setObject(2,saleDetails.get(i).getGoodsId().getgId());
				ps.setObject(3, saleDetails.get(i).getSalePrice());
				ps.setObject(4, saleDetails.get(i).getSaleNumber());
				ps.executeUpdate();
				
				//同时添加数量商品库存表中数据
				
				ps = conn.prepareStatement("{CALL insert_storage_sale(?,?,?)}");
				ps.setObject(1, saleDetails.get(i).getGoodsId().getgId());
				ps.setObject(2, saleDetails.get(i).getSalePrice());
				ps.setObject(3, saleDetails.get(i).getSaleNumber());
				ps.executeUpdate();
				
			}
			
			
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,null);
		}
	}
}
