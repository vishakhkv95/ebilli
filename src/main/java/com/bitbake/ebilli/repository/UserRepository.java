package com.bitbake.ebilli.repository;


import com.bitbake.ebilli.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUserName(String userName);

}
