package com.example.demo.service;

import com.example.demo.components.Bank;

import java.util.List;

public interface bankServiceInterface {
    void addBank(Bank bank);
    List<Bank> getAllBanks();
    List<Bank> filterByName();
    List<Bank> filterByAddress();
    List<Bank> filterByBankId();
    Bank getBankById(Long id);
    void deleteBankById(Long id);
    void deleteAllBanks();
    Long getBankId(String name);
}
