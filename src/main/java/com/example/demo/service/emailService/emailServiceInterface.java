package com.example.demo.service.emailService;

import com.example.demo.components.Bank;
import com.example.demo.components.Card;

public interface emailServiceInterface {

    void sendMessageAboutBank(Bank bank);

    void sendMessageAboutCard(Card card);
}
