package com.liuhuangming.bean;

import java.util.List;

import com.liuhuangming.entity.Strategy;

public class ResultBean {

	private Strategy strategy;
	private List<String> strategyImgs;
	public Strategy getStrategy() {
		return strategy;
	}
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	public List<String> getStrategyImgs() {
		return strategyImgs;
	}
	public void setStrategyImgs(List<String> strategyImgs) {
		this.strategyImgs = strategyImgs;
	}
	
}
