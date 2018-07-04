package com.easy.work.tab.model;

import com.easy.core.model.PagingModel;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Map;

/**
 * Created by apple on 18/5/24.
 */
public class TabDataPager extends PagingModel<Map<String,Object>> {

    private String tabId;

    private String params;

    private List<String> titles;

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }
}
