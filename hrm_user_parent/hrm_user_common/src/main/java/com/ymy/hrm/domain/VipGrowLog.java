package com.ymy.hrm.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 成长值记录
 * </p>
 *
 * @author ymy
 * @since 2019-09-16
 */
@TableName("t_vip_grow_log")
public class VipGrowLog extends Model<VipGrowLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long createTime;
    /**
     * 登录用户
     */
    private Long ssoId;
    /**
     * 来源
     */
    private String fromReason;
    /**
     * 成长值
     */
    private Integer score;
    /**
     * 备注
     */
    private String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getSsoId() {
        return ssoId;
    }

    public void setSsoId(Long ssoId) {
        this.ssoId = ssoId;
    }

    public String getFromReason() {
        return fromReason;
    }

    public void setFromReason(String fromReason) {
        this.fromReason = fromReason;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "VipGrowLog{" +
        ", id=" + id +
        ", createTime=" + createTime +
        ", ssoId=" + ssoId +
        ", fromReason=" + fromReason +
        ", score=" + score +
        ", remark=" + remark +
        "}";
    }
}
