package com.easy.work.event.service;

import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;

/**
 * Created by apple on 18/6/2.
 */
public interface EventService {

    /**
     * 保存事件
     * @param params
     * @throws MessageException
     * @throws TransformException
     */
    public void saveEvent(String params) throws MessageException,TransformException;
}
