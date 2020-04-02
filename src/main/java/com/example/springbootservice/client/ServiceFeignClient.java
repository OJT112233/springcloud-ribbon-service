package com.example.springbootservice.client;


import com.example.springbootservice.common.RestfulResult;
import com.example.springbootservice.entity.ServiceInfo;
import com.example.springbootservice.utils.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "springbootService", fallback = ServiceFallback.class) //这里的value对应调用服务的spring.applicatoin.name
public interface ServiceFeignClient {


    @RequestMapping(value = "/service/hello", method = RequestMethod.GET)
    JsonResult hello(ServiceInfo serviceInfo);

}
