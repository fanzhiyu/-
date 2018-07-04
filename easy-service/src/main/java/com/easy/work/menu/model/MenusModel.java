package com.easy.work.menu.model;


import com.easy.core.annotation.Fields;

import java.util.List;

/**
 * Created by apple on 18/6/28.
 */
public class MenusModel {

    private String id;
    private String text;
    private String menuCode;
    private String menuIcon;
    private String menuUrl;
    private State state = new State(true);
    private List<MenusModel> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }


    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<MenusModel> getChildren() {
        return children;
    }

    public void setChildren(List<MenusModel> children) {
        this.children = children;
    }

}
