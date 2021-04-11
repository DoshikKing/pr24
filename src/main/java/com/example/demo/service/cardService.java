package com.example.demo.service;

import com.example.demo.components.Bank;
import com.example.demo.components.Card;
import com.example.demo.repos.CardRepo;
import com.example.demo.service.emailService.emailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Component
@Transactional
public class cardService implements cardServiceInterface{
    CardRepo cardRepo;
    emailService emailService;

    @Autowired
    public cardService(CardRepo cardRepo, emailService emailService){
        this.emailService = emailService;
        this.cardRepo = cardRepo;
    }

    public cardService(CardRepo cardRepo) {
        this.cardRepo = cardRepo;
    }

    @Override
    public void addCard(Card card) {
        log.info("Added new card {}", card);
        //email service
        //emailService.sendMessageAboutCard(card);
        cardRepo.save(card);
    }

    @Override
    public List<Card> getAllCards() {
        log.info("Found all cards");
        List<Card> cardList = cardRepo.findAll();
        return cardList;
    }

    @Override
    public List<Card> filterByCardNumber() {
        log.info("Sorted card list by card numbers");
        return cardRepo.findByOrderByCardNumber();
    }

    @Override
    public List<Card> filterByCode() {
        log.info("Sorted card list by code");
        return cardRepo.findByOrderByCode();
    }

    @Override
    public List<Card> filterByCardId() {
        log.info("Sorted card list by id");
        return cardRepo.findByOrderById();
    }

    @Override
    public List<Card> filterByRelatedBankId() {
        log.info("Sorted card list by related banks id");
        return cardRepo.findByOrderByBankId();
    }

    @Override
    public Card getCardById(Long id) {
        log.info("Found a card with id {}", id);
        return cardRepo.getOne(id);
    }

    @Override
    public void deleteCardById(Long id) {
        log.info("Deleted card wit id {}", id);
        cardRepo.deleteById(id);
    }

    @Override
    public void deleteAllCards() {
        log.info("All cards deleted");
        cardRepo.deleteAll();
    }

    @Override
    public Bank getBankByCard(Long id) {
        log.info("Got bank by card");
        return cardRepo.findById(id).orElseThrow(() ->
                new IllegalStateException("Card with this id not found")).getBank();
    }

    @Override
    public Long getCardId(int cardNumber) {
        log.info("Found id of the card with the card number {}", cardNumber);
        return cardRepo.findByCardNumber(cardNumber).getId();
    }
}
