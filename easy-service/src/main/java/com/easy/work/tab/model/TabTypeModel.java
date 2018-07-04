package com.easy.work.tab.model;


import com.easy.core.annotation.Fields;

/**
 * Created by apple on 18/5/17.
 */
public class TabTypeModel {

    @Fields(name="tabTypeId")
    private String code;

    @Fields(name="tabTypeName")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
