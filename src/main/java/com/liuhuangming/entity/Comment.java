package com.liuhuangming.entity;

import java.util.Date;

public class Comment {
    private Integer id;

    private String strategyid;

    private String account;

    private String content;

    private Date commentdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrategyid() {
        return strategyid;
    }

    public void setStrategyid(String strategyid) {
        this.strategyid = strategyid == null ? null : strategyid.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(Date commentdate) {
        this.commentdate = commentdate;
    }
}