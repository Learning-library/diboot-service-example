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
 * 任务表 Entity定义
 *
 * @author MyName
 * @version 1.0
 * @date 2020-07-21
 * Copyright © MyCompany
 */
@ApiModel(value = "任务表")
@Getter
@Setter
@Accessors(chain = true)
public class BisTask extends BaseCustomEntity {
    /**
     * task_type字段的关联字典
     */
    public static final String DICT_TASK_TYPE = "TASK_TYPE";
    private static final long serialVersionUID = -8845204535443899906L;
    /**
     * 任务名称
     */
    @ApiModelProperty(value = "任务名称", required = true, example = "任务名称样例内容")
    @NotNull(message = "任务名称不能为空")
    @Length(max = 100, message = "任务名称长度应小于100")
    @BindQuery(comparison = Comparison.LIKE)
    @TableField()
    private String taskName;

    /**
     * 任务描述
     */
    @ApiModelProperty(value = "任务描述", example = "")
    @Length(max = 100, message = "任务描述长度应小于100")
    @TableField()
    private String taskDesc;

    /**
     * 任务类型
     */
    @ApiModelProperty(value = "任务类型 ", example = "0")
    @TableField()
    private Integer taskType;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", example = "2020-07-14 23:13")
    @JSONField(format = D.FORMAT_DATE_Y4MD)
    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NOT_NULL)
    private Date updateTime;


} 
