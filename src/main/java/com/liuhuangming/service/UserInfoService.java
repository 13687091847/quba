		package com.liuhuangming.service;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.liuhuangming.bean.Mes;
import com.liuhuangming.entity.Message;
import com.liuhuangming.entity.Order;
import com.liuhuangming.entity.OrderDetail;
import com.liuhuangming.entity.UserInfo;


public interface UserInfoService {

	/**
	 * 修改用户信息
	 * @param userInfo
	 * @return
	 */
	Mes updateUserInfo(UserInfo userInfo,HttpSession httpSession);
	/**
	  * 验证用户是否存在
	 * 
	 * @param account
	 * @return
	 */
	boolean checkUser(String account);

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	Mes regist(UserInfo user);

	/**
	  * 用户登录
	 * 
	 * @param user
	 * @return
	 */
	Mes login(UserInfo user,HttpSession httpSession);

	/**
	  * 判断用户是否已登录
	 * 
	 * @param httpSession
	 * @return
	 */
	Object isLogin(HttpSession httpSession);
	/**
	  * 获取用户的详细信息
	 * 
	 * @param httpSession
	 * @return
	 */
	UserInfo getAll(HttpSession httpSession);
	/**
	 * 用户退出登录
	 * @param httpSession
	 * @return
	 */
	Mes quit(HttpSession httpSession);
	/**
	 * 用户注销账号
	 * @param httpSession
	 * @return
	 */
	Mes logout(HttpSession httpSession);
	/**
	 * 验证用户邮箱是否正确
	 * @param httpSession
	 * @return
	 */
	boolean checkEmail(String email);
	/**
	 * 用户获取验证码
	 * @param email
	 * @return
	 */
	Mes getEmailCode(String email,HttpSession httpSession);
	/**
	 * 验证用户验证码
	 * @param code
	 * @param httpSession
	 * @return
	 */
	boolean checkCode(String code,HttpSession httpSession);
	/**
	 * 用户重设密码
	 * @param account
	 * @param password
	 * @return
	 */
	Mes resetPassWord(String account,String password);
	/**
	 * 用户上传头像
	 * @param file
	 * @return
	 */
	Mes uploadImg(MultipartFile file,HttpSession session);
	/**
	 * 根据用户Id获取用户信息
	 * @param account
	 * @return
	 */
	UserInfo getUserInfoByAccount(String account);
	/**
	 * 验证用户的支付密码
	 * @param session
	 * @return
	 */
	boolean checkOrderPassWord(HttpSession session,String password);
	/**
	 * 给账号充钱
	 * @param money
	 * @param session
	 * @return
	 */
	Mes addMoney(BigDecimal money,HttpSession session);
	/**
	 * 用户提交订单
	 * @param order
	 * @param session
	 * @return
	 */
	Mes submitOrder(List<OrderDetail> orderDetails,HttpSession session);
	/**
	 * 用户支付订单
	 * @param money
	 * @param session
	 * @return
	 */
	Mes payOrder(String orderId,HttpSession session);
	/**
	 * 验证账号余额是否充足
	 * @param money
	 * @param session
	 * @return
	 */
	boolean checkMoney(BigDecimal money,HttpSession session);
	/**
	 * 获取当前登录用户的所有订单
	 * @param session
	 * @return
	 */
	List<Order> getOrders(HttpSession session);
	/**
	 * 用户取消订单
	 * @param orderId
	 * @return
	 */
	int cancelOrder(String orderId);
	/**
	 * 根据用户名获取对应的关注人
	 * @param account
	 * @return
	 */
	List<UserInfo> getFollowerUser(String account);
	/**
	 * 根据用户名获取对应的粉丝
	 * @param account
	 * @return
	 */
	List<UserInfo> getFansUser(String account);
	/**
	 * 查询followedAccountOrFansAccount是否关注account（就是查询后面的用户是否关注前面的用户）
	 * @param account
	 * @param followerAccount
	 * @return
	 */
	boolean checkIsFollowEachOther(String account,String followerAccount);
	/**
	 * 查询当前登录用户是否关注该用户
	 * @param session
	 * @param otherAccount
	 * @return
	 */
	boolean checkIsFollow(HttpSession session,String otherAccount);
}
