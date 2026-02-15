package com.mad0309.ecommercerestapi.user.infrastructure.mapper;

import com.mad0309.ecommercerestapi.user.domain.model.User;
import com.mad0309.ecommercerestapi.user.infrastructure.adapter.out.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomain(UserEntity entity);

    UserEntity toEntity(User user);

    List<User> toDomainList(List<UserEntity> entities);
}
