package com.example.springbootservice.client;

/**
 * @ClassName ServiceFallback
 * @Description
 * @Author ojt
 * @Date 2020/3/31  16:38
 */

import com.example.springbootservice.common.RestfulResult;
import com.example.springbootservice.entity.ServiceInfo;
import com.example.springbootservice.utils.JsonResult;
import org.springframework.stereotype.Component;


@Component
public class ServiceFallback implements ServiceFeignClient {

    @Override
    public JsonResult hello(ServiceInfo serviceInfo) {
        JsonResult result = new JsonResult();
        result.setData("服务调用失败");
        return result;
    }
}
