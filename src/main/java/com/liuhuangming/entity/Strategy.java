package com.liuhuangming.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * tb_strategy
 * @author 
 */
public class Strategy implements Serializable {
    /**
     * 游记ID
     */
    private String strategyId;

    /**
     * 账号
     */
    private String account;

    /**
     * 游记标题
     */
    private String title;

    /**
     * 游记附图
     */
    private String imgSrc;

    /**
     * 游记上传时间
     */
    @JsonFormat(pattern="MM-dd HH:mm:ss",timezone="GMT+8")
    private Date uploadDate;

    /**
     * 所属城市
     */
    private String city;

    /**
     * 点赞数
     */
    private Integer likeNum;

    /**
     * 收藏数
     */
    private Integer collectNum;

    /**
     * 评论数
     */
    private Integer commentNum;

    /**
     * 状态：0表示已删除，1表示未删除
     */
    private Boolean status;

    /**
     * 浏览量
     */
    private Integer browseVolume;

    /**
     * 游记内容
     */
    private String content;

    /**
     * 用户信息
     */
    private UserInfo userInfo;
    /**
     * 图片集合
     */
    private List<StrategyImg> strategyImgs;
    
    
    public List<StrategyImg> getStrategyImgs() {
		return strategyImgs;
	}

	public void setStrategyImgs(List<StrategyImg> strategyImgs) {
		this.strategyImgs = strategyImgs;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	private static final long serialVersionUID = 1L;

    public String getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getBrowseVolume() {
        return browseVolume;
    }

    public void setBrowseVolume(Integer browseVolume) {
        this.browseVolume = browseVolume;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        Strategy other = (Strategy) that;
        return (this.getStrategyId() == null ? other.getStrategyId() == null : this.getStrategyId().equals(other.getStrategyId()))
            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getImgSrc() == null ? other.getImgSrc() == null : this.getImgSrc().equals(other.getImgSrc()))
            && (this.getUploadDate() == null ? other.getUploadDate() == null : this.getUploadDate().equals(other.getUploadDate()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getLikeNum() == null ? other.getLikeNum() == null : this.getLikeNum().equals(other.getLikeNum()))
            && (this.getCollectNum() == null ? other.getCollectNum() == null : this.getCollectNum().equals(other.getCollectNum()))
            && (this.getCommentNum() == null ? other.getCommentNum() == null : this.getCommentNum().equals(other.getCommentNum()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getBrowseVolume() == null ? other.getBrowseVolume() == null : this.getBrowseVolume().equals(other.getBrowseVolume()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStrategyId() == null) ? 0 : getStrategyId().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getImgSrc() == null) ? 0 : getImgSrc().hashCode());
        result = prime * result + ((getUploadDate() == null) ? 0 : getUploadDate().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getLikeNum() == null) ? 0 : getLikeNum().hashCode());
        result = prime * result + ((getCollectNum() == null) ? 0 : getCollectNum().hashCode());
        result = prime * result + ((getCommentNum() == null) ? 0 : getCommentNum().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getBrowseVolume() == null) ? 0 : getBrowseVolume().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", strategyId=").append(strategyId);
        sb.append(", account=").append(account);
        sb.append(", title=").append(title);
        sb.append(", imgSrc=").append(imgSrc);
        sb.append(", uploadDate=").append(uploadDate);
        sb.append(", city=").append(city);
        sb.append(", likeNum=").append(likeNum);
        sb.append(", collectNum=").append(collectNum);
        sb.append(", commentNum=").append(commentNum);
        sb.append(", status=").append(status);
        sb.append(", browseVolume=").append(browseVolume);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}