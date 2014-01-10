/**
 * 
 *  ViewInject.java
 *  com.lohas.android.utils
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
public @interface ViewInject {
    
    /**
     * Id.
     *
     * @return the int
     */
    public int id();

    /**
     * Click.
     *
     * @return the string
     */
    public String click() default "";

    /**
     * Long click.
     *
     * @return the string
     */
    public String longClick() default "";

    /**
     * Item click.
     *
     * @return the string
     */
    public String itemClick() default "";

    /**
     * Item long click.
     *
     * @return the string
     */
    public String itemLongClick() default "";

    /**
     * Select.
     *
     * @return the select
     */
    public Select select() default @Select(selected = "");
}
