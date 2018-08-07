package io.colligence.api.module.common.service;

import io.colligence.api.persistence.model.mapper.UserDto;
import org.springframework.data.domain.Page;

public interface UserService {
    Page<UserDto> findUserDtoList(Integer page, String name);
}
