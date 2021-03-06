package com.liuhuangming.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * tb_user_info
 * @author 
 */
public class UserInfo implements Serializable {
    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String headImg;

    /**
     * 签名
     */
    private String signature;

    /**
     * 关注数
     */
    private Integer followerNum;

    /**
     * 粉丝数
     */
    private Integer fansNum;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 出生年月
     */
    private String birthDay;

    /**
     * 钱包
     */
    private BigDecimal money;
    
    /**
     * 临时属性(该用户相应的游记篇数)
     */
    private long strategyNum;
    
    /**
     * 临时属性（用来判断当前用户与他关注的用户是否相互关注）
     */
    private Boolean followWithFollower;
    
    /**
     * 临时属性（用来判断当前用户与他关注的粉丝是否相互关注）
     */
    private Boolean followWithFans;
    
	public Boolean getFollowWithFollower() {
		return followWithFollower;
	}

	public void setFollowWithFollower(Boolean followWithFollower) {
		this.followWithFollower = followWithFollower;
	}

	public Boolean getFollowWithFans() {
		return followWithFans;
	}

	public void setFollowWithFans(Boolean followWithFans) {
		this.followWithFans = followWithFans;
	}

	public long getStrategyNum() {
		return strategyNum;
	}

	public void setStrategyNum(long strategyNum) {
		this.strategyNum = strategyNum;
	}

	private static final long serialVersionUID = 1L;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getFollowerNum() {
        return followerNum;
    }

    public void setFollowerNum(Integer followerNum) {
        this.followerNum = followerNum;
    }

    public Integer getFansNum() {
        return fansNum;
    }

    public void setFansNum(Integer fansNum) {
        this.fansNum = fansNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserInfo other = (UserInfo) that;
        return (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
            && (this.getHeadImg() == null ? other.getHeadImg() == null : this.getHeadImg().equals(other.getHeadImg()))
            && (this.getSignature() == null ? other.getSignature() == null : this.getSignature().equals(other.getSignature()))
            && (this.getFollowerNum() == null ? other.getFollowerNum() == null : this.getFollowerNum().equals(other.getFollowerNum()))
            && (this.getFansNum() == null ? other.getFansNum() == null : this.getFansNum().equals(other.getFansNum()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getBirthDay() == null ? other.getBirthDay() == null : this.getBirthDay().equals(other.getBirthDay()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getHeadImg() == null) ? 0 : getHeadImg().hashCode());
        result = prime * result + ((getSignature() == null) ? 0 : getSignature().hashCode());
        result = prime * result + ((getFollowerNum() == null) ? 0 : getFollowerNum().hashCode());
        result = prime * result + ((getFansNum() == null) ? 0 : getFansNum().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getBirthDay() == null) ? 0 : getBirthDay().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", account=").append(account);
        sb.append(", password=").append(password);
        sb.append(", nickName=").append(nickName);
        sb.append(", headImg=").append(headImg);
        sb.append(", signature=").append(signature);
        sb.append(", followerNum=").append(followerNum);
        sb.append(", fansNum=").append(fansNum);
        sb.append(", email=").append(email);
        sb.append(", birthDay=").append(birthDay);
        sb.append(", money=").append(money);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}