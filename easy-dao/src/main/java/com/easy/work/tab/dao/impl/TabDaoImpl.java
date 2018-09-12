package com.easy.work.tab.dao.impl;

import com.easy.core.annotation.Fields;
import com.easy.core.repository.service.impl.BaseRepositoryPagingServiceImpl;
import com.easy.core.util.StringUtils;
import com.easy.work.tab.dao.TabDao;
import com.easy.work.tab.dao.TabFieldDao;
import com.easy.work.tab.domain.TabDomain;
import com.easy.work.tab.domain.TabFieldDomain;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by apple on 18/5/17.
 */
@Repository("tabDao")
public class TabDaoImpl extends BaseRepositoryPagingServiceImpl<TabDomain> implements TabDao {

    @Autowired
    private TabFieldDao tabFieldDao;

    /**
     * 创建表
     * @param tabDomain
     */
    @Override
    public void createTable(TabDomain tabDomain) {
        this.getSqlSession().update(tabDomain.getSqlId()+"createTable",tabDomain);
    }

    /**
     * 删除表
     * @param tabDomain
     */
    @Override
    public void dropTable(TabDomain tabDomain) {
        this.getSqlSession().update(tabDomain.getSqlId()+"dropTable",tabDomain);
    }

    /**
     * 查找表
     * @param tabDomain
     * @return
     */
    @Override
    public Integer findTableCount(TabDomain tabDomain) {
        Connection conn = null;
        try {
            conn = this.getSqlSession().getConnection();
            DatabaseMetaData metaData = conn.getMetaData();
            String url = metaData.getURL();
            String database = StringUtils.substring(url,url.lastIndexOf("/")+1,url.indexOf("?"));
            tabDomain.setDatabase(database);
            return this.getSqlSession().selectOne(tabDomain.getSqlId()+"findTableCount",tabDomain);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 查找表所对应的数据
     * @param tabDomain
     * @return
     */
    @Override
    public Map<String, Object> findTabDataPaging(TabDomain tabDomain) {
        tabDomain.setParamList(formatParams(tabDomain.getParamList()));
        Integer count = this.getSqlSession().selectOne(tabDomain.getSqlId()+"findTabDataCount",tabDomain);
        tabDomain.setCount(count);
        this.calculatedPaging(tabDomain);
        List<Object> resultList = this.getSqlSession().selectList(tabDomain.getSqlId()+"findTabDataPaging",tabDomain);
        List<TabFieldDomain> fieldList = tabDomain.getFieldList();
        List<String> titles = new ArrayList<String>();
        List<String> titleFields = new ArrayList<String>();
        for(TabFieldDomain fieldDomain:fieldList){
            titles.add(fieldDomain.getTabFieldComment());
            titleFields.add(fieldDomain.getTabFieldName());
        }
        List<Map<String,Object>> list = createBean(resultList, titleFields);
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("count", count);
        maps.put("resultList", list);
        maps.put("titles", titles);
        return maps;
    }

    /**
     * 查找表所对应的数据
     * @param tabDomain
     * @return
     */
    @Override
    public Map<String, Object> findTabData(TabDomain tabDomain) {
        tabDomain.setParamList(formatParams(tabDomain.getParamList()));
        List<Object> resultList = this.getSqlSession().selectList(tabDomain.getSqlId()+"findTabData",tabDomain);
        TabFieldDomain tabFieldDomain = new TabFieldDomain();
        tabFieldDomain.setTabId(tabDomain.getTabId());
        List<TabFieldDomain> fieldList = tabFieldDao.findListCondition(tabFieldDomain);
        List<String> titles = new ArrayList<String>();
        List<String> titleFields = new ArrayList<String>();
        for(TabFieldDomain fieldDomain:fieldList){
            titles.add(fieldDomain.getTabFieldComment());
            titleFields.add(fieldDomain.getTabFieldName());
        }
        Map<String, Object> maps = new HashMap<String, Object>();
        if(StringUtils.isNotNull(resultList) && resultList.size() > 1){
            List<Map<String,Object>> list = createBean(resultList, titleFields);
            maps.put("data", list);
        } else if(StringUtils.isNotNull(resultList) && resultList.size() == 1){
            Map<String,Object> object = createBean(resultList.get(0),titleFields);
            maps.put("data", object);
        }
        return maps;
    }

    /**
     * 添加对应表的数据
     * @param tabDomain
     */
    @Override
    public void insertData(TabDomain tabDomain) {
        this.getSqlSession().insert(tabDomain.getSqlId()+"insertData",tabDomain);
    }

    /**
     * 修改对应的表数据
     * @param tabDomain
     */
    @Override
    public void updateData(TabDomain tabDomain) {
        this.getSqlSession().update(tabDomain.getSqlId()+"updateData",tabDomain);
    }

    /**
     * 删除对应表的数据
     * @param tabDomain
     */
    @Override
    public void deleteData(TabDomain tabDomain) {
        this.getSqlSession().delete(tabDomain.getSqlId()+"deleteData",tabDomain);
    }

    /**
     * 创建虚拟javabean
     * @param list
     * @return
     */
    protected List<Map<String,Object>> createBean(List<Object> list, List<String> titleFields){
        List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = null;
        for(Object obj : list){
            map = createBean(obj,titleFields);
            resultList.add(map);
        }
        return resultList;
    }

    /**
     * 创建虚拟javabean
     * @param object
     * @return
     */
    protected Map<String,Object> createBean(Object object,List<String> titleFields){
        Map<String,Object> map = new LinkedHashMap<>();
        String str = object.toString();
        String json = StringUtils.substring(str, 1, str.length()-1);
        String[] beans = json.split(",");
        for(String field : titleFields){
            String[] fields = field.split("\\.");
            String tabName = fields[0];
            String fieldName = fields[1];
            field = field.replace(".","_").toLowerCase().trim();
            for(String bean : beans){
                String[] beanArray = bean.split("=");
                String name = beanArray[0].toLowerCase().trim();
                if(StringUtils.isEquals(field,name)){
//                    String[] characters =  name.split("_");
                    StringBuffer key = new StringBuffer();
                    TabDomain tabDomain = new TabDomain();
                    tabDomain.setTabName(tabName);
                    TabDomain resDomain = this.findCondition(tabDomain);
                    TabFieldDomain tabFieldDomain = new TabFieldDomain();
                    tabFieldDomain.setTabId(resDomain.getTabId());
                    tabFieldDomain.setTabFieldName(fieldName);
                    TabFieldDomain resFiledDomain = tabFieldDao.findCondition(tabFieldDomain);
                    key.append(resFiledDomain.getTabFieldId());
//                    if (characters.length > 1) {
//                        key.append(characters[0]);
//                        for (int i = 1; i < characters.length; i++) {
//                            String character = characters[i];
//                            key.append(StringUtils.substring(character, 0, 1).toUpperCase() + StringUtils.substring(character, 1));
//                        }
//                    }else{
//                        key.append(name);
//                    }
                    map.put(key.toString(), beanArray.length > 1 ? beanArray[1] : "");
                    break;
                }
            }
        }
        return map;
    }

    /**
     * 参数转换
     * @param params
     * @return
     */
    protected Map<String,Object> formatParams(Map<String,Object> params){
        Map<String,Object> maps = null;
        if(StringUtils.isNotNull(params)){
            maps = new HashMap<String,Object>();
            for(Map.Entry<String,Object> entry : params.entrySet()){
                String key = entry.getKey();
                char[] chars = key.toCharArray();
                for(int i = 0; i < chars.length; i++){
                    if(chars[i] >= 'A' && chars[i] <= 'Z'){
                        key = key.replaceAll(String.valueOf(chars[i]),String.valueOf("_"+chars[i]).toLowerCase());
                    }
                }
                maps.put(key,entry.getValue());
            }
        }
        return maps;
    }
}
