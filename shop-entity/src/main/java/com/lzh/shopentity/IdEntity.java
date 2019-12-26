package com.lzh.shopentity;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;


/**
 * 应用程序创建的实体对象Version 从10开始，version 小于10的是系统初始化的数据，
 * 这些数据不能从应用程序删除。
 *
 * @author haocheng
 */
public abstract class IdEntity implements Serializable {
    private static final long serialVersionUID = 8094833045795401482L;
    protected Long id;
    protected Integer version = 10;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getSearchString() {
        StringBuilder searchString = new StringBuilder();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if (fieldName.equals("pageSize") || fieldName.equals("pageNo")) {
                continue;
            }

            Class<?> clazz = field.getType();
            field.setAccessible(true);
            try {
                Object valueObject = field.get(this);
                if (valueObject != null) {
                    if (clazz == String.class) {
                        String value = (String) valueObject;
                        if (value.length() > 0) {
                            searchString.append("&").append(field.getName()).append("=").append(value);
                        }
                    } else if (clazz.isArray()) {
                        int arrayLength = Array.getLength(valueObject);
                        for (int i = 0; i < arrayLength; i++) {
                            Object value = Array.get(valueObject, i);
                            searchString.append("&").append(field.getName()).append("=").append(value);
                        }
                    } else if (clazz.isAssignableFrom(List.class)) {
                        List<?> values = (List<?>) valueObject;
                        for (Object value : values) {
                            searchString.append("&").append(field.getName()).append("=").append(value);
                        }
                    } else {
                        searchString.append("&").append(field.getName()).append("=").append(field.get(this));
                    }
                }
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }

        }

        return searchString.toString();
    }
}
