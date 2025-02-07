package cn.org.jetbrains.handler;

import com.diboot.core.handler.DefaultExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 通用异常处理类
 *
 * @author MyName
 * @version 1.0
 * @date 2020-07-21
 * Copyright © MyCompany
 */
@ControllerAdvice
@Slf4j
public class GeneralExceptionHandler extends DefaultExceptionHandler {

}