package com.qifangli.edumanage.dao.entity;

public class Course {
    private String id;
    private String name;
    private String credit;
    private String hours;
    private String department;
    private String type;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCredit() {
        return credit;
    }

    public String getHours() {
        return hours;
    }

    public String getDepartment() {
        return department;
    }

    public String getType() {
        return type;
    }
}
