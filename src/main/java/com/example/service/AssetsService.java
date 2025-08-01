package com.example.service;

import com.example.entity.Assets;
import com.example.entity.Category;
import com.example.mapper.AssetsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 资产信息业务处理
 **/
@Service
public class AssetsService {

    @Resource
    private AssetsMapper assetsMapper;

    /**
     * 新增
     */
    public void add(Assets assets) {
        assetsMapper.insert(assets);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        assetsMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            assetsMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Assets assets) {
        assetsMapper.updateById(assets);
    }

    /**
     * 根据ID查询
     */
    public Assets selectById(Integer id) {
        return assetsMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Assets> selectAll(Assets assets) {
        return assetsMapper.selectAll(assets);
    }

    /**
     * 分页查询
     */
    public PageInfo<Assets> selectPage(Assets assets, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Assets> list = assetsMapper.selectAll(assets);
        return PageInfo.of(list);
    }


    public int selectCountByCategory(String category) {
        return assetsMapper.selectCountByCategory(category);
    }
}