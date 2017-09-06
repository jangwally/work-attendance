package com.myloe.attend.controller;

import com.myloe.attend.serivce.AttendService;
import com.myloe.attend.vo.PageCondition;
import com.myloe.commons.page.PageQueryBean;
import com.myloe.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("attend")
public class AttendController {

    @Autowired
    private AttendService attendService;

    @RequestMapping
    public String toAttend() {

        return "attend";
    }

    /**
     * 数据分页，通过json传送到前台
     *
     * @param condition
     * @param session
     * @return
     */
    @RequestMapping("attendList")
    @ResponseBody
    public PageQueryBean listAttend(PageCondition condition, HttpSession session) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("LOING_USER");
        String[] rangeDate = condition.getRangeDate().split("/");
        condition.setStartDate(rangeDate[0]);
        condition.setEndDate(rangeDate[1]);
        condition.setUserId(user.getId());
        PageQueryBean result=attendService.listAttend(condition);
        return result;

    }


}
