package com.example.service.impl;

import cn.hutool.core.date.DateUtil;
import com.example.entity.Account;
import com.example.entity.Notice;
import com.example.mapper.NoticeMapper;
import com.example.service.NoticeService;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 公告信息表业务处理实现
 **/
@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public void add(Notice notice) {
        notice.setTime(DateUtil.today());
        Account currentUser = TokenUtils.getCurrentUser();
        notice.setUser(currentUser.getUsername());
        noticeMapper.insert(notice);
    }

    @Override
    public void deleteById(Integer id) {
        noticeMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            noticeMapper.deleteById(id);
        }
    }

    @Override
    public void updateById(Notice notice) {
        noticeMapper.updateById(notice);
    }

    @Override
    public Notice selectById(Integer id) {
        return noticeMapper.selectById(id);
    }

    @Override
    public List<Notice> selectAll(Notice notice) {
        return noticeMapper.selectAll(notice);
    }

    @Override
    public PageInfo<Notice> selectPage(Notice notice, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Notice> list = noticeMapper.selectAll(notice);
        return PageInfo.of(list);
    }
}
