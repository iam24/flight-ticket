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
    private long id;
    private String name;
    //身份证
    private String person_id;
    //权限
    private int role_id = 3;
    private String password;

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public long getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = MD5.getMD5(password);
    }
    public String getPassword(){
        return password;
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

    public UserEntity() {}

    public UserEntity(String name, String password, String person_id, int role_id){
        this.name = name;
        this.person_id = person_id;
        this.role_id = role_id;
        this.password = MD5.getMD5(password);
    }

    public UserEntity(long id,String name, String person_id){
        this.id = id;
        this.name = name;
        this.person_id = person_id;
    }

    public boolean CheckPersonID(){
        return this.person_id.length() == 18;
    }
}
