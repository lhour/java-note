package com.example.mybatis.domin;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
public class Student {

    private String 学号;
    private String 姓名;
    private String 性别;
    private String 出生日期;
    private String 政治面貌;
    private String 班级;
    private String 毕业学校;


    public Student() {
    }


    public String get学号() {
        return 学号;
    }


    public void set学号(String 学号) {
        this.学号 = 学号;
    }


    public String get姓名() {
        return 姓名;
    }


    public void set姓名(String 姓名) {
        this.姓名 = 姓名;
    }


    public String get性别() {
        return 性别;
    }


    public void set性别(String 性别) {
        this.性别 = 性别;
    }


    public String get出生日期() {
        return 出生日期;
    }


    public void set出生日期(String 出生日期) {
        this.出生日期 = 出生日期;
    }


    public String get政治面貌() {
        return 政治面貌;
    }


    public void set政治面貌(String 政治面貌) {
        this.政治面貌 = 政治面貌;
    }


    public String get班级() {
        return 班级;
    }


    public void set班级(String 班级) {
        this.班级 = 班级;
    }


    public String get毕业学校() {
        return 毕业学校;
    }


    public void set毕业学校(String 毕业学校) {
        this.毕业学校 = 毕业学校;
    }

}
