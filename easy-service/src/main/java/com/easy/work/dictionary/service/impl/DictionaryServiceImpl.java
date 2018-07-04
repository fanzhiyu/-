package com.easy.work.dictionary.service.impl;

import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.core.manager.Manager;
import com.easy.core.util.StringUtils;
import com.easy.core.util.TransUtils;
import com.easy.work.core.constant.EasyMessage;
import com.easy.work.dictionary.dao.DictionaryDao;
import com.easy.work.dictionary.domain.DictionaryDomain;
import com.easy.work.dictionary.model.DictionaryModel;
import com.easy.work.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by apple on 18/6/27.
 */
@Service("dictionaryService")
@Transactional
public class DictionaryServiceImpl implements DictionaryService{

    @Autowired
    private DictionaryDao dictionaryDao;

    /**
     * 查找数据字典
     * @param type
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public List<DictionaryModel> searchDictionaryList(String type) throws MessageException, TransformException {
        checkType(type);
        DictionaryDomain dictionaryDomain = new DictionaryDomain();
        dictionaryDomain.setDictionaryType(type);
        List<DictionaryDomain> list = dictionaryDao.findListCondition(dictionaryDomain);
        return TransUtils.formatList(DictionaryModel.class,list);
    }

    private void checkType(String type) throws MessageException{
        if(StringUtils.isNull(type)){
            throw new MessageException(Manager.getMessage(EasyMessage.EASY1001,Manager.getMessage(EasyMessage.EASY1015)));
        }
    }
}
