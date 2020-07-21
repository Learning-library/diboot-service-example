package cn.org.jetbrains.controller;

import cn.org.jetbrains.dto.BisTaskStepDTO;
import cn.org.jetbrains.entity.BisTaskStep;
import cn.org.jetbrains.service.BisTaskStepService;
import cn.org.jetbrains.vo.BisTaskStepDetailVO;
import cn.org.jetbrains.vo.BisTaskStepListVO;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.diboot.iam.annotation.BindPermission;
import com.diboot.iam.annotation.Operation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 任务步骤表 相关Controller
 *
 * @author MyName
 * @version 1.0
 * @date 2020-07-21
 * Copyright © MyCompany
 */
@Api(tags = {"任务步骤表"})
@RestController
@RequestMapping("/bisTaskStep")
@BindPermission(name = "任务步骤表")
@Slf4j
public class BisTaskStepController extends BaseCustomCrudRestController<BisTaskStep> {
    @Autowired
    private BisTaskStepService bisTaskStepService;

    /***
     * 查询ViewObject的分页数据
     * <p>
     * url请求参数示例: /list?field=abc&pageIndex=1&orderBy=abc:DESC
     * </p>
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取列表分页数据")
    @BindPermission(name = "查看列表", code = Operation.LIST)
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(BisTaskStepDTO queryDto, Pagination pagination) throws Exception {
        return super.getViewObjectList(queryDto, pagination, BisTaskStepListVO.class);
    }

    /***
     * 根据资源id查询ViewObject
     * @param id ID
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据ID获取详情数据")
    @BindPermission(name = "查看详情", code = Operation.DETAIL)
    @GetMapping("/{id}")
    public JsonResult getViewObjectMapping(@PathVariable("id") Long id) throws Exception {
        return super.getViewObject(id, BisTaskStepDetailVO.class);
    }

    /***
     * 创建资源对象
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @ApiOperation(value = "新建数据")
    @BindPermission(name = "新建", code = Operation.CREATE)
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody BisTaskStep entity) throws Exception {
        return super.createEntity(entity);
    }

    /***
     * 根据ID更新资源对象
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @ApiOperation(value = "根据ID更新数据")
    @BindPermission(name = "更新", code = Operation.UPDATE)
    @PutMapping("/{id}")
    public JsonResult updateEntityMapping(@PathVariable("id") Long id, @Valid @RequestBody BisTaskStep entity) throws Exception {
        return super.updateEntity(id, entity);
    }

    /***
     * 根据id删除资源对象
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据ID删除数据")
    @BindPermission(name = "删除", code = Operation.DELETE)
    @DeleteMapping("/{id}")
    public JsonResult deleteEntityMapping(@PathVariable("id") Long id) throws Exception {
        return super.deleteEntity(id);
    }

} 