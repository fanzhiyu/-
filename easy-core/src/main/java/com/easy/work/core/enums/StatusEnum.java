package com.easy.work.core.enums;


import com.easy.core.util.StringUtils;

/**
 * Created by apple on 18/1/10.
 */
public enum StatusEnum {

    STATUS_ENABLE("0", "启用"),

    STATUS_DISABLE("1", "禁用");

    private String code;

    private String name;

    StatusEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    StatusEnum(){

    }

    /**
     * 根据枚举code获取名
     * @param code
     * @return
     */
    public static String getNameByCode(String code){
        if(StringUtils.isNull(code)){return null;}
        String name = null;
        for(StatusEnum statusEnum : StatusEnum.values()){
            if(StringUtils.isEquals(statusEnum.getCode(),code)){
                name = statusEnum.getName();
            }
        }
        return name;
    }

    /**
     * 获取code
     * @param statusEnum
     * @return
     */
    public static String getCode(StatusEnum statusEnum){
        return statusEnum.getCode();
    }

    /**
     * 获取名
     * @param statusEnum
     * @return
     */
    public static String getName(StatusEnum statusEnum){
        return statusEnum.getName();
    }

    /**
     * 根据枚举名获取枚举code
     * @param name
     * @return
     */
    public static String getCodeByName(String name){
        if(StringUtils.isNull(name)){return null;}
        String code = null;
        for(StatusEnum statusEnum : StatusEnum.values()){
            if(StringUtils.isEquals(statusEnum.getName(), name)){
                code = statusEnum.getCode();
            }
        }
        return code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
