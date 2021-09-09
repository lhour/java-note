package com.example.demo.model;

public class Student {
    private Integer id;

    private String name;

    private String age;

    private String sex;

    private String schoole;

    public Student(Integer id, String name, String age, String sex, String schoole) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.schoole = schoole;
    }

    public Student() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getSchoole() {
        return schoole;
    }

    public void setSchoole(String schoole) {
        this.schoole = schoole == null ? null : schoole.trim();
    }
}