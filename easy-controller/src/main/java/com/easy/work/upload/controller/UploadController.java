package com.easy.work.upload.controller;

import com.easy.core.model.UploadServerModel;
import com.easy.core.util.FileUtils;
import com.easy.core.util.ResponseUtil;
import com.easy.work.core.bean.QiniuBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 上传附件
 * @author Luopan
 * @time 2017/12/22.
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private QiniuBean qiniuBean;

    /**
     * 附件上传
     * @param fileName
     * @param fileType
     * @return
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseEntity uploadFile(String fileName, String fileType){
        UploadServerModel uploadServerModel = new UploadServerModel();
        uploadServerModel.setAccessKey(qiniuBean.getAccessKey());
        uploadServerModel.setSecretKey(qiniuBean.getSecretKey());
        uploadServerModel.setBucket(qiniuBean.getBucket());
        String file = FileUtils.uploadServers(fileName, fileType, uploadServerModel);
//        FileModel model = new FileModel();
//        model.setFilePath(file);
//        model.setFileRoot(qiniuBean.getUrl());
        return ResponseUtil.success();
    }
}
