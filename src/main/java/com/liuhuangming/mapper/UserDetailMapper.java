package com.liuhuangming.mapper;

import com.liuhuangming.entity.UserDetail;
import com.liuhuangming.entity.UserDetailExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDetailMapper {
    int countByExample(UserDetailExample example);

    int deleteByExample(UserDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserDetail record);

    int insertSelective(UserDetail record);

    List<UserDetail> selectByExample(UserDetailExample example);

    UserDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserDetail record, @Param("example") UserDetailExample example);

    int updateByExample(@Param("record") UserDetail record, @Param("example") UserDetailExample example);

    int updateByPrimaryKeySelective(UserDetail record);

    int updateByPrimaryKey(UserDetail record);
    
    UserDetail selectByAccount(String account);
}