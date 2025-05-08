package com.qifangli.edumanage.dao.entity;

public class Permission {
    private String pid;
    private String rid;
    private String permsCode;
    private String url;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getPermsCode() {
        return permsCode;
    }

    public void setPermsCode(String permsCode) {
        this.permsCode = permsCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
