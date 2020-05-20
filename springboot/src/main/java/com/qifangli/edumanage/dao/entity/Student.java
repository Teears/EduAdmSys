package com.qifangli.edumanage.dao.entity;

public class Student {
    private String id;
    private String name;
    private String sex;
    private String graduate;
    private String birth;
    private String idCard;
    private String telephone;
    private String political;
    private String department;
    private String classAndGrade;
    private String status;
    private String pass;

    public Student() {
    }

    public Student(String id, String name, String sex, String graduate, String birth, String idCard, String telephone, String political, String department, String classAndGrade, String status) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.graduate = graduate;
        this.birth = birth;
        this.idCard = idCard;
        this.telephone = telephone;
        this.political = political;
        this.department = department;
        this.classAndGrade = classAndGrade;
        this.status = status;
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

    public void setGraduate(String graduate) {
        this.graduate = graduate;
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

    public void setClassAndGrade(String classAndGrade) {
        this.classAndGrade = classAndGrade;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getGraduate() {
        return graduate;
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

    public String getClassAndGrade() {
        return classAndGrade;
    }

    public String getStatus() {
        return status;
    }
}