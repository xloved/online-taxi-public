package com.hgx.serviceDriverUser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hgx.internalcomm.dto.DriverUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description com.hgx.serviceDriverUser.mapper
 * @Author huogaoxu
 * @Date 2023-05-28 21:24
 * @Version 1.0
 **/
@Mapper
public interface DriverUserMapper extends BaseMapper<DriverUser> {

    public int select(@Param("cityCode") String cityCode);
}
