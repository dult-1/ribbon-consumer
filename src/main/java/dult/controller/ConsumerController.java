package dult.controller;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import dult.service.AllConsumerService;
import dult.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dult on 2021-11-1.
 */
@RestController
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;
    @Autowired
    AllConsumerService allConsumerService;

    @RequestMapping(value = "/ribbon-consumer")
    public String helloConsumer(){
        HystrixRequestContext.initializeContext();
        String result = consumerService.helloService("12");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result2 = consumerService.helloService("12");
        return result+" "+result2;
    }

    @RequestMapping(value = "/ribbon-consumer-all")
    public String findAll(){
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        String result = allConsumerService.find("12");
        String result2 = allConsumerService.find("13");
        String result3 = allConsumerService.find("14");
        context.close();
        return result+" "+result2+" "+result3;
    }
}
