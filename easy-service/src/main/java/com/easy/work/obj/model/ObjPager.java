package com.easy.work.obj.model;

import com.easy.core.annotation.Fields;
import com.easy.core.model.PagingModel;

import java.util.List;

/**
 * Created by apple on 18/5/30.
 */
public class ObjPager extends PagingModel<ObjPager>{

    private String objName;
    private String objId;
    private String createUser;
    @Fields(name = "createTime", format = "yyyy-MM-dd")
    private String createDate;
    private String updateUser;
    @Fields(name = "updateTime", format = "yyyy-MM-dd")
    private String updateDate;
    private String objType;
    private String objStatus;

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
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
