package com.wjl.testdata.controller;

import com.wjl.testdata.sm2.GMUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: Sm3Controller
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/1/21   WangJiLin     Create the current class
 *************************************************************************
 ******/
@Api(tags = "SM3")
@RestController
public class Sm3Controller {

    @GetMapping("/getSm3")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "val", value = "身份证号", required = true)
    })
    public String getSm3(@RequestParam String val) throws Exception {
        return GMUtil.SM3Hash(val);
    }
}
