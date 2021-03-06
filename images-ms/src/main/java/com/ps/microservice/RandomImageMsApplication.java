package com.ps.microservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableEurekaClient
@RefreshScope
@SpringBootApplication
public class RandomImageMsApplication {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
//	@Value("${images-ms-message}")
//	private String message;
	
	@Autowired
	private ImageService imageService;
	
	@RequestMapping(value="/images", params={"random=true", "fields=url"})
    public Map<String, String> getRandomImageUrl(HttpServletRequest request) {
		logger.info("[{}] getRandomImageUrl()");
		String scheme = request.getScheme();
		String server = request.getServerName();
		int port = request.getServerPort();
		Map<String, String> image = new HashMap<String, String>();
		image.put("imageUrl", scheme+"://"+server+":"+port+"/images/"+imageService.getRandomImage());
        return image;
    }
	
	@RequestMapping("/images/{image:.+}")
	public void getRandomImage(@PathVariable String image, HttpServletResponse response) throws Exception {
		InputStream is = new FileInputStream(new File(getClass().getResource("/images/"+image).toURI()));
		ServletOutputStream os = response.getOutputStream();
		IOUtils.copy(is, os);
		response.setContentType("image/jpg");
    }
	
    public static void main(String[] args) {
    	SpringApplication.run(RandomImageMsApplication.class, args);
    }
}
