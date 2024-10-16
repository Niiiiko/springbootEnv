package com.example.springmem.bean;

/**
 * @ClassName: User
 * @Auther: niko
 * @Date: 2024/10/16 16:05
 * @Description:
 */
public class User {
    private String  id;
    private String  name;
    private String  pass;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
