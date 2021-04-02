package com.wjl.testdata.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjl.testdata.entity.UserInfo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liqq
 * @since 2021-01-19
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    UserInfo findLimitOne();
}
