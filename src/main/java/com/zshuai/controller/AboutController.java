package com.zshuai.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by zshuai
 *
 * @Date :2020/3/19 1:48 PM
 * @Version 1.0
 **/

@Controller
//@Api(value = "跳转个人相关页面", description = "页面跳转")
public class AboutController {


    //@ApiOperation(httpMethod = "GET", value = "跳转个人信息页面")
    @GetMapping("/about")
    public String about() {
        return "about";

    }

    @GetMapping(value = "about/downloadWord")
    public ResponseEntity<byte[]> downloadWordByUrl(HttpServletRequest request) throws Exception {

        String filepath="/blog/java.pdf";//注意filepath的内容；
        File file=new File(filepath);
        String downloadFielName = "宗帅-Java.pdf";
        HttpHeaders headers = new HttpHeaders();
        downloadFielName = new String(downloadFielName.getBytes("UTF-8"), "iso-8859-1");// 将文件名进行转码，不然前端不识别
        headers.setContentDispositionFormData("attachment", downloadFielName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

}
