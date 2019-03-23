package com.liuhuangming.mapper;

import com.liuhuangming.entity.UserInfo;
import com.liuhuangming.entity.UserInfoExample;
import org.springframework.stereotype.Repository;

/**
 * UserInfoDAO继承基类
 */
@Repository
public interface UserInfoDAO extends MyBatisBaseDao<UserInfo, String, UserInfoExample> {
	
	/**
	 * 通过用户账号查出信息，但是为了安全不查出密码
	 * @param account
	 * @return
	 */
	UserInfo selectByPrimaryKeyWithoutPassword(String account);
}