package com.example.demo.service;

import com.example.demo.components.Bank;
import com.example.demo.components.Card;

import java.util.List;

public interface cardServiceInterface {
    void addCard(Card card);
    List<Card> getAllCards();
    List<Card> filterByCardNumber();
    List<Card> filterByCode();
    List<Card> filterByCardId();
    List<Card> filterByRelatedBankId();
    Card getCardById(Long id);
    void deleteCardById(Long id);
    void deleteAllCards();
    Bank getBankByCard(Long id);
    Long getCardId(int cardNumber);
}
