package cn.org.jetbrains.service.impl;

import cn.org.jetbrains.entity.BisTask;
import cn.org.jetbrains.mapper.BisTaskMapper;
import cn.org.jetbrains.service.BisTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 任务表相关Service实现
 *
 * @author MyName
 * @version 1.0
 * @date 2020-07-21
 * Copyright © MyCompany
 */
@Service
@Slf4j
public class BisTaskServiceImpl extends BaseCustomServiceImpl<BisTaskMapper, BisTask> implements BisTaskService {

}
