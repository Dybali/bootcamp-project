package mapper;

import dto.BlacklistCreateRequest;
import dto.BlacklistResponse;
import entity.Blacklist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlacklistMapper {
    Blacklist toEntity(BlacklistCreateRequest request);
    BlacklistResponse toResponse(Blacklist blacklist);
} 