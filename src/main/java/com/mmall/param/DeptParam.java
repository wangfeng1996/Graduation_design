package com.mmall.param;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * 部门的参数
 */
@Getter
@Setter
@ToString
public class DeptParam {

    private Integer id;

    //给这些参数一些限制
    @NotBlank(message = "部门名称不可以为空")
    //上下限值
    @Length(max = 15, min = 2, message = "部门名称长度需要在2-15个字之间")

    private String name;

    private Integer parentId = 0;//这边传值保证parentId不为空值

    @NotNull(message = "展示顺序不可以为空")
    private Integer seq;

    @Length(max = 150, message = "备注的长度需要在150个字以内")
    private String remark;//备注
}
