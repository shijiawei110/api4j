package com.sjw.base.apidoc.helper;

import com.sjw.base.apidoc.utils.StringPool;

/**
 * @author shijiawei
 * @version DocPrintHelper.java -> v 1.0
 * @date 2019/8/19
 */
public class DocPrintHelper {
    public static String getIndex(int classIndex, int methodIndex) {
        return classIndex + StringPool.DOT + methodIndex;
    }

}
