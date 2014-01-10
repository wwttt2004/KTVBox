/**
 * 
 *  CommonException.java
 *  com.lohas.android.utils
 *
 *  Created by culm on 2013-11-22.
 *  Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 *
 */

package com.yiqiding.ktvbox.util;

public class CommonException extends RuntimeException {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new common exception.
     */
    public CommonException() {
        super();
    }

    /**
     * Instantiates a new common exception.
     *
     * @param msg the msg
     */
    public CommonException(String msg) {
        super(msg);
    }

    /**
     * Instantiates a new common exception.
     *
     * @param ex the ex
     */
    public CommonException(Throwable ex) {
        super(ex);
    }

    /**
     * Instantiates a new common exception.
     *
     * @param msg the msg
     * @param ex the ex
     */
    public CommonException(String msg, Throwable ex) {
        super(msg, ex);
    }

}
