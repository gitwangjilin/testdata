package com.wjl.testdata.service.impl;

import com.wjl.testdata.entity.EnterpriseInfo;
import com.wjl.testdata.entity.UserInfo;
import com.wjl.testdata.service.CreationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.Charset;

//import org.springframework.stereotype.Service;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: CreationServiceImpl
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
@Service
public class CreationServiceImpl implements CreationService {
    //
//    @Resource
//    RestTemplate restTemplate;
//    @Resource
//    CreationMapper creationMapper;

    @Override
    public String addEnterprise(EnterpriseInfo enterpriseInfo, String environment) {
//        UserInfo user = creationMapper.findAll();
        UserInfo user = null;
        //公司类型
        String type = StringUtils.isBlank(enterpriseInfo.getEntTemplateType()) ? "内资公司" : enterpriseInfo.getEntTemplateType();
        //省
        String subjectL = StringUtils.isBlank(enterpriseInfo.getSubjectL()) ? user.getSubjectL() : enterpriseInfo.getSubjectL();
        //市
        String subjectS = StringUtils.isBlank(enterpriseInfo.getSubjectS()) ? user.getSubjectS() : enterpriseInfo.getSubjectS();
        //地区编码
        String areaCode = StringUtils.isBlank(enterpriseInfo.getAreaCode()) ? user.getAreaCode() : enterpriseInfo.getAreaCode();
        //统一社会信用代码
        String orgCode = StringUtils.isBlank(enterpriseInfo.getOrgCode()) ? user.getOrgCode() : enterpriseInfo.getOrgCode();
        //注册号
        String regCode = StringUtils.isBlank(enterpriseInfo.getRegCode()) ? user.getRegCode() : enterpriseInfo.getRegCode();
        //企业名称
        String entName = StringUtils.isBlank(enterpriseInfo.getEntName()) ? user.getRegCode() : enterpriseInfo.getEntName();
        //法人名称
        String name = StringUtils.isBlank(enterpriseInfo.getName()) ? user.getRegCode() : enterpriseInfo.getName();
        //注册资金
        String regCap = StringUtils.isBlank(enterpriseInfo.getRegCap()) ? "2000万元" : enterpriseInfo.getRegCap();
        //住所
        String dom = StringUtils.isBlank(enterpriseInfo.getDom()) ? "北京市花园路" : enterpriseInfo.getDom();
        //住所
        String mainMgrLocation = StringUtils.isBlank(enterpriseInfo.getMainMgrLocation()) ? "经营场所" : enterpriseInfo.getMainMgrLocation();
        //开业时间
        String foundDate = StringUtils.isBlank(enterpriseInfo.getFoundDate()) ? "2033年03月30日" : enterpriseInfo.getFoundDate();
        //经营期限至
        String bizTerm = StringUtils.isBlank(enterpriseInfo.getBizTerm()) ? "2033年03月30日" : enterpriseInfo.getBizTerm();
        //经营期限至
        String mgrScope = StringUtils.isBlank(enterpriseInfo.getMgrScope()) ? "2038年08月30日" : enterpriseInfo.getMgrScope();
        //手机号
        String phone = StringUtils.isBlank(enterpriseInfo.getMgrScope()) ? "13116111111" : enterpriseInfo.getPhoneNumber();
        String infoXml = "<?xml version='1.0' encoding='utf-8'?><licence><firm>";
        infoXml = infoXml + "<type>" + type + "</type>";
        infoXml = infoXml + "<attribute500>" + subjectL + "</attribute500>";
        infoXml = infoXml + "<attribute501>" + subjectS + "</attribute501>";
        infoXml = infoXml + "<attribute504>" + areaCode + "</attribute504>";
        infoXml = infoXml + "<attribute13>" + orgCode + "</attribute13>";
        infoXml = infoXml + "<attribute17>" + regCode + "</attribute17>";
        infoXml = infoXml + "<attribute18>" + entName + "</attribute18>";
        infoXml = infoXml + "<attribute19>" + "电子营业执照" + "</attribute19>";
        infoXml = infoXml + "<attribute20>" + name + "</attribute20>";
        infoXml = infoXml + "<attribute21>" + name + "</attribute21>";
        infoXml = infoXml + "<attribute22>" + name + "</attribute22>";
        infoXml = infoXml + "<attribute23>" + name + "</attribute23>";
        infoXml = infoXml + "<attribute24>" + name + "</attribute24>";
        infoXml = infoXml + "<attribute25>" + regCap + "</attribute25>";
        infoXml = infoXml + "<attribute26>" + regCap + "</attribute26>";
        infoXml = infoXml + "<attribute27>" + regCap + "</attribute27>";
        infoXml = infoXml + "<attribute28>" + "组成形式" + "</attribute28>";
        infoXml = infoXml + "<attribute29>" + dom + "</attribute29>";
        infoXml = infoXml + "<attribute30>" + mainMgrLocation + "</attribute30>";
        infoXml = infoXml + "<attribute31>" + mainMgrLocation + "</attribute31>";
        infoXml = infoXml + "<attribute32>" + mainMgrLocation + "</attribute32>";
        infoXml = infoXml + "<attribute33>" + foundDate + "</attribute33>";
        infoXml = infoXml + "<attribute34>" + foundDate + "</attribute34>";
        infoXml = infoXml + "<attribute35>" + bizTerm + "</attribute35>";
        infoXml = infoXml + "<attribute36>" + bizTerm + "</attribute36>";
        infoXml = infoXml + "<attribute37>" + bizTerm + "</attribute37>";
        infoXml = infoXml + "<attribute38>" + bizTerm + "</attribute38>";
        infoXml = infoXml + "<attribute39>" + mgrScope + "</attribute39>";
        infoXml = infoXml + "<attribute40>" + mgrScope + "</attribute40>";
        infoXml = infoXml + "<attribute48>" + "编号" + "</attribute48>";
        infoXml = infoXml + "<attribute49>" + "外国企业名称" + "</attribute49>";
        infoXml = infoXml + "<attribute50>" + "外国企业住所" + "</attribute50>";
        infoXml = infoXml + "<attribute51>" + "" + "</attribute51>";
        infoXml = infoXml + "<attribute52>" + phone + "</attribute52>";
        infoXml = infoXml + "<attribute56>" + enterpriseInfo.getIdCardHash() + "</attribute56>";
        infoXml = infoXml + "<attribute41>" + "工商行政管理局" + "</attribute41>";
        infoXml = infoXml + "<attribute42>" + "工商行政管理局" + "</attribute42>";
        infoXml = infoXml + "<attribute43>" + "2020年9月17日" + "</attribute43>";
        infoXml = infoXml + "<attribute44>" + "http://guojiagongs/public/gsgs/service.gov" + "</attribute44>";
        infoXml = infoXml + "<attribute90>" + "A" + "</attribute90>";
        infoXml = infoXml + "</firm></licence>";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(sendUrl(environment));
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        ContentType contentType= ContentType.create("text/plain", Charset.forName("UTF-8")); //设置文本字符编码为UTF-8
        entityBuilder.addPart("infoXml",new StringBody(infoXml,contentType));
        httpPost.setEntity(entityBuilder.build());
        CloseableHttpResponse response = null;
        //读取响应流
        try {
            response = httpclient.execute(httpPost);
            String resulst = EntityUtils.toString(response.getEntity());
            System.out.println(resulst);
            String respe="身份证号："+enterpriseInfo.getIdCardHash() +"\n"+"法人："+ enterpriseInfo.getEntName()+"\n"+"手机号："+phone+"\n";
            return respe;
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    return null;
    }
    private static String sendUrl(String environment) {
        switch (environment) {
            case "2":
                return "http://172.18.151.31:9090/enterprise/registerOrChange";
            case "1":
                return "http://192.168.100.113:8083/enterprise/registerOrChange";
            case "0":
                return "http://192.168.100.113:8083/enterprise/registerOrChange";
            default:
                return "http://172.18.151.31:9090/enterprise/registerOrChange";
        }
    }
}
