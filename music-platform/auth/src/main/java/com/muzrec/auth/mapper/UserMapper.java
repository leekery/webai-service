package com.muzrec.auth.mapper;

import com.muzrec.auth.dto.UserDto;
import com.muzrec.auth.dto.UserRegisterDto;
import com.muzrec.auth.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toUser(UserRegisterDto dto);

    UserDto toUserDto(User user);
}
