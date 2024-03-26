package io.spring.infrastructure.mybatis.readservice;

import io.spring.application.data.UserData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserReadService {
  UserData findByUsername(@Param("username") String username);

  UserData findById(@Param("id") String id);

  List<UserData> findAll();
}
