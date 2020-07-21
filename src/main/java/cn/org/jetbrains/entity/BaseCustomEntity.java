package cn.org.jetbrains.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.diboot.core.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 自定义BaseEntity，对diboot-core的BaseEntity做差异化定义
 *
 * @author MyName
 * @version 1.0
 * @date 2020-07-21
 * Copyright © MyCompany
 */
@Getter
@Setter
@Accessors(chain = true)
public abstract class BaseCustomEntity extends BaseEntity {
    private static final long serialVersionUID = -2267618713371952467L;

    @ApiModelProperty(hidden = true)
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(hidden = true)
    @JSONField(serialize = false)
    @TableField("is_deleted")
    private boolean deleted = false;

}
