package com.liuhuangming.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * tb_comment
 * @author 
 */
public class Comment implements Serializable {
    /**
     * 评论ID
     */
    private Integer commentId;

    /**
     * 账号
     */
    private String account;

    /**
     * 回复数量
     */
    private Integer replyNum;

    /**
     * 游记ID
     */
    private String strategyId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    @JsonFormat(pattern="MM-dd HH:mm:ss",timezone="GMT+8")
    private Date commentDate;

    /**
     * 点赞数量
     */
    private Integer likeNum;

    /**
     * 状态：0表示已删除，1表示未删除
     */
    private Boolean status;
    /**
     * 该评论的作者的信息
     */
    private UserInfo userInfo;

    /**
     * 是否为当前登录用户的评论（数据库无此字段）
     */
    private boolean owner;
    
    /**
     * 判断当前用户是否点赞该评论
     */
    private boolean like;
    /**
     * 该条评论评论的游记
     */
	private Strategy strategy;
	/**
	 * 当前登录用户是否关注该评论的发布者
	 */
    public Boolean follow;
    
    public Boolean getFollow() {
		return follow;
	}

	public void setFollow(Boolean follow) {
		this.follow = follow;
	}

	public boolean isOwner() {
		return owner;
	}

	public void setOwner(boolean owner) {
		this.owner = owner;
	}

	public boolean isLike() {
		return like;
	}

	public void setLike(boolean like) {
		this.like = like;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	private static final long serialVersionUID = 1L;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public String getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
        Comment other = (Comment) that;
        return (this.getCommentId() == null ? other.getCommentId() == null : this.getCommentId().equals(other.getCommentId()))
            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getReplyNum() == null ? other.getReplyNum() == null : this.getReplyNum().equals(other.getReplyNum()))
            && (this.getStrategyId() == null ? other.getStrategyId() == null : this.getStrategyId().equals(other.getStrategyId()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getCommentDate() == null ? other.getCommentDate() == null : this.getCommentDate().equals(other.getCommentDate()))
            && (this.getLikeNum() == null ? other.getLikeNum() == null : this.getLikeNum().equals(other.getLikeNum()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCommentId() == null) ? 0 : getCommentId().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getReplyNum() == null) ? 0 : getReplyNum().hashCode());
        result = prime * result + ((getStrategyId() == null) ? 0 : getStrategyId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getCommentDate() == null) ? 0 : getCommentDate().hashCode());
        result = prime * result + ((getLikeNum() == null) ? 0 : getLikeNum().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", commentId=").append(commentId);
        sb.append(", account=").append(account);
        sb.append(", replyNum=").append(replyNum);
        sb.append(", strategyId=").append(strategyId);
        sb.append(", content=").append(content);
        sb.append(", commentDate=").append(commentDate);
        sb.append(", likeNum=").append(likeNum);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}