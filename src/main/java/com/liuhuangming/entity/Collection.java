package com.liuhuangming.entity;

public class Collection {
    public Collection() {
		super();
	}

	public Collection(String account, Long strategyId) {
		super();
		this.account = account;
		this.strategyId = strategyId;
	}

	private Integer id;

    private String account;

    private Long strategyId;

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

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }
}