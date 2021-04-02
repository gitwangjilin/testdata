package com.wjl.testdata.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: UserDto
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
@Data
public class UserDto {
    @ApiModelProperty(value = "编号")
    private String idCard;
//
//    @ApiModelProperty(value = "用户名称")
//    private String name;
//
//    @ApiModelProperty(value = "用户年龄")
//    private Integer age;
}