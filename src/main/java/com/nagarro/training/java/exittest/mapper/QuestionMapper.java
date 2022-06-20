package com.nagarro.training.java.exittest.mapper;

import com.nagarro.training.java.exittest.dto.QuestionDetailsResponse;
import com.nagarro.training.java.exittest.dto.QuestionPreviewResponse;
import com.nagarro.training.java.exittest.dto.ReplyResponse;
import com.nagarro.training.java.exittest.dto.UserResponse;
import com.nagarro.training.java.exittest.entity.Question;
import com.nagarro.training.java.exittest.entity.Reply;
import com.nagarro.training.java.exittest.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {


    @Mapping(source = "product.name", target = "product")
    QuestionDetailsResponse convertToDto(Question question);

    @Mapping(source = "user.username", target = "username")
    ReplyResponse convertToDto(Reply reply);

    UserResponse convertToDto(UserEntity userEntity);

    @Mapping(source = "product.name", target = "product")
    QuestionPreviewResponse convertToPreviewDto(Question question);

    List<QuestionPreviewResponse> convertToPreviewDtoList(List<Question> questions);
}
