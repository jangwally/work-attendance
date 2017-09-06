package com.myloe.attend.dao;

import com.myloe.attend.entity.Attend;
import com.myloe.attend.vo.PageCondition;

import java.util.List;

public interface AttendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Attend record);

    int insertSelective(Attend record);

    Attend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Attend record);

    int updateByPrimaryKey(Attend record);

    int countByCondition(PageCondition pageCondition);

    List<Attend> selectAttendPage(PageCondition pageCondition);
}