package com.mycloud.api.common;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/9/26
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    private Integer version;

    private Long createBy;

    private Long UpdateBy;

    private Date createTime;

    private Date updateTime;

}