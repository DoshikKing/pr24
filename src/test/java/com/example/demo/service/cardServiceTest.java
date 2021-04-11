package com.example.demo.service;

import com.example.demo.components.Card;
import com.example.demo.repos.BankRepo;
import com.example.demo.repos.CardRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class cardServiceTest {
    @Mock
    CardRepo cardRepo;
    @Captor
    ArgumentCaptor<Card> captor;
    @Test
    void getAll(){
        Card card = new Card();
        card.setCode(5748937);
        card.setCardNumber(5756937);
        Card card1 = new Card();
        card1.setCode(6666666);
        card1.setCardNumber(5746666);
        Mockito.when(cardRepo.findAll()).thenReturn(List.of(card,card1));
        assertEquals(2, cardRepo.findAll().size());
    }
    @Test
    void create() {
        Card card = new Card();
        card.setCode(6854654);
        cardService ss = new cardService(cardRepo);
        ss.addCard(card);
        Mockito.verify(cardRepo).save(captor.capture());
        Card captured = captor.getValue();
        assertEquals(6854654, captured.getCode());
    }
}
