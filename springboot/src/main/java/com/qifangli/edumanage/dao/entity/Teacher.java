package com.qifangli.edumanage.dao.entity;

public class Teacher {
    private String id;
    private String name;
    private String sex;
    private String birth;
    private String idCard;
    private String telephone;
    private String political;
    private String department;
    private String degree;
    private String title;
    private String workTime;
    private String pass;

    public Teacher() {
    }

    public Teacher(String id, String name, String sex, String degree, String title, String birth, String idCard, String telephone, String political, String department, String workTime) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.degree = degree;
        this.title = title;
        this.birth = birth;
        this.idCard = idCard;
        this.telephone = telephone;
        this.political = political;
        this.department = department;
        this.workTime = workTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getSex() {
        return sex;
    }

    public String getDegree() {
        return degree;
    }

    public String getTitle() {
        return title;
    }

    public String getBirth() {
        return birth;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPolitical() {
        return political;
    }

    public String getDepartment() {
        return department;
    }

    public String getWorkTime() {
        return workTime;
    }
}
