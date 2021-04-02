package com.wjl.testdata.mapper;


import com.wjl.testdata.entity.QueryEnterpriseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lixian
 * @since 2019-12-17
 */
@Mapper
public interface QueryEnterpriseInfoMapper {


    //法人身份核验
    List<QueryEnterpriseInfo> findIdCardAndOrgCode(String idCard);

}
