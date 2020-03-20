package com.mmall.dto;

import com.google.common.collect.Lists;
import com.mmall.model.SysDept;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Getter
@Setter
@ToString
//继承SysDept  这样就拥有了部门的基本属性
public class DeptLevelDto extends SysDept {
    //增加一个新的属性
    private List<DeptLevelDto> deptList = Lists.newArrayList();
    //适配的方法
    public static DeptLevelDto adapt(SysDept dept) {
        DeptLevelDto dto = new DeptLevelDto();
        BeanUtils.copyProperties(dept, dto);
        return dto;
    }
}