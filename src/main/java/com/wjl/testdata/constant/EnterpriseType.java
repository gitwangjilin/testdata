package com.wjl.testdata.constant;

import java.util.HashMap;
import java.util.Map;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: EnterpriseType
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
public abstract class EnterpriseType {

    public static Map<String, String> businessTypeAndOutType = new HashMap();

    public static String SAIC_TYPE_NZGS = "1";
    public static String SAIC_TYPE_WZGS = "2";
    public static String SAIC_TYPE_NZFGSQY = "3";
    public static String SAIC_TYPE_HHQY = "4";
    public static String SAIC_TYPE_WSTZHHQY = "5";
    public static String SAIC_TYPE_NMZYHZSFR = "6";
    public static String SAIC_TYPE_GRDZQY = "7";
    public static String SAIC_TYPE_GTGSH = "8";
    public static String SAIC_TYPE_FGS = "9";
    public static String SAIC_TYPE_WZFGS = "10";
    public static String SAIC_TYPE_NZFGSQYFZJGJFFRQY = "11";
    public static String SAIC_TYPE_HHQYFZJG = "12";
    public static String SAIC_TYPE_WSTZHHQYFZJG = "13";
    public static String SAIC_TYPE_GRDZQYFZJG = "14";
    public static String SAIC_TYPE_NMZYHZSFZJG = "15";
    public static String SAIC_TYPE_NMHZLS = "16";
    public static String SAIC_TYPE_NMHZLSFJG = "17";
    public static String SAIC_TYPE_NMZYHZL = "18";
    public static String SAIC_TYPE_WGQYFZJG = "19";
    public static String SAIC_TYPE_WGQYJYHD = "20";
    public static String SAIC_TYPE_FGSWZFGS = "21";
    public static String SAIC_TYPE_WGQYCZDBJGDJZ = "31";
    // 普华那边过来的值，和我们这边数据对应的不一样。做兼容
    public static String SAIC_TYPE_WGQYCZDBJGDJZ_PH = "22";
    public static String SAIC_TYPE_QYJTDJZ = "23";

    static {
        businessTypeAndOutType.put(SAIC_TYPE_NZGS, SAIC_TYPE_NZGS);
        businessTypeAndOutType.put(SAIC_TYPE_WZGS, SAIC_TYPE_WZGS);
        businessTypeAndOutType.put(SAIC_TYPE_NZFGSQY, SAIC_TYPE_NZFGSQY);
        businessTypeAndOutType.put(SAIC_TYPE_HHQY, SAIC_TYPE_HHQY);
        businessTypeAndOutType.put(SAIC_TYPE_WSTZHHQY, SAIC_TYPE_WSTZHHQY);
        businessTypeAndOutType.put(SAIC_TYPE_NMZYHZSFR, SAIC_TYPE_NMZYHZSFR);
        businessTypeAndOutType.put(SAIC_TYPE_GRDZQY, SAIC_TYPE_GRDZQY);
        businessTypeAndOutType.put(SAIC_TYPE_GTGSH, SAIC_TYPE_GTGSH);
        businessTypeAndOutType.put(SAIC_TYPE_FGS, SAIC_TYPE_FGS);
        businessTypeAndOutType.put(SAIC_TYPE_WZFGS, SAIC_TYPE_WZFGS);
        businessTypeAndOutType.put(SAIC_TYPE_NZFGSQYFZJGJFFRQY, SAIC_TYPE_NZFGSQYFZJGJFFRQY);
        businessTypeAndOutType.put(SAIC_TYPE_HHQYFZJG, SAIC_TYPE_HHQYFZJG);
        businessTypeAndOutType.put(SAIC_TYPE_WSTZHHQYFZJG, SAIC_TYPE_WSTZHHQYFZJG);
        businessTypeAndOutType.put(SAIC_TYPE_GRDZQYFZJG, SAIC_TYPE_GRDZQYFZJG);
        businessTypeAndOutType.put(SAIC_TYPE_NMZYHZSFZJG, SAIC_TYPE_NMZYHZSFZJG);
        businessTypeAndOutType.put(SAIC_TYPE_NMHZLS, SAIC_TYPE_NMHZLS);
        businessTypeAndOutType.put(SAIC_TYPE_NMHZLSFJG, SAIC_TYPE_NMHZLSFJG);
        businessTypeAndOutType.put(SAIC_TYPE_NMZYHZL, SAIC_TYPE_NMZYHZL);
        businessTypeAndOutType.put(SAIC_TYPE_WGQYFZJG, SAIC_TYPE_WGQYFZJG);
        businessTypeAndOutType.put(SAIC_TYPE_WGQYJYHD, SAIC_TYPE_WGQYJYHD);
        businessTypeAndOutType.put(SAIC_TYPE_FGSWZFGS, SAIC_TYPE_FGSWZFGS);
        businessTypeAndOutType.put(SAIC_TYPE_WGQYCZDBJGDJZ, SAIC_TYPE_WGQYCZDBJGDJZ);
        businessTypeAndOutType.put(SAIC_TYPE_WGQYCZDBJGDJZ_PH, SAIC_TYPE_WGQYCZDBJGDJZ);
        businessTypeAndOutType.put(SAIC_TYPE_QYJTDJZ, SAIC_TYPE_QYJTDJZ);
        businessTypeAndOutType.put("内资公司", SAIC_TYPE_NZGS);
        businessTypeAndOutType.put("外资公司", SAIC_TYPE_WZGS);
        businessTypeAndOutType.put("内资非公司企业", SAIC_TYPE_NZFGSQY);
        businessTypeAndOutType.put("合伙企业", SAIC_TYPE_HHQY);
        businessTypeAndOutType.put("外商投资合伙企业", SAIC_TYPE_WSTZHHQY);
        businessTypeAndOutType.put("农民专业合作社法人", SAIC_TYPE_NMZYHZSFR);
        businessTypeAndOutType.put("个人独资企业", SAIC_TYPE_GRDZQY);
        businessTypeAndOutType.put("个体工商户", SAIC_TYPE_GTGSH);
        businessTypeAndOutType.put("分公司", SAIC_TYPE_FGS);
        businessTypeAndOutType.put("外资分公司", SAIC_TYPE_WZFGS);
        businessTypeAndOutType.put("内资非公司企业分支机构及非法人企业", SAIC_TYPE_NZFGSQYFZJGJFFRQY);
        businessTypeAndOutType.put("合伙企业分支机构", SAIC_TYPE_HHQYFZJG);
        businessTypeAndOutType.put("外商投资合伙企业分支机构", SAIC_TYPE_WSTZHHQYFZJG);
        businessTypeAndOutType.put("个人独资企业分支机构", SAIC_TYPE_GRDZQYFZJG);
        businessTypeAndOutType.put("农民专业合作社分支机构", SAIC_TYPE_NMZYHZSFZJG);
        businessTypeAndOutType.put("农民合作联社", SAIC_TYPE_NMHZLS);
        businessTypeAndOutType.put("农民合作联社分支机构", SAIC_TYPE_NMHZLSFJG);
        businessTypeAndOutType.put("农民专业合作联社", SAIC_TYPE_NMZYHZL);
        businessTypeAndOutType.put("外国企业分支机构", SAIC_TYPE_WGQYFZJG);
        businessTypeAndOutType.put("外国企业经营活动", SAIC_TYPE_WGQYJYHD);
        businessTypeAndOutType.put("外资非公司", SAIC_TYPE_FGSWZFGS);
        businessTypeAndOutType.put("外国（地区）企业常驻代表机构登记证", SAIC_TYPE_WGQYCZDBJGDJZ);
        businessTypeAndOutType.put("企业集团登记证", SAIC_TYPE_QYJTDJZ);
    }

    public static String getEnterpriseType(String s){
        return businessTypeAndOutType.get(s);
    }

}