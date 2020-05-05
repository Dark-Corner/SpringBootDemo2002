package com.example.demo.modules.test.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modules.test.vo.ConfigBean;

@RestController
@RequestMapping("/api/Controller")
public class TestController {
	
	//引入log
	private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	
	
	
	//这是用全局配置文件 测试
	//通过value这个注解的方式将值传递过来
	@Value("${server.port}")
	private int port;
	@Value("${com.thron_bird.name}")
	private String name;
	@Value("${com.thron_bird.age}")
	private int age;
	@Value("${com.thron_bird.desc}")
	private String desc;
	@Value("${com.thron_bird.random}")
	private String random;
	
	//注入我们创建的非全局配置文件测试类
		@Autowired
		private ConfigBean configBean;
		
		@RequestMapping("/log")
		public String logTest(){
			LOGGER.trace("this is TRACE");
			LOGGER.debug("this is DEBUG");
			LOGGER.info("this is INFO");
			LOGGER.warn("this is WARN");
			LOGGER.error("this is ERROR log.");
			return "this is log test";
		}

		@RequestMapping("/download")
		public ResponseEntity<Resource> downLoadFile(@RequestParam  String fileName){
			try {
				
				Resource resource = new UrlResource(Paths.get("D:\\load\\"+fileName).toUri());
				
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
						.header(HttpHeaders.CONTENT_DISPOSITION
									, String.format("attachment;filename=\"%s\"", resource.getFilename()))
						.body(resource);
			} catch (Exception e) {
				e.printStackTrace();	
			}	
			return null;
		}	
		
		
		
		
		/**
		 * 将文件以BufferedInputStream的方式读取到byte[]里面，然后用OutputStream.write输出文件
		 */
		@RequestMapping("/download1")
		public void downloadFile1(HttpServletRequest request, 
				HttpServletResponse response, @RequestParam String fileName) {
			String filePath = "D:/upload" + File.separator + fileName;
			File downloadFile = new File(filePath);
			
			if (downloadFile.exists()) {
				response.setContentType("application/octet-stream");
				response.setContentLength((int)downloadFile.length());
				response.setHeader(HttpHeaders.CONTENT_DISPOSITION, 
						String.format("attachment; filename=\"%s\"", fileName));
				
				byte[] buffer = new byte[1024];
				FileInputStream fis = null;
				BufferedInputStream bis = null;
				try {
					fis = new FileInputStream(downloadFile);
					bis = new BufferedInputStream(fis);
					OutputStream os = response.getOutputStream();
					int i = bis.read(buffer);
					while (i != -1) {
						os.write(buffer, 0, i);
						i = bis.read(buffer);
					}
				} catch (Exception e) {
					LOGGER.debug(e.getMessage());
					e.printStackTrace();
				} finally {
					try {
						if (fis != null) {
							fis.close();
						}
						if (bis != null) {
							bis.close();
						}
					} catch (Exception e2) {
						LOGGER.debug(e2.getMessage());
						e2.printStackTrace();
					}
				}
			}
		}
		
		/**
		 * 以包装类 IOUtils 输出文件
		 */
		@RequestMapping("/download2")
		public void downloadFile2(HttpServletRequest request, 
				HttpServletResponse response, @RequestParam String fileName) {
			String filePath = "D:/upload" + File.separator + fileName;
			File downloadFile = new File(filePath);
			
			try {
				if (downloadFile.exists()) {
					response.setContentType("application/octet-stream");
					response.setContentLength((int)downloadFile.length());
					response.setHeader(HttpHeaders.CONTENT_DISPOSITION, 
							String.format("attachment; filename=\"%s\"", fileName));
					
					InputStream is = new FileInputStream(downloadFile);
					IOUtils.copy(is, response.getOutputStream());
					response.flushBuffer();
				}
			} catch (Exception e) {
				LOGGER.debug(e.getMessage());
				e.printStackTrace();
			}
		}
		
		
		
		
	@RequestMapping("/config")
	public String configTest(){
		StringBuffer sb = new StringBuffer();
		sb.append(port).append("------")
			.append(name).append("------")
			.append(age).append("------")
			.append(desc).append("------")
			.append(random).append("</br>");
			sb.append(configBean.getName()).append("------")
				.append(configBean.getAge()).append("------")
				.append(configBean.getDesc()).append("------")
				.append(configBean.getRandom());
		return sb.toString();
	}
	
	@RequestMapping("/appDoesc")
	//@ResponseBody的作用是讲该方法变成接口与controller一样
	@ResponseBody
	public String getDoesc(HttpServletRequest request ,@RequestParam String key ){
		String value = request.getParameter("key");
		return "Holle word, this is spring boot demo."+value+key;
	}
}
