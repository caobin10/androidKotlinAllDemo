package com.example.myapplication3.Bean;

public class User {
    Integer id;   //Id
    String number;//账号
    String nick;  //昵称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public User() {
        super();
    }

    public User(Integer id, String number, String nick) {
        super();
        this.id = id;
        this.number = number;
        this.nick = nick;
    }
}

