package com.mmall.service;

import com.google.common.base.Preconditions;

import com.mmall.common.RequestHolder;

import com.mmall.dao.SysRoleMapper;

import com.mmall.exception.ParamException;
import com.mmall.model.SysRole;

import com.mmall.param.RoleParam;
import com.mmall.util.BeanValidator;
import com.mmall.util.IpUtil;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;
//    @Resource
//    private SysRoleUserMapper sysRoleUserMapper;
//    @Resource
//    private SysRoleAclMapper sysRoleAclMapper;
//    @Resource
//    private SysUserMapper sysUserMapper;
//    @Resource
//    private SysLogService sysLogService;


    //    修改操作
    public void save(RoleParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getName(), param.getId())) {
            throw new ParamException("角色名称已经存在");
        }
        SysRole role = SysRole.builder().name(param.getName()).status(param.getStatus()).type(param.getType())
                .remark(param.getRemark()).build();
        role.setOperator(RequestHolder.getCurrentUser().getUsername());
        role.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        role.setOperateTime(new Date());
        sysRoleMapper.insertSelective(role);
//        sysLogService.saveRoleLog(null, role);
    }

    //更新操作
    public void update(RoleParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getName(), param.getId())) {
            throw new ParamException("角色名称已经存在");
        }
        SysRole before = sysRoleMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的角色不存在");

        SysRole after = SysRole.builder().id(param.getId()).name(param.getName()).status(param.getStatus()).type(param.getType())
                .remark(param.getRemark()).build();
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperateTime(new Date());
        sysRoleMapper.updateByPrimaryKeySelective(after);
//        sysLogService.saveRoleLog(before, after);
    }

    //获取所有的角色
    public List<SysRole> getAll() {
        return sysRoleMapper.getAll();
    }

    private boolean checkExist(String name, Integer id) {
        return sysRoleMapper.countByName(name, id) > 0;
    }
//
//    public List<SysRole> getRoleListByUserId(int userId) {
//        List<Integer> roleIdList = sysRoleUserMapper.getRoleIdListByUserId(userId);
//        if (CollectionUtils.isEmpty(roleIdList)) {
//            return Lists.newArrayList();
//        }
//        return sysRoleMapper.getByIdList(roleIdList);
//    }
//
//    public List<SysRole> getRoleListByAclId(int aclId) {
//        List<Integer> roleIdList = sysRoleAclMapper.getRoleIdListByAclId(aclId);
//        if (CollectionUtils.isEmpty(roleIdList)) {
//            return Lists.newArrayList();
//        }
//        return sysRoleMapper.getByIdList(roleIdList);
//    }
//
//    public List<SysUser> getUserListByRoleList(List<SysRole> roleList) {
//        if (CollectionUtils.isEmpty(roleList)) {
//            return Lists.newArrayList();
//        }
//        List<Integer> roleIdList = roleList.stream().map(role -> role.getId()).collect(Collectors.toList());
//        List<Integer> userIdList = sysRoleUserMapper.getUserIdListByRoleIdList(roleIdList);
//        if (CollectionUtils.isEmpty(userIdList)) {
//            return Lists.newArrayList();
//        }
//        return sysUserMapper.getByIdList(userIdList);
//    }
}
