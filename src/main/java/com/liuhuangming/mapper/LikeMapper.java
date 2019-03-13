package com.liuhuangming.mapper;

import com.liuhuangming.entity.Like;
import com.liuhuangming.entity.LikeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LikeMapper {
    long countByExample(LikeExample example);

    int deleteByExample(LikeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Like record);

    int insertSelective(Like record);

    List<Like> selectByExample(LikeExample example);

    Like selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Like record, @Param("example") LikeExample example);

    int updateByExample(@Param("record") Like record, @Param("example") LikeExample example);

    int updateByPrimaryKeySelective(Like record);

    int updateByPrimaryKey(Like record);
}