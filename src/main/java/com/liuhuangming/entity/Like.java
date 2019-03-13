package com.liuhuangming.entity;

public class Like {
    public Like() {
		super();
	}

	public Like(Long strategyId, String account) {
		super();
		this.strategyId = strategyId;
		this.account = account;
	}

	private Integer id;

    private Long strategyId;

    private String account;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }
}