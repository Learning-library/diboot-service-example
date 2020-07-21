package cn.org.jetbrains.service.impl;

import cn.org.jetbrains.entity.BisTaskStep;
import cn.org.jetbrains.mapper.BisTaskStepMapper;
import cn.org.jetbrains.service.BisTaskStepService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 任务步骤表相关Service实现
 *
 * @author MyName
 * @version 1.0
 * @date 2020-07-21
 * Copyright © MyCompany
 */
@Service
@Slf4j
public class BisTaskStepServiceImpl extends BaseCustomServiceImpl<BisTaskStepMapper, BisTaskStep> implements BisTaskStepService {

}
