package com.example.myapplication3.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "TestNode", createInDb = false)
public class MyCode {
    @Property(nameInDb = "id")
    private String id;
    @Property(nameInDb = "code")
    private String code;
    @Property(nameInDb = "name")
    private String name;

    @Generated(hash = 1022365150)
    public MyCode(String id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    @Generated(hash = 1008272223)
    public MyCode() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
