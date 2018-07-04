package com.easy.work.menu.model;

/**
 * Created by apple on 18/7/2.
 */
public class State {

    private boolean opened = true;

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
