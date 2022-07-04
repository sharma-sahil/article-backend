package com.nagarro.training.java.exittest.mapper;

import com.nagarro.training.java.exittest.dto.ReplyResponse;
import com.nagarro.training.java.exittest.entity.Reply;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReplyMapper {

    @Mapping(source = "user.username", target = "username")
    ReplyResponse convertToDto(Reply reply);

    List<ReplyResponse> convertToDtoList(List<Reply> observation);
}
