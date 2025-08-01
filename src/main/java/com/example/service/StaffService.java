package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Staff;
import com.example.exception.CustomException;
import com.example.mapper.StaffMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 员工员业务处理
 **/
@Service
public class StaffService {

    @Resource
    private StaffMapper staffMapper;

    /**
     * 新增
     */
    public void add(Staff staff) {
        Staff dbStaff = staffMapper.selectByUsername(staff.getUsername());
        if (ObjectUtil.isNotNull(dbStaff)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(staff.getPassword())) {
            staff.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        if (ObjectUtil.isEmpty(staff.getName())) {
            staff.setName(staff.getUsername());
        }
        // 修复BUG：新建员工默认角色应该是STAFF，不是ADMIN
        if (ObjectUtil.isEmpty(staff.getRole())) {
            staff.setRole(RoleEnum.STAFF.name());
        }
        staffMapper.insert(staff);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        staffMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            staffMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Staff staff) {
        staffMapper.updateById(staff);
    }

    /**
     * 根据ID查询
     */
    public Staff selectById(Integer id) {
        return staffMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Staff> selectAll(Staff staff) {
        return staffMapper.selectAll(staff);
    }

    /**
     * 分页查询
     */
    public PageInfo<Staff> selectPage(Staff staff, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Staff> list = staffMapper.selectAll(staff);
        return PageInfo.of(list);
    }

    /**
     * 登录
     */
    public Account login(Account account) {
        Account dbStaff = staffMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbStaff)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbStaff.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        // 生成token
        String tokenData = dbStaff.getId() + "-" + RoleEnum.STAFF.name();
        String token = TokenUtils.createToken(tokenData, dbStaff.getPassword());
        dbStaff.setToken(token);
        return dbStaff;
    }


    /**
     * 注册
     */
    public void register(Account account) {
        Staff staff = new Staff();
        staff.setUsername(account.getUsername());
        staff.setPassword(account.getPassword());
        this.add(staff);
    }
    /**
     * 修改密码
     */
    public void updatePassword(Account account) {
        Staff dbStaff = staffMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbStaff)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbStaff.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }
        dbStaff.setPassword(account.getNewPassword());
        staffMapper.updateById(dbStaff);
    }




}