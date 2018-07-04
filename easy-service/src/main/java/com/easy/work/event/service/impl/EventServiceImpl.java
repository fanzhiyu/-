package com.easy.work.event.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.easy.core.constant.Constant;
import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.core.manager.Manager;
import com.easy.core.util.RandomNumberUtil;
import com.easy.core.util.StringUtils;
import com.easy.work.core.constant.EasyConstant;
import com.easy.work.core.constant.EasyMessage;
import com.easy.work.event.service.EventService;
import com.easy.work.obj.dao.ObjDao;
import com.easy.work.obj.dao.ObjTabDao;
import com.easy.work.obj.dao.ObjTabFieldDao;
import com.easy.work.obj.domain.ObjDomain;
import com.easy.work.obj.domain.ObjTabDomain;
import com.easy.work.obj.domain.ObjTabFieldDomain;
import com.easy.work.tab.dao.TabDao;
import com.easy.work.tab.domain.TabDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by apple on 18/6/2.
 */
@Service("eventService")
public class EventServiceImpl implements EventService {

    @Autowired
    private ObjTabDao objTabDao;

    @Autowired
    private TabDao tabDao;

    @Autowired
    private ObjTabFieldDao objTabFieldDao;

    /**
     * 保存事件
     * @param params
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public void saveEvent(String params) throws MessageException, TransformException {
        JSONObject json = JSONObject.parseObject(params);
        String objId = json.getString("objId");
        JSONArray list = JSONObject.parseArray(json.getString("list"));
        ObjTabDomain objTabDomain = new ObjTabDomain();
        objTabDomain.setObjId(objId);
        List<ObjTabDomain> resultList = objTabDao.findListCondition(objTabDomain);
        Map<String,Object> maps = null;
        TabDomain tabDomain = null;
        if(StringUtils.isNotNull(resultList) && resultList.size() > 0){
            for(ObjTabDomain domain : resultList){
                tabDomain = new TabDomain();
                maps = new LinkedHashMap<>();
                for(int i =0;i<list.size();i++){
                    JSONObject object = JSONObject.parseObject(list.get(i).toString());
                    Iterator<String> sIterator = object.keySet().iterator();
                    while (sIterator.hasNext()){
                        String key = sIterator.next();
                        ObjTabFieldDomain objTabFieldDomain = new ObjTabFieldDomain();
                        objTabFieldDomain.setObjTabFieldId(key);
                        objTabFieldDomain.setObjTabId(domain.getObjTabId());
                        ObjTabFieldDomain result = objTabFieldDao.findCondition(objTabFieldDomain);

                        if(StringUtils.isNotNull(result)){
                            if(StringUtils.isEquals(EasyConstant.IS_NOT_NULL,result.getFieldIsNull())
                                    && StringUtils.isNull(object.get(key))){
                                throw new MessageException(Manager.getMessage(EasyMessage.EASY1001,result.getFieldComment()));
                            }
                            Object val  =object.get(key);
                            if(StringUtils.isEquals(EasyConstant.IS_KEY,result.getFieldIsKey())){
                                val = RandomNumberUtil.getNewId();
                            }
                            maps.put(result.getFieldName(), val);
                        }
                    }
                }
                tabDomain.setTabName(domain.getTabName());
                tabDomain.setFieldMap(maps);
                tabDao.insertData(tabDomain);
            }
        }
    }
}
