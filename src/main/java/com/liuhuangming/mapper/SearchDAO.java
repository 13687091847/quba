package com.liuhuangming.mapper;

import com.liuhuangming.entity.Search;
import com.liuhuangming.entity.SearchExample;
import org.springframework.stereotype.Repository;

/**
 * SearchDAO继承基类
 */
@Repository
public interface SearchDAO extends MyBatisBaseDao<Search, Integer, SearchExample> {
}