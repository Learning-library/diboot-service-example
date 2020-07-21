package cn.org.jetbrains.vo;

import cn.org.jetbrains.entity.BisTask;
import com.diboot.core.binding.annotation.BindDict;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 任务表 ListVO定义
 *
 * @author MyName
 * @version 1.0
 * @date 2020-07-21
 * Copyright © MyCompany
 */
@Getter
@Setter
@Accessors(chain = true)
public class BisTaskListVO extends BisTask {
    private static final long serialVersionUID = -2802116266364611828L;

    /**
     * 关联字典：TASK_TYPE
     */
    @BindDict(type = DICT_TASK_TYPE, field = "taskType")
    private String taskTypeLabel;

}