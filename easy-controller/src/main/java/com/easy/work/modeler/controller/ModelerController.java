package com.easy.work.modeler.controller;

import com.easy.work.modeler.service.ModelerService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by apple on 18/3/28.
 */
@RestController
@RequestMapping("/modeler")
public class ModelerController {

    @Resource
    private ModelerService modelerService;

    /**
     * 读取json文件
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/editor/stencilset",method = RequestMethod.GET)
    public String getStencilset() throws IOException {
        InputStream stencilsetStream = this.getClass().getClassLoader().getResourceAsStream("stencilset.json");
        return IOUtils.toString(stencilsetStream, "utf-8");
    }

    /**A
     * 获取编辑信息
     * @param modelId
     * @return
     */
    @RequestMapping(value = "/{modelId}/json", method = RequestMethod.GET)
    public ObjectNode getEditorJson(@PathVariable String modelId) throws Exception{
        ObjectNode objectNode = modelerService.searchEditorJson(modelId);
        return objectNode;
    }
}
