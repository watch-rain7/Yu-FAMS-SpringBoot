package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.entity.Account;
import com.example.entity.Notice;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * 公告信息表业务处理接口
 **/
public interface NoticeService {

    void add(Notice notice);

    void deleteById(Integer id);

    void deleteBatch(List<Integer> ids);

    void updateById(Notice notice);

    Notice selectById(Integer id);

    List<Notice> selectAll(Notice notice);

    PageInfo<Notice> selectPage(Notice notice, Integer pageNum, Integer pageSize);

//    List<Notice> selectAll();
}