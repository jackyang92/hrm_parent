package com.ymy.hrm.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 商品收藏
 * </p>
 *
 * @author ymy
 * @since 2019-09-16
 */
@TableName("t_vip_course_collect")
public class VipCourseCollect extends Model<VipCourseCollect> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long createTime;
    /**
     * 登录用户
     */
    private Long ssoId;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * SKU ID
     */
    private Long skuId;


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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "VipCourseCollect{" +
        ", id=" + id +
        ", createTime=" + createTime +
        ", ssoId=" + ssoId +
        ", productId=" + productId +
        ", skuId=" + skuId +
        "}";
    }
}
