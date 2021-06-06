package com.xuecheng.manage_cms.Test;

import com.xuecheng.manage_cms.conﬁg.Task;
import org.springframework.beans.factory.annotation.Autowired;


public class CoreTest {

    @Autowired
    private Task task;
    public static void main(String[] args) {
    //每天下午三点触发 “0 0 15 * * ?
        //每月最后一天的晚上十二点执行 0 0 0 L * * ?
        //每月最后一星期的星期三 上午8点15分执行    0 15 8 ？ *  5L
        //每月的第二个星期一      上午从9点半到下午三点每半小时执行一次 0 0/30 9-15


    }
}
