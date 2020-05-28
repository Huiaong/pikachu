package com.huiaong.pikachu.resource.impl.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.google.common.base.Throwables;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.resource.impl.properties.PikaAlioosProperties;
import com.huiaong.pikachu.resources.dto.PikaGoodsPicture;
import com.huiaong.pikachu.resources.dto.PikaPictureFile;
import com.huiaong.pikachu.resources.service.PikaResourcesWriteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service(cluster = "failfast", timeout = 3000)
public class PikaResourcesWriteServiceImpl implements PikaResourcesWriteService {
    private final PikaAlioosProperties alioosProperties;


    @Override
    public Response<PikaGoodsPicture> upload(PikaPictureFile pictureFile) {

        //获取阿里云存储相关常量
        String endPoint = alioosProperties.getEndpoint();
        String accessKeyId = alioosProperties.getAccessKey();
        String accessKeySecret = alioosProperties.getAccessKeySecret();
        String bucketName = alioosProperties.getBucketName();

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);

            InputStream inputStream = new ByteArrayInputStream(pictureFile.getFileData());
            //给文件名加入随机值
            String uuid = UUID.randomUUID().toString();
            //当前日期当作目录
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String dataPath = dateTimeFormatter.format(LocalDateTime.now());
            String filename = dataPath + "/" + uuid + pictureFile.getFileSuffix();
            // 上传文件
            ossClient.putObject(bucketName, filename, inputStream);
            //获取url地址
            String uploadUrl = "http://" + bucketName + "." + endPoint + "/" + filename;
            // 关闭OSSClient
            ossClient.shutdown();

            PikaGoodsPicture pikaGoodsPicture = new PikaGoodsPicture();
            pikaGoodsPicture.setFileName(pictureFile.getFilePrefix());
            pikaGoodsPicture.setFileAddr(uploadUrl);
            return Response.ok(pikaGoodsPicture);
        } catch (Exception e) {
            log.error("failed to upload picture:{}[byte data:{}] to alioss cause by:{} ", pictureFile.getFilePrefix() + pictureFile.getFileSuffix(), pictureFile.getFileData(), Throwables.getStackTraceAsString(e));
            return Response.fail("picture.upload.fail");
        }
    }
}
