package com.buza.server.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public class PropertiesUtils {

    private static Properties properties;

    private static String PROGRAM_NAME;
    private static String FILE_PATH;
    private static String ALIYUN_OSS_FILE_BUCKET_NAME;
    private static String ALIYUN_OSS_FILE_ENDPOINT;
    private static String ALIYUN_OSS_FILE_BUCKET_ENDPOINT;
    private static String ALIYUN_OSS_FILE_ACCESS_KEY_ID;
    private static String ALIYUN_OSS_FILE_ACCESS_KEY_SECRET;

    static {
        properties = new Properties();
        InputStream inStream = PropertiesUtils.class.getClassLoader().getResourceAsStream("config.properties");
        try {
            Reader reader = new InputStreamReader(inStream, "UTF-8");
            properties.load(reader);
            PROGRAM_NAME = properties.getProperty("PROGRAM_NAME");
            FILE_PATH = properties.getProperty("FILE_PATH");
            ALIYUN_OSS_FILE_BUCKET_NAME = properties.getProperty("ALIYUN_OSS_FILE_BUCKET_NAME");
            ALIYUN_OSS_FILE_ENDPOINT = properties.getProperty("ALIYUN_OSS_FILE_ENDPOINT");
            ALIYUN_OSS_FILE_BUCKET_ENDPOINT = properties.getProperty("ALIYUN_OSS_FILE_BUCKET_ENDPOINT");
            ALIYUN_OSS_FILE_ACCESS_KEY_ID = properties.getProperty("ALIYUN_OSS_FILE_ACCESS_KEY_ID");
            ALIYUN_OSS_FILE_ACCESS_KEY_SECRET = properties.getProperty("ALIYUN_OSS_FILE_ACCESS_KEY_SECRET");

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try { inStream.close(); } catch (Exception e1) {}
        }
    }

    public static String getProgramName() {
        return PROGRAM_NAME;
    }

    public static void setProgramName(String programName) {
        PROGRAM_NAME = programName;
    }

    public static String getFilePath() {
        return FILE_PATH;
    }

    public static void setFilePath(String filePath) {
        FILE_PATH = filePath;
    }

    public static String getAliyunOssFileBucketName() {
        return ALIYUN_OSS_FILE_BUCKET_NAME;
    }

    public static void setAliyunOssFileBucketName(String aliyunOssFileBucketName) {
        ALIYUN_OSS_FILE_BUCKET_NAME = aliyunOssFileBucketName;
    }

    public static String getAliyunOssFileEndpoint() {
        return ALIYUN_OSS_FILE_ENDPOINT;
    }

    public static void setAliyunOssFileEndpoint(String aliyunOssFileEndpoint) {
        ALIYUN_OSS_FILE_ENDPOINT = aliyunOssFileEndpoint;
    }

    public static String getAliyunOssFileBucketEndpoint() {
        return ALIYUN_OSS_FILE_BUCKET_ENDPOINT;
    }

    public static void setAliyunOssFileBucketEndpoint(String aliyunOssFileBucketEndpoint) {
        ALIYUN_OSS_FILE_BUCKET_ENDPOINT = aliyunOssFileBucketEndpoint;
    }

    public static String getAliyunOssFileAccessKeyId() {
        return ALIYUN_OSS_FILE_ACCESS_KEY_ID;
    }

    public static void setAliyunOssFileAccessKeyId(String aliyunOssFileAccessKeyId) {
        ALIYUN_OSS_FILE_ACCESS_KEY_ID = aliyunOssFileAccessKeyId;
    }

    public static String getAliyunOssFileAccessKeySecret() {
        return ALIYUN_OSS_FILE_ACCESS_KEY_SECRET;
    }

    public static void setAliyunOssFileAccessKeySecret(String aliyunOssFileAccessKeySecret) {
        ALIYUN_OSS_FILE_ACCESS_KEY_SECRET = aliyunOssFileAccessKeySecret;
    }
}
