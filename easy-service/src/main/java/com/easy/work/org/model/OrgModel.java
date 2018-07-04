package com.easy.work.org.model;

import com.easy.core.annotation.Fields;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;

/**
 * Created by apple on 18/6/14.
 */
public class OrgModel {

    @Fields(name = "orgId")
    private String id;
    @Fields(name = "orgName")
    private String text;
    private State state = new State(true);
    private List<OrgModel> children;

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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<OrgModel> getChildren() {
        return children;
    }

    public void setChildren(List<OrgModel> children) {
        this.children = children;
    }


    public class State{

        private boolean opened;

        public boolean isOpened() {
            return opened;
        }

        public void setOpened(boolean opened) {
            this.opened = opened;
        }

        public State(Boolean opened){
            this.opened = opened;
        }
        public State(){}
    }
}
