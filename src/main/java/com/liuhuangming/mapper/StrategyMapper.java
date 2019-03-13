package com.liuhuangming.mapper;

import com.liuhuangming.entity.Strategy;
import com.liuhuangming.entity.StrategyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StrategyMapper {
    long countByExample(StrategyExample example);

    int deleteByExample(StrategyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Strategy record);

    int insertSelective(Strategy record);

    List<Strategy> selectByExample(StrategyExample example);

    Strategy selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Strategy record, @Param("example") StrategyExample example);

    int updateByExample(@Param("record") Strategy record, @Param("example") StrategyExample example);

    int updateByPrimaryKeySelective(Strategy record);

    int updateByPrimaryKey(Strategy record);
}