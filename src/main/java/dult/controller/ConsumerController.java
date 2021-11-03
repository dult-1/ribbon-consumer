package dult.controller;

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

    @RequestMapping(value = "/ribbon-consumer")
    public String helloConsumer(){
        return consumerService.helloService();
    }
}
