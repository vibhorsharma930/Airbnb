package com.bnb.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SMSService {

    @Value("${twilio.phoneNumber}")
    private String twillioNum;

    public void sendMsg(String to ,String msgBody){
        Message message = Message.creator(new PhoneNumber(to),
                new PhoneNumber(twillioNum),
                msgBody).create();


        System.out.println(message.getBody());


    }



}
