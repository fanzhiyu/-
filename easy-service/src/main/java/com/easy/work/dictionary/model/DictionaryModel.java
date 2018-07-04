package com.easy.work.dictionary.model;

import com.easy.core.annotation.Column;
import com.easy.core.annotation.Fields;

/**
 * Created by apple on 18/6/27.
 */
public class DictionaryModel {

    @Fields(name = "dictionaryCode")
    private String id;
    @Fields(name = "dictionaryName")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
