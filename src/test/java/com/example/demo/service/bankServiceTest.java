package com.example.demo.service;

import com.example.demo.components.Bank;
import com.example.demo.repos.BankRepo;
import com.example.demo.service.emailService.emailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class bankServiceTest {
    @Mock
    BankRepo bankRepo;
    @Captor
    ArgumentCaptor<Bank> captor;
    @Test
    void getAll(){
        Bank bank = new Bank();
        bank.setName("Qwerty");
        Bank bank1 = new Bank();
        bank1.setName("Asdfgh");
        Mockito.when(bankRepo.findAll()).thenReturn(List.of(bank,bank1));
        assertEquals(2, bankRepo.findAll().size());
    }
    @Test
    void create() {
        Bank bank = new Bank();
        bank.setName("devil");
        bankService ss = new bankService(bankRepo);
        ss.addBank(bank);
        Mockito.verify(bankRepo).save(captor.capture());
        Bank captured = captor.getValue();
        assertEquals("devil", captured.getName());
    }
}