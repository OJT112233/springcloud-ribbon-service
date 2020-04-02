/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.example.springbootservice.controller;


import com.example.springbootservice.client.ServiceFeignClient;
import com.example.springbootservice.common.RestfulResult;
import com.example.springbootservice.entity.ServiceInfo;
import com.example.springbootservice.utils.CommUtils;
import com.example.springbootservice.utils.JsonResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController // 重要，如果用Controller会404
@RequestMapping(value = "ribbonService")
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${server.port}")
    String port;


    // 调用：localhost:6007/consumerServiceRibbon?token=1
    @RequestMapping(value = "/consumerServiceRibbon", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "consumerServiceRibbonFallback")
    public JsonResult consumerServiceRibbon(ServiceInfo serviceInfo) {
        String result = this.restTemplate.postForObject("http://springbootService/service/rest", serviceInfo, String.class);
        return JsonResult.buildSuccessResult(result);
    }

    public JsonResult consumerServiceRibbonFallback(ServiceInfo serviceInfo) {
        String result = "consumerServiceRibbon异常，端口：" + port + "，Name=" + serviceInfo.getName();
        return JsonResult.buildExceptionResult(result);
    }


}