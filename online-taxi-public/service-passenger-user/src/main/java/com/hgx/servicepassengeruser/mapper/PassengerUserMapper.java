package com.hgx.servicepassengeruser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hgx.servicepassengeruser.dto.PassengerUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
public interface PassengerUserMapper extends BaseMapper<PassengerUser> {


}
