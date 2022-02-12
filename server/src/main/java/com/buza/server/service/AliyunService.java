package com.buza.server.service;

import org.springframework.web.multipart.MultipartFile;

public interface AliyunService {

    public String uploadFileReturnURL(MultipartFile file) throws Exception;

}
