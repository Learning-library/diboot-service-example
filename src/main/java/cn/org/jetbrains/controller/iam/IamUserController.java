package cn.org.jetbrains.controller.iam;

import cn.org.jetbrains.dto.BaseUserInfoDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.util.V;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.KeyValue;
import com.diboot.core.vo.Pagination;
import com.diboot.iam.annotation.BindPermission;
import com.diboot.iam.annotation.Operation;
import com.diboot.iam.config.Cons;
import com.diboot.iam.dto.ChangePwdDTO;
import com.diboot.iam.dto.IamUserAccountDTO;
import com.diboot.iam.entity.IamAccount;
import com.diboot.iam.entity.IamRole;
import com.diboot.iam.entity.IamUser;
import com.diboot.iam.service.IamAccountService;
import com.diboot.iam.service.IamRoleService;
import com.diboot.iam.service.IamUserService;
import com.diboot.iam.util.IamSecurityUtils;
import com.diboot.iam.vo.IamUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 系统用户相关Controller
 *
 * @author MyName
 * @version 1.0
 * @date 2020-07-21
 * Copyright © MyCompany
 */
@RestController
@RequestMapping("/iam/user")
@Slf4j
@Api(tags = {"用户"})
@BindPermission(name = "用户")
public class IamUserController extends BaseCrudRestController<IamUser> {

    @Autowired
    private IamUserService iamUserService;

    @Autowired
    private IamRoleService iamRoleService;

    @Autowired
    private IamAccountService iamAccountService;

    /***
     * 查询ViewObject的分页数据
     * <p>
     * url请求参数示例: /list?field=abc&pageSize=20&pageIndex=1&orderBy=id
     * </p>
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取列表分页数据")
    @BindPermission(name = "查看列表", code = Operation.LIST)
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(IamUser entity, Pagination pagination) throws Exception {
        return super.getViewObjectList(entity, pagination, IamUserVO.class);
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
        return super.getViewObject(id, IamUserVO.class);
    }

    /***
     * 新建用户、账号和用户角色关联列表
     * @param iamUserAccountDTO
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "新建数据")
    @PostMapping("/")
    @BindPermission(name = "新建", code = Operation.CREATE)
    public JsonResult createEntityMapping(@Valid @RequestBody IamUserAccountDTO iamUserAccountDTO) throws Exception {
        iamUserService.createUserAndAccount(iamUserAccountDTO);
        return JsonResult.OK();
    }

    /***
     * 更新用户、账号和用户角色关联列表
     * @param iamUserAccountDTO
     * @return JsonResult
     * @throws Exception
     */
    @ApiOperation(value = "根据ID更新数据")
    @BindPermission(name = "更新", code = Operation.UPDATE)
    @PutMapping("/{id}")
    public JsonResult updateEntityMapping(@PathVariable("id") Long id, @Valid @RequestBody IamUserAccountDTO iamUserAccountDTO) throws Exception {
        iamUserService.updateUserAndAccount(iamUserAccountDTO);
        return JsonResult.OK();
    }

    /***
     * 删除用户、账号和用户角色关联列表
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据ID删除数据")
    @BindPermission(name = "删除", code = Operation.DELETE)
    @DeleteMapping("/{id}")
    public JsonResult deleteEntityMapping(@PathVariable("id") Long id) throws Exception {
        iamUserService.deleteUserAndAccount(id);
        return JsonResult.OK();
    }

    /**
     * 加载更多数据
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取更多关联数据")
    @GetMapping("/attachMore")
    public JsonResult attachMore(ModelMap modelMap) throws Exception {
        // 获取关联数据字典USER_STATUS的KV
        List<KeyValue> userStatusKvList = dictionaryService.getKeyValueList(Cons.DICTTYPE.USER_STATUS.name());
        modelMap.put("userStatusKvList", userStatusKvList);
        // 获取关联数据字典ORG_TYPE的KV
        List<KeyValue> genderKvList = dictionaryService.getKeyValueList(Cons.DICTTYPE.GENDER.name());
        modelMap.put("genderKvList", genderKvList);
        // 获取关联数据role的KV
        List<KeyValue> roleKvList = iamRoleService.getKeyValueList(
                Wrappers.<IamRole>lambdaQuery().select(IamRole::getName, IamRole::getId)
        );
        modelMap.put("roleKvList", roleKvList);
        return JsonResult.OK(modelMap);
    }

    /***
     * 获取用户名
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取用户名")
    @GetMapping("/getUsername/{id}")
    public JsonResult getUsername(@PathVariable("id") Long id) throws Exception {
        IamAccount account = iamAccountService.getSingleEntity(
                Wrappers.<IamAccount>lambdaQuery()
                        .eq(IamAccount::getUserType, IamUser.class.getSimpleName())
                        .eq(IamAccount::getUserId, id)
        );
        return JsonResult.OK(account != null ? account.getAuthAccount() : null).msg("获取用户名成功");
    }

    /**
     * 获取指定orgId下的用户列表
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取指定orgId下的用户列表")
    @GetMapping("/getUserList/{orgId}")
    public JsonResult getUserList(@PathVariable("orgId") Long orgId, IamUser iamUser, Pagination pagination) throws Exception {
        QueryWrapper<IamUser> wrapper = super.buildQueryWrapper(iamUser);
        if (orgId != null && !V.equals(orgId, 0L)) {
            wrapper.lambda().eq(IamUser::getOrgId, orgId);
        }
        List entityList = this.getService().getEntityList(wrapper, pagination);
        return JsonResult.OK(entityList).bindPagination(pagination);
    }

    /***
     * 获取当前用户信息
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("/getCurrentUserInfo")
    public JsonResult getCurrentUserInfo() throws Exception {
        IamUser iamUser = IamSecurityUtils.getCurrentUser();
        IamUser newIamUser = iamUserService.getEntity(iamUser.getId());
        return JsonResult.OK(newIamUser);
    }

    /**
     * 更新当前用户信息
     *
     * @param baseUserInfoDTO
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "更新当前用户信息")
    @PostMapping("/updateCurrentUserInfo")
    public JsonResult updateCurrentUserInfo(@Valid @RequestBody BaseUserInfoDTO baseUserInfoDTO) throws Exception {
        IamUser iamUser = IamSecurityUtils.getCurrentUser();
        IamUser newIamUser = iamUserService.getEntity(iamUser.getId());
        newIamUser.setRealname(baseUserInfoDTO.getRealname())
                .setGender(baseUserInfoDTO.getGender())
                .setMobilePhone(baseUserInfoDTO.getMobilePhone())
                .setEmail(baseUserInfoDTO.getEmail());
        boolean success = iamUserService.updateEntity(newIamUser);
        if (!success) {
            return JsonResult.FAIL_OPERATION("更新个人信息失败");
        }
        return JsonResult.OK(newIamUser).msg("更新成功");
    }

    /***
     * 更改密码
     * @param changePwdDTO
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "更改密码")
    @PostMapping("/changePwd")
    public JsonResult changePwd(@Valid @RequestBody ChangePwdDTO changePwdDTO) throws Exception {
        IamUser iamUser = IamSecurityUtils.getCurrentUser();
        IamAccount iamAccount = iamAccountService.getSingleEntity(
                Wrappers.<IamAccount>lambdaQuery()
                        .eq(IamAccount::getUserType, IamUser.class.getSimpleName())
                        .eq(IamAccount::getUserId, iamUser.getId())
        );
        boolean success = iamAccountService.changePwd(changePwdDTO, iamAccount);
        if (!success) {
            return JsonResult.FAIL_OPERATION("更改密码失败");
        }
        return JsonResult.OK().msg("更改密码成功");
    }

    /**
     * 校验用户名是否重复
     *
     * @param id
     * @param username
     * @return
     */
    @ApiOperation(value = "校验用户名是否重复")
    @GetMapping("/checkUsernameDuplicate")
    public JsonResult checkUsernameDuplicate(@RequestParam(required = false) Long id, @RequestParam String username) {
        if (V.notEmpty(username)) {
            LambdaQueryWrapper<IamAccount> wrapper = Wrappers.<IamAccount>lambdaQuery()
                    .select(IamAccount::getUserId)
                    .eq(IamAccount::getUserType, IamUser.class.getSimpleName()).eq(IamAccount::getAuthAccount, username);
            if (V.notEmpty(id)) {
                wrapper.ne(IamAccount::getUserId, id);
            }
            boolean alreadyExists = iamAccountService.exists(wrapper);
            if (alreadyExists) {
                return JsonResult.FAIL_OPERATION("用户名已存在");
            }
        }
        return JsonResult.OK();
    }

    /**
     * 校验用户编号是否重复
     *
     * @param id
     * @param userNum
     * @return
     */
    @ApiOperation(value = "校验用户编号是否重复")
    @GetMapping("/checkUserNumDuplicate")
    public JsonResult checkUserNumDuplicate(@RequestParam(required = false) Long id, @RequestParam String userNum) {
        if (V.notEmpty(userNum)) {
            LambdaQueryWrapper<IamUser> wrapper = Wrappers.<IamUser>lambdaQuery()
                    .select(IamUser::getUserNum)
                    .eq(IamUser::getUserNum, userNum);
            if (V.notEmpty(id)) {
                wrapper.ne(IamUser::getId, id);
            }
            boolean alreadyExists = iamUserService.exists(wrapper);
            if (alreadyExists) {
                return JsonResult.FAIL_OPERATION("用户编号已存在");
            }
        }
        return JsonResult.OK();
    }
}
