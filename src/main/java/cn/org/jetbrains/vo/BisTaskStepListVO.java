package cn.org.jetbrains.vo;

import cn.org.jetbrains.entity.BisTask;
import cn.org.jetbrains.entity.BisTaskStep;
import com.diboot.core.binding.annotation.BindField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 任务步骤表 ListVO定义
 *
 * @author MyName
 * @version 1.0
 * @date 2020-07-21
 * Copyright © MyCompany
 */
@Getter
@Setter
@Accessors(chain = true)
public class BisTaskStepListVO extends BisTaskStep {
    private static final long serialVersionUID = -4484641659469399873L;

    /**
     * 关联字段：BisTask.taskName
     */
    @BindField(entity = BisTask.class, field = "taskName", condition = "this.bis_task_id=id")
    private String bisTaskTaskName;

}