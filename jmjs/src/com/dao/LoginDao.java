package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import com.pojo.UsersDTO;
import com.util.BaseDao;

public class LoginDao extends BaseDao {

	public UsersDTO login(UsersDTO user) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("select `u_id`, `u_name`, `u_pwd`,`u_real_name`,`u_email` from users where (u_name = ? or `u_real_name` = ? or `u_email` = ?) and u_pwd = ? and state=1");
			ps.setString(1,user.getuName());
			ps.setString(2,user.getuName());
			ps.setString(3,user.getuName());
			ps.setString(4,user.getuPwd());
			rs = ps.executeQuery();
			conn.commit();
			UsersDTO usersDTO = null;
			while (rs.next()) {
				usersDTO = new UsersDTO();
				usersDTO.setuId(rs.getInt("u_id"));
				usersDTO.setuName(rs.getString("u_name"));
				usersDTO.setuPwd(rs.getString("u_pwd"));
				usersDTO.setuRealName(rs.getString("u_real_name"));
				usersDTO.setuEmail(rs.getString("u_email"));
				
			}
			return usersDTO;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,rs);
		}
		return null;
	}

	public UsersDTO queryAllPromission(UsersDTO user) {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("SELECT r.r_name,p.p_menu FROM users u LEFT JOIN user_role ur ON u.u_id=ur.u_id 	LEFT JOIN roles r ON r.`r_id`=ur.`r_id` LEFT JOIN role_permission rp ON r.`r_id`=rp.`r_id` LEFT JOIN permission p ON p.`p_id`=rp.`p_id` WHERE u.`u_id`=? AND u.`state` = 1");
			ps.setInt(1, user.getuId());
			rs = ps.executeQuery();
			conn.commit();
			Set<String> promissionSet = user.getPromissionSet();
			Set<String> rolesSet = user.getRolesSet();
			while (rs.next()) {
				rolesSet.add(rs.getString("r_name"));
				promissionSet.add(rs.getString("p_menu"));
			}
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close(conn,ps,rs);
		}
		return user;
	}

}
