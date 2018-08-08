package io.colligence.persistence.repository;

import io.colligence.persistence.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(@Param("name") final String name);
    List<User> findAllByOrderByName();
    Page<User> findByName(String name, Pageable pageable);
}
