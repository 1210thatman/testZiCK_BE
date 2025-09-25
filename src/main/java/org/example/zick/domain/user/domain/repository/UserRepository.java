package org.example.zick.domain.user.domain.repository;

import org.example.zick.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);
}