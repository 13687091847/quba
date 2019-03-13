package com.liuhuangming.entity;

import java.util.Date;

public class Comment {
    public Comment() {
		super();
	}

	public Comment(String account, String strategyId, String content, Date commentDate, String nickName,
			String headImg) {
		super();
		this.account = account;
		this.strategyId = strategyId;
		this.content = content;
		this.commentDate = commentDate;
		this.nickName = nickName;
		this.headImg = headImg;
	}

	private Integer id;

    private String account;

    private String strategyId;

    private String content;

    private Date commentDate;

    private String nickName;

    private String headImg;

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

    public String getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId == null ? null : strategyId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
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
}