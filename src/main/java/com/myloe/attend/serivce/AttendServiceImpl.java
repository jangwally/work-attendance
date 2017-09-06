package com.myloe.attend.serivce;

import com.myloe.attend.dao.AttendMapper;
import com.myloe.attend.entity.Attend;
import com.myloe.attend.vo.PageCondition;
import com.myloe.commons.page.PageQueryBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendServiceImpl implements  AttendService {

    @Autowired
    private AttendMapper attendMapper;

    @Override
    public PageQueryBean listAttend(PageCondition pageCondition) {
        //根据条件查询记录数
        int count=attendMapper.countByCondition(pageCondition);

        PageQueryBean pageQueryBean=new PageQueryBean();
        if(count>0){
            pageQueryBean.setCurrentPage(pageCondition.getCurrentPage());
            pageQueryBean.setPageSize(pageCondition.getPageSize());
            pageQueryBean.setTotalRows(count);

            List<Attend> attendList=attendMapper.selectAttendPage(pageCondition);
            pageQueryBean.setItems(attendList);
        }
        return pageQueryBean;
    }
}
