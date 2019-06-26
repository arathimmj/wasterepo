package com.stackroute.registrationserver.service;

import com.stackroute.registrationserver.domain.Charity;

public interface CharityService {

    public Charity saveCharity(Charity charity) throws Exception;

}