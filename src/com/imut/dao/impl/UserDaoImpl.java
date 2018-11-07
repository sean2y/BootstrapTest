package com.imut.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.imut.dao.UserDao;
import com.imut.domin.User;
import com.imut.utils.JDBCUtil;

public class UserDaoImpl implements UserDao {
	QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
	
	@Override
	public boolean add(User user) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			String sql = "insert into t_user(userId,userName,userSex,userAge,userNum) values(null,?,?,?,?)";
			int result = queryRunner.update(sql,user.getUserName(),user.getUserSex(),user.getUserAge(),user.getUserNum());
			if(result > 0){
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean del(int userId) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			String sql = "DELETE FROM t_user WHERE userId = ?";
			int result = queryRunner.update(sql,userId);
			if(result > 0){
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public User findById(int userId) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			String sql = "select * from t_user where userId = ?";
			user = queryRunner.query(sql,new BeanHandler<>(User.class) ,userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean update(User user) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			String sql = "UPDATE t_user SET userName=?,userSex=?,userAge=?,userNum=? WHERE userId = ?;";
			int result = queryRunner.update(sql,user.getUserName(),user.getUserSex(),user.getUserAge(),user.getUserNum(),user.getUserId());
			if(result > 0){
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<>();
		try {
			
			String sql = "select * from t_user";
			list = queryRunner.query(sql,new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
