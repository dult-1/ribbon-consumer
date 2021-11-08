package dult.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.Future;

/**
 * 测试Hystrix请求合并器
 * Created by dult on 2021-11-5.
 */
@Service
public class AllConsumerService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCollapser(batchMethod = "findAll",scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,
            collapserProperties = {@HystrixProperty(name="timerDelayInMilliseconds",value = "3000")})
    public Future<String> find(String id){
        System.out.println("====进入find方法=====");
        return null;
    }

    @HystrixCommand
    public List<String> findAll(List<String> ids){
        System.out.println("====进入findAll方法====="+System.currentTimeMillis()+"=="+ids);
        return restTemplate.getForObject("http://HELLO-SERVICE/findAll?ids={1}",List.class, StringUtils.join(ids,","));
    }
}
