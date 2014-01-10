/**
 * 
 *  ViewException.java
 *  com.yiqiding.ktvbox.utils
 *
 *  Created by culm on 2013-11-22.
 *  Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 *
 */

package com.yiqiding.ktvbox.util;


public class ViewException extends CommonException {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The str msg. */
    private String strMsg = null;

    /**
     * Instantiates a new view exception.
     *
     * @param strExce the str exce
     */
    public ViewException(String strExce) {
        strMsg = strExce;
    }

    /**
     * Prints the stack trace.
     */
    public void printStackTrace() {
        if (strMsg != null)
            System.err.println(strMsg);

        super.printStackTrace();
    }
}
