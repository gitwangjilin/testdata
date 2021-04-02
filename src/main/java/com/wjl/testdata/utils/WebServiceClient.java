package com.wjl.testdata.utils;


import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import javax.xml.rpc.ParameterMode;

public class WebServiceClient {
		
	public static String requestContext(String requestContent) {
		
		try{
	        Service service = new Service();
	        Call call = (Call) service.createCall();
//	        call.setTargetEndpointAddress(endpoint);// 远程调用路径
	        call.setOperationName("uniformRequestServer");// 调用的方法名
	        call.addParameter("arg0", // 参数名
	                    XMLType.XSD_STRING,// 参数类型:String
	                    ParameterMode.IN);// 参数模式：'IN' or 'OUT'
	        call.setReturnType(XMLType.XSD_STRING);// 返回值类型：String

	    		/**
	    		 * 1)	电子营业执照验证: BusinessLicenceCheck(String   licenceEntity, String   sysCode)
	    		 * 		licenceEntity：电子营业执照，数据格式为BASE64编码格式
	    		 *		sysCode：当前发起请求业务系统的系统代码ID，通过协商后，统一规划的业务系统代码，必填项
	    		 */

	    		 Object[] caReqObj = new Object[]{requestContent};
			System.out.println("调用----");
			Object resport = (String)call.invoke(caReqObj);// 远程调用
			System.out.println("结束---");
	    		return resport.toString();
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}
}
