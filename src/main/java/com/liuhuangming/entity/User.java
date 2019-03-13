package com.liuhuangming.entity;

public class User {
    public User() {
		super();
	}

	public User(String account, String password, String email) {
		super();
		this.account = account;
		this.password = password;
		this.email = email;
	}

	private Integer id;

    private String account;

    private String password;

    private String email;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}