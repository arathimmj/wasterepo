package com.stackroute.registrationserver.service;

import com.stackroute.registrationserver.domain.Charity;
import com.stackroute.registrationserver.repository.CharityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharityServiceImpl implements CharityService {

    CharityRepository charityRepository;

    @Autowired
    public CharityServiceImpl(CharityRepository charityRepository){

        this.charityRepository = charityRepository;

    }

    @Override
    public Charity saveCharity(Charity charity) throws Exception {
        Charity savedCharity = charityRepository.save(charity);
        if (savedCharity == null)
            throw new Exception("User Already Exists");
        return null;
    }
}
