package com.stackroute.registrationserver.service;

import com.stackroute.registrationserver.domain.Restaurant;

public interface RestaurantService {

    public Restaurant saveRestaurant(Restaurant restaurant) throws Exception;

    public void sendToRabbitMq(Restaurant restaurant);

}