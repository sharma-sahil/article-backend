package com.nagarro.training.java.exittest.mapper;

import com.nagarro.training.java.exittest.dto.UserResponse;
import com.nagarro.training.java.exittest.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse convertToDto(UserEntity userEntity);
}
