package com.example.myapplication3.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "DisBase",createInDb = false,generateConstructors = false)
public class DisBase {
    @Id
    @Property(nameInDb = "id")
    private String id;
    @Property(nameInDb = "code")
    private String code;
    @Property(nameInDb = "name")
    private String name;
    @Property(nameInDb = "age")
    private String age;
    public DisBase() {
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
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
}
