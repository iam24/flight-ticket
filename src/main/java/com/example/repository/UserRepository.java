package com.example.repository;

import com.example.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by iam24 on 17/3/30.
 */
@Repository
public interface UserRepository extends JpaRepository<UserRepository, Integer>{
    UserEntity findByName(String name);


}
