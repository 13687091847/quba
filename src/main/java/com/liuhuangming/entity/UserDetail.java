package com.liuhuangming.entity;

import java.util.Date;

public class UserDetail {
    public UserDetail() {
		super();
	}

	public UserDetail(String account, String email, Integer money) {
		super();
		this.account = account;
		this.email = email;
		this.money = money;
	}

	public UserDetail(Integer id, String account, String nickName, String headImg, String signature, String email,
			Date birthDay, Integer money) {
		super();
		this.id = id;
		this.account = account;
		this.nickName = nickName;
		this.headImg = headImg;
		this.signature = signature;
		this.email = email;
		this.birthDay = birthDay;
		this.money = money;
	}

	private Integer id;

    private String account;

    private String nickName;

    private String headImg;

    private String signature;

    private String email;

    private Date birthDay;

    private Integer money;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}