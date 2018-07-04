package com.easy.work.forms.model;

import com.easy.core.annotation.Fields;
import com.easy.core.model.PagingModel;

/**
 * Created by apple on 18/4/16.
 */
public class FormsPager extends PagingModel<FormsPager> {

    private String formId;
    private String formTitle;
    private String objName;
    private String formType;
    @Fields(name = "createTime", format = "yyyy-MM-dd")
    private String createDate;
    private String createUser;
    @Fields(name = "updateTime", format = "yyyy-MM-dd")
    private String updateDate;
    private String updateUser;


    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getFormTitle() {
        return formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
