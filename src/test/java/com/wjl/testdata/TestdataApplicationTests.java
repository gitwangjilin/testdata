package com.wjl.testdata;

import com.wjl.testdata.entity.EnterpriseInfo;
import com.wjl.testdata.entity.UserInfo;
import com.wjl.testdata.mapper.UserInfoMapper;
import com.wjl.testdata.service.CreationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestdataApplicationTests {
    @Autowired
    CreationService creationService;

    @Test
    void contextLoads() {
        EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
        enterpriseInfo.setAreaCode("120000");
        enterpriseInfo.setTemplateType("内资公司");
        enterpriseInfo.setSubjectL("天津");
        enterpriseInfo.setSubjectS("天津");
        enterpriseInfo.setOrgCode("91120225MA05XD4C0M");
        enterpriseInfo.setRegCode("120225000179886");
        enterpriseInfo.setEntName("天津制恒建材销售有限公司");
        enterpriseInfo.setName("戚盈凯");
        enterpriseInfo.setRegCap("2000万元");
        enterpriseInfo.setIdCardHash("330681199412032737");
        enterpriseInfo.setDom("天津市蓟州区穿芳峪镇西李各庄村南穿芳峪镇楼宇服务中心02室");
        enterpriseInfo.setMainMgrLocation("主要经营场所");
        enterpriseInfo.setBizTerm("经营场所");
        enterpriseInfo.setMgrScope("建筑材料(不含砂石料)、五金电料、保温材料、装饰装修材料(不含危险化学品)、钢材、管材批发兼零售;房屋建筑工程、园林绿化工程、土石方工程、外墙保温工程、管道工程(不含压力管道)施工;建筑机械设备、筑路机械设备、建筑工具、筑路工具租赁服务;建筑劳务分包(不含劳务派遣)。(依法须经批准的项目,经相关部门批准后方可开展经营活动)");
        creationService.addEnterprise(enterpriseInfo, "1");
    }
    @Autowired
    UserInfoMapper userInfoMapper;
    @Test
    void fingData() {
       UserInfo userInfo = userInfoMapper.findLimitOne();
        System.out.println(userInfo);
    }
}