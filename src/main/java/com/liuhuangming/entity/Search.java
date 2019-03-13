package com.liuhuangming.entity;

import java.util.Date;

public class Search {
    public Search() {
		super();
	}

	public Search(String account, String content, Date searchTime) {
		super();
		this.account = account;
		this.content = content;
		this.searchTime = searchTime;
	}

	private Integer id;

    private String account;

    private String content;

    private Date searchTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(Date searchTime) {
        this.searchTime = searchTime;
    }
}