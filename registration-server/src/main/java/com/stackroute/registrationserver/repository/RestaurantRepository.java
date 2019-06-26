package com.stackroute.registrationserver.repository;

import com.stackroute.registrationserver.domain.RestaurantDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantDetails, String> {
}
