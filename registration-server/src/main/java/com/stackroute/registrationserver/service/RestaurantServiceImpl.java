package com.stackroute.registrationserver.service;

import com.stackroute.registrationserver.domain.Restaurant;
import com.stackroute.registrationserver.domain.RestaurantDetails;
import com.stackroute.rabbitmq.model.Users;
import com.stackroute.registrationserver.repository.RestaurantRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository){

        this.restaurantRepository = restaurantRepository;

    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) throws Exception {

        RestaurantDetails restaurantDetails = new RestaurantDetails(restaurant.getRestaurantId(),restaurant.getUsername(),restaurant.getRestaurantName(),restaurant.getCertificateNo(),restaurant.getPhoneNo(),restaurant.getAddress());

        RestaurantDetails savedRestaurantDetails = restaurantRepository.save(restaurantDetails);
        if (savedRestaurantDetails == null)
            throw new Exception("User Already Exists");
        return null;
    }

    @Override
    public void sendToRabbitMq(Restaurant restaurant) {

        Users user = new Users(restaurant.getRestaurantId(),restaurant.getUsername(),restaurant.getPassword());
//
        rabbitTemplate.convertAndSend(exchange, routingkey,user);

        System.out.println("Send msg = " + restaurant);

    }
}
