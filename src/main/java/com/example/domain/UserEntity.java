package com.example.domain;

import com.example.Utils.MD5;

import javax.persistence.*;


/**
 * Created by iam24 on 17/3/30.
 */
@Entity
public class UserEntity {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    //身份证
    private String person_id;
    //权限
    private int role_id;
    private String password;

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    protected UserEntity() {}

    public UserEntity(String name, String person_id, int role_id, String password){
        this.name = name;
        this.person_id = person_id;
        this.role_id = role_id;
        this.password = MD5.getMD5(password);
    }
}
