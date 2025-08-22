package org.example.zick.domain.user.domain.repository;

import org.example.zick.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserReposiotry extends CrudRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}