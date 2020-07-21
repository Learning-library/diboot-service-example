package cn.org.jetbrains.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.diboot.core.util.D;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 任务步骤表 Entity定义
 *
 * @author MyName
 * @version 1.0
 * @date 2020-07-21
 * Copyright © MyCompany
 */
@ApiModel(value = "任务步骤表")
@Getter
@Setter
@Accessors(chain = true)
public class BisTaskStep extends BaseCustomEntity {
    private static final long serialVersionUID = -544201668170335498L;

    /**
     * 任务表
     */
    @ApiModelProperty(value = "任务表", required = true, example = "1234567890")
    @NotNull(message = "任务表不能为空")
    @TableField()
    private Long bisTaskId;

    /**
     * 任务步骤名称
     */
    @ApiModelProperty(value = "任务步骤名称", required = true, example = "任务步骤名称样例内容")
    @NotNull(message = "任务步骤名称不能为空")
    @Length(max = 100, message = "任务步骤名称长度应小于100")
    @BindQuery(comparison = Comparison.LIKE)
    @TableField()
    private String taskStepName;

    /**
     * 任务步骤描述
     */
    @ApiModelProperty(value = "任务步骤描述", example = "")
    @Length(max = 100, message = "任务步骤描述长度应小于100")
    @TableField()
    private String taskStepDesc;

    /**
     * 任务步骤开始时间
     */
    @ApiModelProperty(value = "任务步骤开始时间", example = "2020-07-21 00:00")
    @JSONField(format = D.FORMAT_DATETIME_Y4MDHM)
    @TableField()
    private Date taskStepStartTime;

    /**
     * 任务步骤结束时间
     */
    @ApiModelProperty(value = "任务步骤结束时间", example = "2020-07-21 00:00")
    @JSONField(format = D.FORMAT_DATETIME_Y4MDHM)
    @TableField()
    private Date taskStepEndTime;

    /**
     * 任务步骤单价
     */
    @ApiModelProperty(value = "任务步骤单价", example = "")
    @TableField()
    private Long taskStepPrice;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", example = "2020-07-14 23:20")
    @JSONField(format = D.FORMAT_DATETIME_Y4MDHM)
    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NOT_NULL)
    private Date updateTime;


} 
