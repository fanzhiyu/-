package com.easy.work.obj.model;

import java.util.List;

/**
 * Created by apple on 18/5/30.
 */
public class ObjModel {

    private String objId;
    private String objName;
    private String objType;
    private String objStatus;
    private List<ObjTabModel> tabIds;

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
}
