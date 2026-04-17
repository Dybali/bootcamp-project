package com.bootcamp.mapper;

import com.bootcamp.dto.BlacklistCreateRequest;
import com.bootcamp.dto.BlacklistResponse;
import com.bootcamp.entity.Blacklist;
import com.bootcamp.org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlacklistMapper {
    Blacklist toEntity(BlacklistCreateRequest request);
    BlacklistResponse toResponse(Blacklist blacklist);
} 


