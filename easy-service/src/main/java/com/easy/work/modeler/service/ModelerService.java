package com.easy.work.modeler.service;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Created by apple on 18/3/28.
 */
public interface ModelerService {

    /**
     * 查找编辑的节点
     * @param modelId
     * @return
     */
    public ObjectNode searchEditorJson(String modelId) throws Exception;
}
