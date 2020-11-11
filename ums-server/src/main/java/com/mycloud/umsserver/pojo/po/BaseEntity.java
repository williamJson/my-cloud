package com.mycloud.umsserver.pojo.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * 功能说明：
 * 基础entity
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/9/26
 */
@Getter
@Setter
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 标识符
     */
    private Long id;

    /**
     * 乐观锁版本
     */
    private Integer version;

    /**
     * 删除标识
     */
    private BigInteger delFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
