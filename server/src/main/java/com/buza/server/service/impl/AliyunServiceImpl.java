package com.buza.server.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.buza.server.service.AliyunService;
import com.buza.server.util.DateUtil;
import com.buza.server.util.PropertiesUtils;
import com.buza.server.util.ValueUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Calendar;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@Service
public class AliyunServiceImpl implements AliyunService {

    public String uploadFileReturnURL(MultipartFile file) throws Exception {
        String aliyunOssBucketName = PropertiesUtils.getAliyunOssFileBucketName();
        String aliyunOssEndPoint = PropertiesUtils.getAliyunOssFileEndpoint();
        String aliyunOssAccessKeyId = PropertiesUtils.getAliyunOssFileAccessKeyId();
        String aliyunOssAccessKeySecret = PropertiesUtils.getAliyunOssFileAccessKeySecret();

        OSS ossClient = new OSSClientBuilder().build(aliyunOssEndPoint, aliyunOssAccessKeyId, aliyunOssAccessKeySecret);
        InputStream inputStream = file.getInputStream();

        //기존파일명
        String fileName = file.getOriginalFilename();
        //파일확장자
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        //새로운 파일명  UUID.randomUUID().toString()
        String uploadFileName = DateUtil.getTime() + "_" + ValueUtil.generateUid(10) + "." + fileExtensionName;

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);

        String file_path_url = year + "/" + month + "/" + date + "/" + uploadFileName;
        ossClient.putObject(aliyunOssBucketName, file_path_url, inputStream);
        ossClient.shutdown();
        return "https://" + PropertiesUtils.getAliyunOssFileBucketEndpoint() + "/" + file_path_url;
    }

}