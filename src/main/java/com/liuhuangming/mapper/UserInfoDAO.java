package com.liuhuangming.mapper;

import com.liuhuangming.entity.UserInfo;
import com.liuhuangming.entity.UserInfoExample;
import org.springframework.stereotype.Repository;

/**
 * UserInfoDAO继承基类
 */
@Repository
public interface UserInfoDAO extends MyBatisBaseDao<UserInfo, String, UserInfoExample> {
}