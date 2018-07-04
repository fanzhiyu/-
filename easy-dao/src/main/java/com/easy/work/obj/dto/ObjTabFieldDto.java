package com.easy.work.obj.dto;

import com.easy.core.domain.Domain;

/**
 * Created by apple on 18/5/30.
 */
public class ObjTabFieldDto extends Domain {

    private static final String SQL_ID ="com.easy.work.obj.dao.ObjTabFieldDao.";

    private String objId;
    private String fieldComment;
    private String fieldName;
    private String fieldIsNull;
    private String fieldIsKey;

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getFieldComment() {
        return fieldComment;
    }

    public void setFieldComment(String fieldComment) {
        this.fieldComment = fieldComment;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldIsNull() {
        return fieldIsNull;
    }

    public void setFieldIsNull(String fieldIsNull) {
        this.fieldIsNull = fieldIsNull;
    }

    public String getFieldIsKey() {
        return fieldIsKey;
    }

    public void setFieldIsKey(String fieldIsKey) {
        this.fieldIsKey = fieldIsKey;
    }

    @Override
    public String getSqlId() {
        return SQL_ID;
    }
}
