package cn.org.jetbrains.vo;

import cn.org.jetbrains.entity.BisTask;
import cn.org.jetbrains.entity.BisTaskStep;
import com.diboot.core.binding.annotation.BindEntity;
import com.diboot.core.binding.annotation.BindField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 任务步骤表 DetailVO定义
 *
 * @author MyName
 * @version 1.0
 * @date 2020-07-21
 * Copyright © MyCompany
 */
@Getter
@Setter
@Accessors(chain = true)
public class BisTaskStepDetailVO extends BisTaskStep {
    private static final long serialVersionUID = 3492958512625903097L;

    /**
     * 关联字段：BisTask.taskName
     */
    @BindField(entity = BisTask.class, field = "taskName", condition = "this.bis_task_id=id")
    private String bisTaskTaskName;

    /**
     * 关联对象：BisTask
     */
    @BindEntity(entity = BisTask.class, condition = "this.bis_task_id=id")
    private BisTask bisTask;

}