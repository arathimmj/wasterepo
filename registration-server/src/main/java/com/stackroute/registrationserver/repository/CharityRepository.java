package com.stackroute.registrationserver.repository;

import com.stackroute.registrationserver.domain.Charity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharityRepository extends JpaRepository<Charity, String> {
}
