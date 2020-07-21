package cn.org.jetbrains.vo;

import cn.org.jetbrains.entity.BisTask;
import cn.org.jetbrains.entity.BisTaskStep;
import com.diboot.core.binding.annotation.BindDict;
import com.diboot.core.binding.annotation.BindEntityList;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 任务表 DetailVO定义
 *
 * @author MyName
 * @version 1.0
 * @date 2020-07-21
 * Copyright © MyCompany
 */
@Getter
@Setter
@Accessors(chain = true)
public class BisTaskDetailVO extends BisTask {

    private static final long serialVersionUID = 2802524776493686244L;

    /**
     * 关联字典：TASK_TYPE
     */
    @BindDict(type = DICT_TASK_TYPE, field = "taskType")
    private String taskTypeLabel;

    // 绑定bisTaskStepList
    @BindEntityList(entity = BisTaskStep.class, condition = "this.id=bis_task_id")
    private List<BisTaskStep> bisTaskStepList;
}
