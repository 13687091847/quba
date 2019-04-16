package com.liuhuangming.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liuhuangming.bean.IDUtils;
import com.liuhuangming.bean.Mes;
import com.liuhuangming.bean.ResultBean;
import com.liuhuangming.entity.Strategy;
import com.liuhuangming.entity.StrategyExample;
import com.liuhuangming.entity.StrategyExample.Criteria;
import com.liuhuangming.entity.StrategyImg;
import com.liuhuangming.entity.UserInfo;
import com.liuhuangming.mapper.StrategyDAO;
import com.liuhuangming.service.CollectService;
import com.liuhuangming.service.LikeService;
import com.liuhuangming.service.StrategyImgService;
import com.liuhuangming.service.StrategyService;
import com.liuhuangming.service.UserInfoService;
import com.sun.tools.javac.resources.compiler;

/**
 * 游记管理接口实现类
 * 
 * @author Administrator
 *
 */
@Service
public class StrategyServiceImpl implements StrategyService {
	@Autowired
	StrategyDAO strategyDAO;
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	LikeService likeService;
	@Autowired
	CollectService collectService;
	@Autowired
	StrategyImgService strategyImgService;

	/**
	 * 用户发表游记
	 */
	@Override
	public Mes addStrategy(ResultBean resultBean, HttpSession session) {
		Mes message = new Mes();
		Strategy strategy = resultBean.getStrategy();
		String account = (String) session.getAttribute("account");
		if (account != null) {
			strategy.setAccount(account);
		}
		String strategyId = IDUtils.createID();
		strategy.setStatus(true);
		strategy.setStrategyId(strategyId);
		strategy.setCollectNum(0);
		strategy.setLikeNum(0);
		strategy.setCommentNum(0);
		strategy.setBrowseVolume(0);
		// 首先查看是否为重复发送
		if (checkStrategy(strategy, session)) {
			message.setCode(500);
			message.setMessage("请勿重复发送");
			return message;
		}
		// 插入游记
		int insertStrategyCode = strategyDAO.insertSelective(strategy);
		List<StrategyImg> strategyImgs = new ArrayList<>();
		for (String url : resultBean.getStrategyImgs()) {
			StrategyImg strategyImg = new StrategyImg();
			strategyImg.setImgUrl(url);
			strategyImg.setStrategyId(strategyId);
			strategyImgs.add(strategyImg);
		}
		// 插入游记附图
		int insertStrategyImgCode = strategyImgService.addStrategyImg(strategyImgs);
		if (insertStrategyCode > 0 && insertStrategyImgCode >0) {
			message.setCode(200);
			return message;
		}
		message.setCode(500);
		return message;
	}

	/**
	 * 检查游记是否重复发送
	 */
	@Override
	public boolean checkStrategy(Strategy strategy, HttpSession session) {
		String account = (String) session.getAttribute("account");
		StrategyExample strategyExample = new StrategyExample();
		Criteria criteria = strategyExample.createCriteria();
		criteria.andAccountEqualTo(account);
		criteria.andTitleEqualTo(strategy.getTitle());
		List<Strategy> listStrategy = strategyDAO.selectByExample(strategyExample);
		if (listStrategy != null) {
			for (Strategy item : listStrategy) {
				// 时间不超过15分钟，则提示不能重复发送
				if ((item.getUploadDate().getTime() - (new Date()).getTime()) < 15) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 获取当前登录用户的所有游记
	 */
	@Override
	public List<Strategy> getMyStrategy(HttpSession session) {
		String account = (String) session.getAttribute("account");
		return getStrategyByAccount(account);
	}

	/**
	 * 根据游记ID删除游记
	 */
	@Override
	public Mes deleteStrategy(String strategyId) {
		Mes message = new Mes();
		Strategy strategy = new Strategy();
		strategy.setStrategyId(strategyId);
		// 设定该游记为删除状态
		strategy.setStatus(false);
		// 调用更新DAO方法
		int resultColde = strategyDAO.updateByPrimaryKeySelective(strategy);
		// 删除收藏表中对应的游记
		int deleteCode = collectService.deleteByStrategyId(strategyId);
		if (resultColde > 0 && deleteCode > 0) {
			message.setCode(200);
		} else {
			message.setCode(500);
		}
		return message;
	}

	/**
	 * 更新游记信息
	 */
	@Override
	public int updateStrategy(Strategy strategy) {
		if (strategy.getStrategyId() != null) {
			return strategyDAO.updateByPrimaryKey(strategy);
		}
		return 0;
	}

	/**
	 * 根据游记ID获取游记信息
	 */
	@Override
	public Strategy selectByStrategyId(String strategyId) {
		Strategy strategy = strategyDAO.selectByPrimaryKey(strategyId);
		// 根据用户账号查询用户信息
		strategy.setUserInfo(userInfoService.getUserInfoByAccount(strategy.getAccount()));
		//查询对应的游记附图
		strategy.setStrategyImgs(strategyImgService.findByStrategyId(strategyId));
		return strategy;
	}

	/**
	 * 统计对应用户发表的游记数量
	 */
	@Override
	public long countByAccount(String account) {
		StrategyExample strategyExample = new StrategyExample();
		Criteria criteria = strategyExample.createCriteria();
		if (account != null) {
			criteria.andAccountEqualTo(account);
			criteria.andStatusEqualTo(true);
			return strategyDAO.countByExample(strategyExample);
		}
		return 0;
	}

	/**
	 * 通过用户账号获取其对应的游记
	 */
	@Override
	public List<Strategy> getStrategyByAccount(String account) {
		List<Strategy> sList = new ArrayList<>();
		StrategyExample strategyExample = new StrategyExample();
		Criteria criteria = strategyExample.createCriteria();
		if (account != null) {
			criteria.andAccountEqualTo(account);
			// 未删除状态
			criteria.andStatusEqualTo(true);
			sList = strategyDAO.selectByExampleWithBLOBs(strategyExample);
			if (sList != null) {
				for (Strategy strategy : sList) {
					// 根据用户账号查询用户信息
					UserInfo userInfo = userInfoService.getUserInfoByAccount(account);
					strategy.setUserInfo(userInfo);
					//查询对应的游记附图
					strategy.setStrategyImgs(strategyImgService.findByStrategyId(strategy.getStrategyId()));
				}
			}
		}
		return sList;
	}

	/**
	 * 获取所有游记
	 */
	@Override
	public PageInfo<Strategy> getAll(Integer pageNum, Integer pageSize) {
		StrategyExample strategyExample = new StrategyExample();
		PageHelper.startPage(pageNum, pageSize);
		PageHelper.orderBy("upload_date desc");
		Criteria criteria = strategyExample.createCriteria();
		criteria.andStatusEqualTo(true);
		// 剔除管理员发布的游记
		criteria.andAccountNotEqualTo("admin");
		List<Strategy> strategies = strategyDAO.selectByExampleWithBLOBs(strategyExample);
		if (strategies != null) {
			for (Strategy strategy : strategies) {
				// 根据用户账号查询用户信息
				UserInfo userInfo = userInfoService.getUserInfoByAccount(strategy.getAccount());
				strategy.setUserInfo(userInfo);
				//查询对应的游记附图
				strategy.setStrategyImgs(strategyImgService.findByStrategyId(strategy.getStrategyId()));
			}
		}
		PageInfo<Strategy> pageInfo = new PageInfo<Strategy>(strategies);
		return pageInfo;
	}

	/**
	 * 获取管理员推荐的游记
	 */
	@Override
	public List<Strategy> getRecommendStrategy() {
		StrategyExample strategyExample = new StrategyExample();
		Criteria criteria = strategyExample.createCriteria();
		criteria.andAccountEqualTo("admin");
		criteria.andStatusEqualTo(true);
		List<Strategy> strategies = strategyDAO.selectByExampleWithBLOBs(strategyExample);
		if (strategies != null) {
			for (Strategy strategy : strategies) {
				// 根据用户账号查询用户信息
				UserInfo userInfo = userInfoService.getUserInfoByAccount(strategy.getAccount());
				strategy.setUserInfo(userInfo);
				//查询对应的游记附图
				strategy.setStrategyImgs(strategyImgService.findByStrategyId(strategy.getStrategyId()));
			}
		}
		return strategies;
	}

	/**
	 * 根据标题模糊查询游记
	 */
	@Override
	public List<Strategy> getStrategyByTitle(String title) {
		StrategyExample strategyExample = new StrategyExample();
		Criteria criteria = strategyExample.createCriteria();
		criteria.andTitleLike("%" + title + "%");
		return strategyDAO.selectByExample(strategyExample);
	}

	/**
	 * 增加浏览次数
	 */
	@Override
	public int addBrowseVolume(String strategyId) {
		Strategy strategy = strategyDAO.selectByPrimaryKey(strategyId);
		int browseVolume = strategy.getBrowseVolume() == null ? 0 : strategy.getBrowseVolume();
		strategy.setBrowseVolume(browseVolume + 1);
		return strategyDAO.updateByPrimaryKeySelective(strategy);
	}

}
