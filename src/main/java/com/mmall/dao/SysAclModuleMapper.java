package com.mmall.dao;

import com.mmall.model.SysAclModule;
import com.mmall.model.SysDept;
import com.sun.mail.imap.protocol.ID;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAclModuleMapper {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(SysAclModule record);

    int insertSelective(SysAclModule record);

    SysAclModule selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(SysAclModule record);

    int updateByPrimaryKey(SysAclModule record);

    int countByNameAndParentId(@Param("parentId") Integer parentId, @Param("name") String name, @Param("id") Integer id);

    List<SysAclModule> getChildAclModuleListByLevel(@Param("level") String level);

    void batchUpdateLevel(@Param("sysAclModuleList") List<SysAclModule> sysAclModuleList);

    List<SysAclModule> getAllAclModule();

    int countByParentId(@Param("aclModuleId") int aclModuleId);

}