package com.liuhuangming.mapper;

import com.liuhuangming.entity.Fans;
import com.liuhuangming.entity.FansExample;
import org.springframework.stereotype.Repository;

/**
 * FansDAO继承基类
 */
@Repository
public interface FansDAO extends MyBatisBaseDao<Fans, Fans, FansExample> {
}