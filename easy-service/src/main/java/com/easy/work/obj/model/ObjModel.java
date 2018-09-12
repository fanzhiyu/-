package com.easy.work.obj.model;

import com.easy.core.model.Model;

import java.util.List;

/**
 * Created by apple on 18/5/30.
 */
public class ObjModel{

    private String objId;
    private String objName;
    private String objType;
    private String objStatus;
    private List<ObjTabModel> tabIds;
    private String paramList;
    private String searchFormId;
    private String addFormId;

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public List<ObjTabModel> getTabIds() {
        return tabIds;
    }

    public void setTabIds(List<ObjTabModel> tabIds) {
        this.tabIds = tabIds;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getObjStatus() {
        return objStatus;
    }

    public void setObjStatus(String objStatus) {
        this.objStatus = objStatus;
    }

    public String getParamList() {
        return paramList;
    }

    public void setParamList(String paramList) {
        this.paramList = paramList;
    }

    public String getSearchFormId() {
        return searchFormId;
    }

    public void setSearchFormId(String searchFormId) {
        this.searchFormId = searchFormId;
    }

    public String getAddFormId() {
        return addFormId;
    }

    public void setAddFormId(String addFormId) {
        this.addFormId = addFormId;
    }
}
