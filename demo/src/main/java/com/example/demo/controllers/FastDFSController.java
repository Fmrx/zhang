package com.example.demo.controllers;

import com.example.demo.VO.ResultVO;
import com.example.demo.common.fastDFS.FastDFSClientWrapper;
import com.example.demo.utils.ResultUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/file")
public class FastDFSController {

    @Resource
    private FastDFSClientWrapper fastDFSClientWrapper;

    /**
     * 文件上传
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = {RequestMethod.POST})
    public ResultVO upload(MultipartFile file) throws IOException {
        String fileUrl = fastDFSClientWrapper.uploadFile(file);
        return ResultUtil.success(fileUrl);
    }

}
