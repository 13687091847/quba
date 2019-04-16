package com.liuhuangming.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_strategy_img
 * @author 
 */
public class StrategyImg implements Serializable {
    private Integer strategyImgId;

    /**
     * 游记ID
     */
    private String strategyId;

    /**
     * 图片url
     */
    private String imgUrl;

    /**
     * 上传时间
     */
    private Date uploadTime;

    private static final long serialVersionUID = 1L;

    public Integer getStrategyImgId() {
        return strategyImgId;
    }

    public void setStrategyImgId(Integer strategyImgId) {
        this.strategyImgId = strategyImgId;
    }

    public String getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
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
        StrategyImg other = (StrategyImg) that;
        return (this.getStrategyImgId() == null ? other.getStrategyImgId() == null : this.getStrategyImgId().equals(other.getStrategyImgId()))
            && (this.getStrategyId() == null ? other.getStrategyId() == null : this.getStrategyId().equals(other.getStrategyId()))
            && (this.getImgUrl() == null ? other.getImgUrl() == null : this.getImgUrl().equals(other.getImgUrl()))
            && (this.getUploadTime() == null ? other.getUploadTime() == null : this.getUploadTime().equals(other.getUploadTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStrategyImgId() == null) ? 0 : getStrategyImgId().hashCode());
        result = prime * result + ((getStrategyId() == null) ? 0 : getStrategyId().hashCode());
        result = prime * result + ((getImgUrl() == null) ? 0 : getImgUrl().hashCode());
        result = prime * result + ((getUploadTime() == null) ? 0 : getUploadTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", strategyImgId=").append(strategyImgId);
        sb.append(", strategyId=").append(strategyId);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", uploadTime=").append(uploadTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}