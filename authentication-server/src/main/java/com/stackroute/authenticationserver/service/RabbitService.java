package com.stackroute.authenticationserver.service;

import com.stackroute.authenticationserver.model.User;
import com.stackroute.rabbitmq.model.Users;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "${rabbitmq.queue}")
public class RabbitService {


    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RabbitHandler
    public void recievedMessage(Users employee) {
//        String[] employeeDetails = employeeE.split(",");
//                User employee = new User(employeeDetails[0],employeeDetails[1],employeeDetails[2]);
        System.out.println("Recieved Message From RabbitMQ: " + employee);
        User user = new User(employee.getId(),employee.getUsername(),employee.getPassword());
        userDetailsService.save(user);

    }

}