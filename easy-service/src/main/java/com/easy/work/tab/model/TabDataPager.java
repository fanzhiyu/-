package com.easy.work.tab.model;

import com.easy.core.model.PagingModel;
import com.easy.work.obj.model.ObjModel;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Map;

/**
 * Created by apple on 18/5/24.
 */
public class TabDataPager extends PagingModel<Map<String,Object>> {

    private String tabId;

    private Map<String,Object> params;

    private List<String> fieldIdList;

    private List<String> titles;

    private ObjModel objModel;

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public List<String> getFieldIdList() {
        return fieldIdList;
    }

    public void setFieldIdList(List<String> fieldIdList) {
        this.fieldIdList = fieldIdList;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public ObjModel getObjModel() {
        return objModel;
    }

    public void setObjModel(ObjModel objModel) {
        this.objModel = objModel;
    }
}
