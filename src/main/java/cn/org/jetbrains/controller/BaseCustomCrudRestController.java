package cn.org.jetbrains.controller;

import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义通用CRUD父类RestController
 *
 * @author MyName
 * @version 1.0
 * @date 2020-07-21
 * Copyright © MyCompany
 */
@Slf4j
public class BaseCustomCrudRestController<E extends BaseEntity> extends BaseCrudRestController {

}