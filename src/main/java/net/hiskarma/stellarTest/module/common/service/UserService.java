package net.hiskarma.stellarTest.module.common.service;

import net.hiskarma.stellarTest.persistence.model.mapper.UserDto;
import org.springframework.data.domain.Page;

public interface UserService {
    Page<UserDto> findUserDtoList(Integer page, String name);
}
