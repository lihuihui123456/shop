package com.lzh.shopweb;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    public Persistence ColumnType() default Persistence.PERSISTENCE;
    public String ColumnName() default "";
}
