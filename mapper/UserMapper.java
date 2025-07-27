package mapper;

import dto.UserCreateRequest;
import dto.UserResponse;
import entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserCreateRequest request);
    UserResponse toResponse(User user);
} 