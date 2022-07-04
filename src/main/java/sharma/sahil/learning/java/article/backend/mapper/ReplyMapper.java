package sharma.sahil.learning.java.article.backend.mapper;

import sharma.sahil.learning.java.article.backend.dto.ReplyResponse;
import sharma.sahil.learning.java.article.backend.entity.Reply;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReplyMapper {

    @Mapping(source = "user.username", target = "username")
    ReplyResponse convertToDto(Reply reply);

    List<ReplyResponse> convertToDtoList(List<Reply> observation);
}
