package cn.org.jetbrains.mapper;

import cn.org.jetbrains.entity.BisTask;
import com.diboot.core.mapper.BaseCrudMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 任务表Mapper
 *
 * @author MyName
 * @version 1.0
 * @date 2020-07-21
 * Copyright © MyCompany
 */
@Mapper
public interface BisTaskMapper extends BaseCrudMapper<BisTask> {

}

