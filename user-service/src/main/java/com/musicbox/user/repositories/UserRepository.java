package com.musicbox.user.repositories;

import com.musicbox.user.common.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository <User, Long> {
    User findUserById(Long id);

    User findUserByEmail(String email);
    Long findIdByEmail(String email);


    Boolean existsByEmail(String email);
}
