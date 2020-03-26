package com.mmall.dto;

import com.mmall.model.SysAcl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@ToString
public class AclDto extends SysAcl {

    // 是否要默认选中--渲染的时候权限店是否要默认选中
    private boolean checked = false;

    // 是否有权限操作 ----渲染的时候权限点是否有权限进行操作
    private boolean hasAcl = false;

    public static AclDto adapt(SysAcl acl) {
        AclDto dto = new AclDto();
        BeanUtils.copyProperties(acl, dto);
        return dto;
    }
}
