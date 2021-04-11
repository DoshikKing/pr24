package com.example.demo.service.emailService;

import com.example.demo.components.Bank;
import com.example.demo.components.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class emailService implements emailServiceInterface{

    public JavaMailSender javaMailSender;

    @Autowired
    public emailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendMessageAboutBank(Bank bank){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("qwertyuiop12345jomh@gmail.com");
        simpleMailMessage.setSubject("Added bank");
        simpleMailMessage.setText("Added bank: name - " +
                bank.getName() + " address - " + bank.getAddress());
        javaMailSender.send(simpleMailMessage);
    }
    @Async
    public void sendMessageAboutCard(Card card){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("qwertyuiop12345jomh@gmail.com");
        simpleMailMessage.setSubject("Added card");
        simpleMailMessage.setText("Added bank: card number - " +
                card.getCardNumber() + " code - " + card.getCode());
        javaMailSender.send(simpleMailMessage);
    }
}
