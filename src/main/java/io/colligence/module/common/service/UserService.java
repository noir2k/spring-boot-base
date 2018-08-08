package io.colligence.module.common.service;

import io.colligence.persistence.model.mapper.UserDto;
import org.springframework.data.domain.Page;

public interface UserService {
    Page<UserDto> findByUserDto(Integer page, String name);
}
