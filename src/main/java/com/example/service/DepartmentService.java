package com.example.service;

import com.example.entity.Department;
import com.example.mapper.DepartmentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 部门信息业务处理
 **/
@Service
public class DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    /**
     * 新增
     */
    public void add(Department department) {
        departmentMapper.insert(department);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        departmentMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            departmentMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Department department) {
        departmentMapper.updateById(department);
    }

    /**
     * 根据ID查询
     */
    public Department selectById(Integer id) {
        return departmentMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Department> selectAll(Department department) {
        return departmentMapper.selectAll(department);
    }

    /**
     * 分页查询
     */
    public PageInfo<Department> selectPage(Department department, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Department> list = departmentMapper.selectAll(department);
        return PageInfo.of(list);
    }
    public List<Department> selectTree() {
        // 效率比较低  我们应该怎么办？  lambda表达式做筛选
        List<Department> list = departmentMapper.selectAll(null);
        List<Department> rootList = list.stream().filter(department -> department.getPid() == null).collect(Collectors.toList());
        for (Department root : rootList) {
            List<Department> level2List = list.stream().filter(department -> Objects.equals(department.getPid(), root.getId())).collect(Collectors.toList());
            root.setChildren(level2List);
            for (Department level2 : level2List) {
                //  IO
                List<Department> level3List = list.stream().filter(department -> Objects.equals(department.getPid(), level2.getId())).collect(Collectors.toList());
                level2.setChildren(level3List);
            }
        }
        return rootList;
    }

    public List<Department> selectParent() {
        List<Department> list = departmentMapper.selectAll(null);
        return list.stream().filter(department -> department.getLevel() == 2 || department.getLevel() == 1).collect(Collectors.toList());
    }
}