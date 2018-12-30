package com.ssd.ssdapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ssd.ssdapp.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByNameAndPassword(String name, String password);

}
