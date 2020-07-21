package cn.org.jetbrains.dto;

import cn.org.jetbrains.entity.BisTask;
import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.diboot.core.util.D;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 任务表 DTO定义
 *
 * @author MyName
 * @version 1.0
 * @date 2020-07-21
 * Copyright © MyCompany
 */
@Getter
@Setter
@Accessors(chain = true)
public class BisTaskDTO extends BisTask {

    private static final long serialVersionUID = 1560030003081618909L;

    /**
     * 创建时间-起始
     */
    @BindQuery(comparison = Comparison.GE, field = "createTime")
    private Date createTimeBegin;

    /**
     * 创建时间-截止
     */
    @BindQuery(comparison = Comparison.LT, field = "createTime")
    private Date createTimeEnd;

    public Date getCreateTimeBegin() {
        return this.createTimeBegin;
    }

    public BisTask setCreateTimeBegin(Date createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
        return this;
    }

    public Date getCreateTimeEnd() {
        return this.createTimeEnd;
    }

    public BisTask setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = D.nextDay(createTimeEnd);
        return this;
    }
}
