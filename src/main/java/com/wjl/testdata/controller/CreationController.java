package com.wjl.testdata.controller;

import com.wjl.testdata.entity.EnterpriseInfo;
import com.wjl.testdata.service.CreationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;





/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: Controller
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/1/8   WangJiLin     Create the current class
 *************************************************************************
 ******/
@Slf4j
@Api(tags = "创建测试企业")
@RestController
@RequestMapping("add")
public class CreationController {
    @Resource
    CreationService creationService;

    @ApiOperation(value = "注册企业（默认天津）")
    @GetMapping("data")
    public String addData() {
        EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
        enterpriseInfo.setAreaCode("100000");
        enterpriseInfo.setTemplateType("内资公司");
        enterpriseInfo.setSubjectL("天津");
        enterpriseInfo.setSubjectS("天津");
        enterpriseInfo.setOrgCode("ZZJGD1610351412345");
        enterpriseInfo.setRegCode("441610351454587");
        enterpriseInfo.setEntName("测试注册企业");
        enterpriseInfo.setName("王继林");
        enterpriseInfo.setRegCap("ZZ123");
        enterpriseInfo.setDom("ZZ123");
        enterpriseInfo.setMainMgrLocation("ZZ123");
        enterpriseInfo.setBizTerm("ZZ123");
        enterpriseInfo.setMgrScope("ZZ123");
        return creationService.addEnterprise(enterpriseInfo,"1");

    }
    @ApiOperation(value = "姓名身份证号手机号", notes = "姓名身份证号手机号")
    @GetMapping("/addNameIdcardPhone")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", required = true),
            @ApiImplicitParam(name = "entName", value = "企业名称", required = true),
            @ApiImplicitParam(name = "idcard", value = "身份证号", required = true),
            @ApiImplicitParam(name = "environment", value = "1：预生产，2：测试环境。", required = true)
    })
    public String addNameIdcardPhone(@RequestParam String name,String entName,String idcard, String environment){
        EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
        enterpriseInfo.setAreaCode("100000");
        enterpriseInfo.setTemplateType("内资公司");
        enterpriseInfo.setSubjectL("天津");
        enterpriseInfo.setSubjectS("天津");
        enterpriseInfo.setEntName(entName);
        enterpriseInfo.setName(name);
        enterpriseInfo.setRegCap("2000万元");
        enterpriseInfo.setIdCardHash(idcard);
        enterpriseInfo.setDom("天津测试地址");
        enterpriseInfo.setMainMgrLocation("ZZ123");
        enterpriseInfo.setBizTerm("ZZ123");
        enterpriseInfo.setMgrScope("ZZ123");
        return creationService.addEnterprise(enterpriseInfo,environment);
    }
    @PostMapping("data")
    public String addData(@RequestBody EnterpriseInfo enterpriseInfo) {
        return creationService.addEnterprise(enterpriseInfo,"1");
    }
}
