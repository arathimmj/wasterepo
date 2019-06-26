package com.stackroute.registrationserver.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Charity {

    @Id
    String charityId;
    String username;
    String password;
    String charityName;
    String certificateNo;
    int phoneNo;
    String address;
}
