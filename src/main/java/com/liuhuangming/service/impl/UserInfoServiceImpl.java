package com.liuhuangming.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.liuhuangming.bean.IDUtils;
import com.liuhuangming.bean.Mes;
import com.liuhuangming.bean.Upload;
import com.liuhuangming.entity.Follow;
import com.liuhuangming.entity.Message;
import com.liuhuangming.entity.Order;
import com.liuhuangming.entity.OrderDetail;
import com.liuhuangming.entity.UserInfo;
import com.liuhuangming.entity.UserInfoExample;
import com.liuhuangming.entity.UserInfoExample.Criteria;
import com.liuhuangming.mapper.UserInfoDAO;
import com.liuhuangming.service.FollowService;
import com.liuhuangming.service.MessageService;
import com.liuhuangming.service.OrderDetailService;
import com.liuhuangming.service.OrderService;
import com.liuhuangming.service.StrategyService;
import com.liuhuangming.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDAO userInfoDAO;
	@Autowired
	private FollowService followService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private StrategyService strategyService;
	@Resource(name = "mailSender")
	JavaMailSenderImpl mailSender;
	// 定义邮件的发送方
	private static final String FROM_EMAIL = "357066443@qq.com";

	/**
	 * 验证用户是否存在(如果返回true则说明用户不存在，false说明存在)
	 */
	@Override
	public boolean checkUser(String account) {
		UserInfoExample userExample = new UserInfoExample();
		Criteria cheCri = userExample.createCriteria();
		cheCri.andAccountEqualTo(account);
		long count = userInfoDAO.countByExample(userExample);
		return count == 0;
	}

	/**
	 * 用户注册
	 */
	@Override
	public Mes regist(UserInfo userInfo) {
		Mes mes = new Mes();
		try {
			if (checkUser(userInfo.getAccount()) == false) {
				// 用户之前已注册
				mes.setCode(500);
			} else {
				// 初始化钱包金额
				userInfo.setMoney(new BigDecimal(0));
				userInfo.setFansNum(0);
				userInfo.setFollowerNum(0);
				int resultCode = userInfoDAO.insert(userInfo);
				if (resultCode > 0) {
					mes.setCode(200);
					// 发送欢迎消息给用户
					Message message = new Message();
					message.setTitle("欢迎 " + userInfo.getAccount());
					message.setContent("感谢您注册去吧APP，如您在使用的过程中遇到任何Bug，请及时联系开发人员，QQ:357066443@qq.com <br>谢谢");
					message.setSendTo(userInfo.getAccount());
					message.setStatus((byte) 0);
					message.setSendFrom("去吧管理员");
					int insertCode = messageService.sendMessage(message);
					if (insertCode > 0) {
						System.out.println("发送成功");
					} else {
						System.out.println("发送失败");
					}
				} else {
					mes.setCode(500);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			mes.setCode(500);
		}
		return mes;
	}

	/**
	 * 用户登录
	 */
	@Override
	public Mes login(UserInfo userInfo, HttpSession httpSession) {
		Mes message = new Mes();
		String password = userInfo.getPassword();
		if (checkUser(userInfo.getAccount()) != true) {
			userInfo = userInfoDAO.selectByPrimaryKey(userInfo.getAccount());
			if (userInfo.getPassword().equals(password)) {
				message.setCode(200);
				httpSession.setAttribute("account", userInfo.getAccount());
			} else {
				message.setCode(500);
			}
		} else {
			message.setCode(500);
		}
		return message;
	}

	/**
	 * 判断用户是否登录,如果登录则返回当前登录用户的详细信息，否则返回null
	 */
	@Override
	public Object isLogin(HttpSession httpSession) {
		String account = (String) httpSession.getAttribute("account");
		if (account != null) {
			UserInfo userInfo = getAll(httpSession);
			return userInfo;
		} else {
			return null;
		}
	}

	/**
	 * 获取当前登录用户的信息
	 */
	@Override
	public UserInfo getAll(HttpSession httpSession) {
		String account = (String) httpSession.getAttribute("account");
		return getUserInfoByAccount(account);
	}

	/**
	 * 更新当前登录用户信息
	 */
	@Override
	public Mes updateUserInfo(UserInfo userInfo, HttpSession httpSession) {

		Mes message = new Mes();
		String account = (String) httpSession.getAttribute("account");
		userInfo.setAccount(account);
		int resultCode = userInfoDAO.updateByPrimaryKeySelective(userInfo);
		if (resultCode > 0) {
			message.setCode(200);
			message.setMessage("修改成功");
		} else {
			message.setCode(500);
		}
		return message;
	}

	/**
	 * 用户退出登录
	 */
	@Override
	public Mes quit(HttpSession httpSession) {

		Mes message = new Mes();
		httpSession.removeAttribute("account");
		if (httpSession.getAttribute("account") == null) {
			message.setCode(200);
		} else {
			message.setCode(500);
		}
		return message;
	}

	/**
	 * 用户注销账号
	 */
	@Override
	public Mes logout(HttpSession httpSession) {

		Mes message = new Mes();
		String account = (String) httpSession.getAttribute("account");
		httpSession.removeAttribute("account");
		int result = userInfoDAO.deleteByPrimaryKey(account);
		if (result > 0) {
			message.setCode(200);
		} else {
			message.setCode(500);
		}
		return message;
	}

	/**
	 * 验证用户邮箱是否正确
	 */
	@Override
	public boolean checkEmail(String email) {

		// 判断参数不为空
		if (email == null) {
			return false;
		}
		UserInfoExample userInfoExample = new UserInfoExample();
		Criteria checkCri = userInfoExample.createCriteria();
		checkCri.andEmailEqualTo(email);
		List<UserInfo> uList = userInfoDAO.selectByExample(userInfoExample);
		System.err.println(uList.size());
		if (null != uList && uList.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 发送验证码至邮箱
	 */
	@Override
	public Mes getEmailCode(String email, HttpSession httpSession) {

		Mes message = new Mes();
		// 获取上次获得验证码的时间，若时间在15分钟内，则不改变验证码
		Date lastTime = (Date) httpSession.getAttribute("createCodeTime");
		// 获取上一次生成的验证码
		String code = (String) httpSession.getAttribute("code");

		// 获取当前时间
		Date currTime = new Date();
		if ((null != lastTime) && (null != code)) {// 非首次获取验证码
			// 时间差
			long diff = (currTime.getTime() - lastTime.getTime()) / 60 / 1000;
			if (diff > 15) {// 超时
				// 随机生成六位数验证码x
				code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
			}
		} else {// 首次或者时间超过15分钟
				// 随机生成六位数验证码
			code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
		}

		// 发送邮件
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email);
		mailMessage.setFrom(FROM_EMAIL);
		mailMessage.setSubject("密码修改-去吧");
		mailMessage.setText("您的验证码为： " + code + "\n15分钟内有效！");
		mailSender.send(mailMessage);

		// 储存验证码和生成时间
		httpSession.setAttribute("createCodeTime", currTime);
		httpSession.setAttribute("code", code);
		message.setCode(200);

		return message;
	}

	/**
	 * 验证用户验证码
	 */
	@Override
	public boolean checkCode(String code, HttpSession httpSession) {

		String lastCode = (String) httpSession.getAttribute("code");
		if (code != null && code.equals(lastCode)) {
			return true;
		}
		return false;
	}

	/**
	 * 用户重设密码
	 */
	@Override
	public Mes resetPassWord(String account, String password) {

		UserInfo userInfo = new UserInfo();
		Mes message = new Mes();
		userInfo.setAccount(account);
		userInfo.setPassword(password);
		int result = userInfoDAO.updateByPrimaryKeySelective(userInfo);
		if (result > 0) {
			message.setCode(200);
			return message;
		}
		message.setCode(500);
		return message;
	}

	/**
	 * 用户上传图片
	 */
	@Override
	public Mes uploadImg(MultipartFile file, HttpSession session) {
		Mes message = new Mes();
		try {
			String imgUrl = Upload.uploadFile(file);
			if (imgUrl != null) {
				message.setCode(200);
				message.setMessage(imgUrl);
			} else {
				message.setCode(500);
			}
		} catch (Exception e) {

		}
		return message;
	}

	/**
	 * 根据用户account查询用户信息
	 */
	@Override
	public UserInfo getUserInfoByAccount(String account) {
		UserInfo userInfo = new UserInfo();
		try {
			userInfo = userInfoDAO.selectByPrimaryKeyWithoutPassword(account);
			// 获取关注数
			List<Follow> followsList = followService.getAllByAccount(account);
			if (followsList.isEmpty() == true) {
				userInfo.setFollowerNum(0);
			} else {
				userInfo.setFollowerNum(followsList.size());
			}
			// 获取粉丝数
			List<Follow> fansList = followService.getAllByFollowedUser(account);
			if (fansList.isEmpty() == true) {
				userInfo.setFansNum(0);
			} else {
				userInfo.setFansNum(fansList.size());
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return userInfo;
	}

	/**
	 * 验证用户的支付密码
	 */
	@Override
	public boolean checkOrderPassWord(HttpSession session, String password) {
		String account = (String) session.getAttribute("account");
		if (account != null) {
			UserInfo userInfo = userInfoDAO.selectByPrimaryKey(account);
			if (password.equals(userInfo.getPassword())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 给当前登录账号充钱
	 */
	@Override
	public Mes addMoney(BigDecimal money, HttpSession session) {

		String account = (String) session.getAttribute("account");
		Mes message = new Mes();
		if (account != null) {
			UserInfo userInfo = userInfoDAO.selectByPrimaryKey(account);
			userInfo.setMoney(money.add(userInfo.getMoney()));
			int updateCode = userInfoDAO.updateByPrimaryKey(userInfo);
			if (updateCode > 0) {
				message.setCode(200);
				return message;
			}
		}
		message.setCode(500);
		return message;
	}

	/**
	 * 用户支付订单
	 */
	@Override
	public Mes payOrder(String orderId, HttpSession session) {
		String account = (String) session.getAttribute("account");
		Mes message = new Mes();
		Order order = orderService.selectByOrderId(orderId);
		if (order != null) {
			BigDecimal money = order.getTotalPrice();
			if (account != null && checkMoney(money, session)) {
				// 余额充足,相应减少用户余额
				UserInfo userInfo = userInfoDAO.selectByPrimaryKeyWithoutPassword(account);
				userInfo.setMoney(userInfo.getMoney().subtract(money));
				int updateUserInfoCode = userInfoDAO.updateByPrimaryKeySelective(userInfo);
				// 修改订单信息为已支付
				int updateOrderCode = orderService.payOrder(orderId);
				if (updateUserInfoCode > 0 && updateOrderCode > 0) {
					message.setCode(200);
					return message;
				}
			}
		}
		message.setCode(500);
		return message;
	}

	/**
	 * 判断用户余额是否充足
	 */
	@Override
	public boolean checkMoney(BigDecimal money, HttpSession session) {
		String account = (String) session.getAttribute("account");
		if (account != null) {
			UserInfo userInfo = userInfoDAO.selectByPrimaryKeyWithoutPassword(account);
			if (userInfo.getMoney().compareTo(money) == 1) {
				// 余额充足
				return true;
			}
		}
		return false;
	}

	/**
	 * 用户提交订单
	 */
	@Override
	public Mes submitOrder(List<OrderDetail> orderDetails, HttpSession session) {
		// 首先查看订单是否重复提交
		Mes message = new Mes();
		if (orderDetailService.checkOrder(orderDetails)) {
			message.setCode(500);
			return message;
		} else {
			String account = (String) session.getAttribute("account");
			Order order = new Order();
			order.setOrderId(IDUtils.createID());
			order.setOrderStatus((byte) 0);// 设定订单状态为未支付状态
			order.setTotalPrice(new BigDecimal(0));
			order.setOrderDetails(orderDetails);
			for (OrderDetail orderDetail : orderDetails) {
				// 累加每个订单详情的金额
				order.setTotalPrice(order.getTotalPrice().add(orderDetail.getPrice()));
				// 为每个订单详情添加订单号
				orderDetail.setOrderId(order.getOrderId());
			}
			if (account != null) {
				order.setAccount(account);
				// 提交订单至订单表和订单详情表
				int insertOrderCode = orderService.addOrder(order);
				int insertOrderDetailCode = orderDetailService.addOrderDetail(orderDetails);
				if (insertOrderCode > 0 && insertOrderDetailCode > 0) {
					message.setCode(200);
					// 返回提交成功的订单号
					message.setMessage(order.getOrderId());
					return message;
				}
			}
		}
		message.setCode(500);
		return message;
	}

	/**
	 * 获取当前登录用户的所有订单
	 */
	@Override
	public List<Order> getOrders(HttpSession session) {
		List<Order> orders = new ArrayList<>();
		String account = (String) session.getAttribute("account");
		if (account != null) {
			orders = orderService.selectByAccount(account);
		}
		return orders;
	}

	/**
	 * 用户取消订单
	 */
	@Override
	public int cancelOrder(String orderId) {
		return orderService.cancelOrder(orderId);
	}

	/**
	 * 根据用户名获取对应的所关注人的信息集合(并统计出游记的篇数)
	 */
	@Override
	public List<UserInfo> getFollowerUser(String account) {
		List<Follow> follows = followService.getAllByAccount(account);
		List<UserInfo> userInfos = new ArrayList<>();
		for (Follow follow : follows) {
			// 查出对应用户信息
			UserInfo userInfo = userInfoDAO.selectByPrimaryKey(follow.getFollowedUser());
			// 再查出该用户的游记数量
			long count = strategyService.countByAccount(follow.getFollowedUser());
			userInfo.setStrategyNum(count);
			// 查询用户与被关注的用户是否相互关注
			userInfo.setFollowWithFollower(checkIsFollowEachOther(account, follow.getFollowedUser()));
			userInfos.add(userInfo);
		}
		return userInfos;
	}

	/**
	 * 根据用户名获取对应粉丝信息集合
	 */
	@Override
	public List<UserInfo> getFansUser(String account) {
		List<Follow> fans = followService.getAllByFollowedUser(account);
		List<UserInfo> userInfos = new ArrayList<>();
		for (Follow fan : fans) {
			// 查出对应用户信息
			UserInfo userInfo = userInfoDAO.selectByPrimaryKey(fan.getAccount());
			// 再查出该用户的游记数量
			long count = strategyService.countByAccount(fan.getAccount());
			userInfo.setStrategyNum(count);
			// 查询用户与该粉丝是否相互关注
			userInfo.setFollowWithFans(checkIsFollowEachOther(fan.getAccount(), account));
			userInfos.add(userInfo);
		}
		return userInfos;
	}

	/**
	 * 查询followedAccountOrFansAccount是否关注account
	 */
	@Override
	public boolean checkIsFollowEachOther(String account, String followedAccountOrFansAccount) {
		// 根据第二个参数的用户名去关注表中查询这个用户关注的人，然后与第一个参数的用户名是否存在刚刚的查询结果集中
		// 如果存在则说明这两个人是相互关注的，反之则不是
		List<Follow> follows = followService.getAllByAccount(followedAccountOrFansAccount);
		for (Follow follow : follows) {
			if (follow.getFollowedUser().equals(account)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 查询当前登录用户是否关注该用户
	 */
	@Override
	public boolean checkIsFollow(HttpSession session, String otherAccount) {
		String account = (String) session.getAttribute("account");
		return checkIsFollowEachOther(otherAccount, account);
	}
}
