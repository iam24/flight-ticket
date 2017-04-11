package com.example.repository;

import com.example.domain.UserEntity;
import com.sun.tools.javac.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by iam24 on 17/3/30.
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>{
    UserEntity findByName(String name);

    @Query("select new com.example.domain.UserEntity(u.name, u.person_id) from UserEntity u")
    public ArrayList<UserEntity> findAllUser();
}
