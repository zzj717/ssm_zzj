package com.fh.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class OssloadUtil {
    //上传文件步骤  1,获取项目目录创建
	public static String uploadFile(HttpServletRequest request,MultipartFile picture) throws IOException {

		//获取图片路径的名称
		String Filenmae = picture.getOriginalFilename();
		//截取图片的后缀  以免有汉字 符号
		String last = Filenmae.substring(Filenmae.lastIndexOf("."),Filenmae.length());
		//创建 uuid 以uuid为图片的新名称
		String lastuuid ="zzj/book/"+UUID.randomUUID().toString()+last;

        InputStream is = picture.getInputStream();
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/init.html?spm=5176.docoss/sdk/java-sdk/get-start
		OSS ossClient = new OSSClientBuilder().build(Constant.endpoint, Constant.accessKeyId, Constant.accessKeySecret);
		//putobject (1,2,3)  1 bucket  2 文件名（目录/文件名） 3 输入流
		PutObjectResult putObjectResult = ossClient.putObject(Constant.bucketName,lastuuid,is);
		return "http://"+Constant.bucketName+".oss-cn-beijing.aliyuncs.com/"+lastuuid;
	}
}
