package cn.org.jetbrains.dto;

import cn.org.jetbrains.entity.BisTask;
import cn.org.jetbrains.entity.BisTaskStep;
import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.diboot.core.util.D;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 任务步骤表 DTO定义
 *
 * @author MyName
 * @version 1.0
 * @date 2020-07-21
 * Copyright © MyCompany
 */
@Getter
@Setter
@Accessors(chain = true)
public class BisTaskStepDTO extends BisTaskStep {

    private static final long serialVersionUID = -1157969541840789562L;

    /**
     * 关联字段 BisTask.taskName
     */
    @BindQuery(comparison = Comparison.EQ, entity = BisTask.class, field = "taskName", condition = "this.bis_task_id=id")
    private String bisTaskTaskName;

    /**
     * 任务步骤开始时间-起始
     */
    @BindQuery(comparison = Comparison.GE, field = "taskStepStartTime")
    private Date taskStepStartTime;

    /**
     * 任务步骤开始时间-截止
     */
    @BindQuery(comparison = Comparison.LT, field = "taskStepStartTime")
    private Date taskStepStartTimeEnd;

    /**
     * 任务步骤结束时间-起始
     */
    @BindQuery(comparison = Comparison.GE, field = "taskStepEndTime")
    private Date taskStepEndTime;

    /**
     * 任务步骤结束时间-截止
     */
    @BindQuery(comparison = Comparison.LT, field = "taskStepEndTime")
    private Date taskStepEndTimeEnd;

    @Override()
    public Date getTaskStepStartTime() {
        return this.taskStepStartTime;
    }

    @Override()
    public BisTaskStep setTaskStepStartTime(Date taskStepStartTime) {
        this.taskStepStartTime = taskStepStartTime;
        return this;
    }

    public Date getTaskStepStartTimeEnd() {
        return D.nextDay(taskStepStartTime);
    }

    public BisTaskStep setTaskStepStartTimeEnd(Date taskStepStartTimeEnd) {
        this.taskStepStartTimeEnd = taskStepStartTimeEnd;
        return this;
    }

    @Override()
    public Date getTaskStepEndTime() {
        return this.taskStepEndTime;
    }

    @Override()
    public BisTaskStep setTaskStepEndTime(Date taskStepEndTime) {
        this.taskStepEndTime = taskStepEndTime;
        return this;
    }

    public Date getTaskStepEndTimeEnd() {
        return D.nextDay(taskStepEndTime);
    }

    public BisTaskStep setTaskStepEndTimeEnd(Date taskStepEndTimeEnd) {
        this.taskStepEndTimeEnd = taskStepEndTimeEnd;
        return this;
    }
}
