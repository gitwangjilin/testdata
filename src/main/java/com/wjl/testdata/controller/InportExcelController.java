package com.wjl.testdata.controller;

import com.wjl.testdata.entity.QueryEnterpriseInfo;
import com.wjl.testdata.entity.UserDto;
import com.wjl.testdata.excel.ExcelUtil;
import com.wjl.testdata.mapper.QueryEnterpriseInfoMapper;
import com.wjl.testdata.sm2.GMUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: InportExcelController
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
@RestController
@Api(tags = "导入Excel文件")
@Slf4j
public class InportExcelController {
    @Autowired
    QueryEnterpriseInfoMapper queryEnterpriseInfoMapper;
    @Autowired
    private ExcelUtil excelUtil;
    @PostMapping(value = "/uploadExcel", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "用户信息Excel导入数据", notes = "用户信息Excel导入数据", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "上传成功！"),
            @ApiResponse(code = 500, message = "上传失败！")
    })
    public String uploadExcel(@ApiParam(value = "用户信息Excel导入数据", required = true)MultipartFile file) throws Exception {
        List<UserDto> dtoList = excelUtil.readExcelFileToDTO(file, UserDto.class);
        int i = 0;
        for (UserDto userDto : dtoList) {
//            System.out.println(userDto.getIdCard());
//            System.out.println(GMUtil.SM3Hash(userDto.getIdCard()));
            List<QueryEnterpriseInfo> idCardAndOrgCode = queryEnterpriseInfoMapper.findIdCardAndOrgCode(GMUtil.SM3Hash(userDto.getIdCard()));

                if(idCardAndOrgCode.size()==0){
                    i++;
                    System.out.println(userDto.getIdCard());
                }

        }
        log.info("总数："+i);
        log.info("长度："+dtoList.size());
        //TODO 入库的代码自行补充
        return "导入成功";
    }
}
