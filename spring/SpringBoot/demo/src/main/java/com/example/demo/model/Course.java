package com.example.demo.model;

public class Course {
    private String id;

    private String name;

    private String teacher;

    private String type;

    private Integer credit;

    public Course(String id, String name, String teacher, String type, Integer credit) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.type = type;
        this.credit = credit;
    }

    public Course() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher == null ? null : teacher.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }
}