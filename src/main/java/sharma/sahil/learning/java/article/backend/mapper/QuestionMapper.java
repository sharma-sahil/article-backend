package sharma.sahil.learning.java.article.backend.mapper;

import sharma.sahil.learning.java.article.backend.dto.QuestionDetailsResponse;
import sharma.sahil.learning.java.article.backend.dto.QuestionPreviewResponse;
import sharma.sahil.learning.java.article.backend.dto.ReplyResponse;
import sharma.sahil.learning.java.article.backend.dto.UserResponse;
import sharma.sahil.learning.java.article.backend.entity.Question;
import sharma.sahil.learning.java.article.backend.entity.Reply;
import sharma.sahil.learning.java.article.backend.entity.UserEntity;
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
