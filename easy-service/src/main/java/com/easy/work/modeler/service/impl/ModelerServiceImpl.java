package com.easy.work.modeler.service.impl;

import com.easy.core.exception.MessageException;
import com.easy.core.util.StringUtils;
import com.easy.work.modeler.service.ModelerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by apple on 18/3/28.
 */
@Repository("modelerService")
@Transactional
public class ModelerServiceImpl implements ModelerService {


    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 查找编辑信息
     * @param modelId
     * @return
     * @throws Exception
     */
    @Override
    public ObjectNode searchEditorJson(String modelId) throws Exception {
        checkModelId(modelId);
        ObjectNode objectNode = null;
        Model model = repositoryService.getModel(modelId);
        if(StringUtils.isNotNull(model)){
            if(StringUtils.isNotNull(model.getMetaInfo())){
                objectNode = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
            }else{
                objectNode = objectMapper.createObjectNode();
                objectNode.put(ModelDataJsonConstants.MODEL_NAME, model.getName());
            }
            objectNode.put(ModelDataJsonConstants.MODEL_ID, model.getId());
            ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(new String(repositoryService.getModelEditorSource(model.getId()),"UTF-8"));
            objectNode.put("model",editorJsonNode);
        }
        return objectNode;
    }

    /**
     * 验证modelId是否填写
     * @param modelId
     * @throws MessageException
     */
    private void checkModelId(String modelId) throws MessageException{
        if(StringUtils.isNull(modelId)){
            throw new MessageException("模型id不能为空");
        }
    }
}
