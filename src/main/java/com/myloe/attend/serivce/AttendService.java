package com.myloe.attend.serivce;

import com.myloe.attend.vo.PageCondition;
import com.myloe.commons.page.PageQueryBean;

public interface AttendService {

   public PageQueryBean listAttend(PageCondition pageCondition);


}
