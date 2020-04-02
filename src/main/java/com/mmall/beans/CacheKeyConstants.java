package com.mmall.beans;

import lombok.Getter;

@Getter
public enum CacheKeyConstants {
//缓存系统内的所有权限
    SYSTEM_ACLS,
//缓存用户的权限
    USER_ACLS;

}
