package com.wjl.testdata.sm2;

/**
 * <p>Title:CheckEmpty.java</p>
 * <p>
 * <p>Description: 判断空操作</p>
 * <p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>
 * <p>Company: ccit</p>
 *
 * @author huangjinyu
 * @version 1.0
 */
public class CheckEmpty {

    public static boolean isEmpty(String data) {
        if (data == null || "".equals(data.trim())) {
            return true;
        }
        return false;
    }
}
