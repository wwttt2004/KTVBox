/**
 * 
 *  Select.java
 *  com.yiqiding.ktvbox.utils
 *
 *  Created by culm on 2013-11-22.
 *  Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 *
 */
package com.yiqiding.ktvbox.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Select {

    /**
     * Selected.
     *
     * @return the string
     */
    public String selected();

    /**
     * No selected.
     *
     * @return the string
     */
    public String noSelected() default "";

}
