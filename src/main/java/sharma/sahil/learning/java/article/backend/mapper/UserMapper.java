package sharma.sahil.learning.java.article.backend.mapper;

import sharma.sahil.learning.java.article.backend.dto.UserResponse;
import sharma.sahil.learning.java.article.backend.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse convertToDto(UserEntity userEntity);
}
