package com.kchen.rocketmq.demo.provider.controller;

import com.kchen.rocketmq.demo.common.entity.Order;
import com.kchen.rocketmq.demo.provider.service.OrderService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "home";
    }

    @RequestMapping(value = "/produce", method = RequestMethod.GET)
    @ResponseBody
    public String produce() {
        String orderId = RandomStringUtils.random(16, true, true);
        String buyerId = RandomStringUtils.random(16, true, true);
        String price = Double.valueOf(RandomUtils.nextFloat(10f, 100f)).toString();
        Order order = new Order(orderId, buyerId, price);
        orderService.sendOrderToMQ(order);
        return "produce successfully";
    }
}
