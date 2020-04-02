package com.mmall.util;


import org.apache.commons.lang3.StringUtils;

public class LevelUtil {

    public final static String SEPARATOR = ".";//定义部门之间的分隔符

    public final static String ROOT = "0";// 从0开始

    // 0
    // 0.1
    // 0.1.2
    // 0.1.3
    // 0.2
    //定义部门层级level计算规则
    public static String calculateLevel(String parentLevel, int parentId) {
            //如果当前层是空的话  ---首层
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            //不是首层的话  parentLevel  连上parentId就可以了
            return StringUtils.join(parentLevel, SEPARATOR, parentId);
        }
    }
}
