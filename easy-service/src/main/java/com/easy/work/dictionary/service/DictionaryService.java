package com.easy.work.dictionary.service;

import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.work.dictionary.model.DictionaryModel;

import java.util.List;

/**
 * Created by apple on 18/6/27.
 */
public interface DictionaryService {

    /**
     * 查找数据字典
     * @param type
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public List<DictionaryModel> searchDictionaryList(String type) throws MessageException,TransformException;
}
