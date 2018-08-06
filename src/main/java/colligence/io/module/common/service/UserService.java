package colligence.io.module.common.service;

import colligence.io.persistence.model.mapper.UserDto;
import org.springframework.data.domain.Page;

public interface UserService {
    Page<UserDto> findUserDtoList(Integer page, String name);
}
