package com.example.planningpoker.model;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private Role role;
    private String pic_id;

    public User() {
    }

    public User(String name) {
        this.name = name;
        this.role = Role.USER;
        this.pic_id = "";
    }

    public User(int id, String name, Role role) {
        this.name = name;
        this.role = role;
        this.pic_id = "";
    }

    public User(int id, String name, String pic_id) {
        this.name = name;
        this.pic_id = pic_id;
        this.role = Role.USER;
    }

    public User(int id, String name, Role role, String pic_id) {
        this.name = name;
        this.role = role;
        this.pic_id = pic_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPic_id() {
        return pic_id;
    }

    public void setPic_id(String pic_id) {
        this.pic_id = pic_id;
    }
}
