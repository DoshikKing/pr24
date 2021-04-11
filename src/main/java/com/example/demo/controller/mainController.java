package com.example.demo.controller;

import com.example.demo.components.Bank;
import com.example.demo.components.Card;
import com.example.demo.service.bankService;
import com.example.demo.service.cardService;
import com.example.demo.work.WorkFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class mainController {
    private bankService bankService;
    private cardService cardService;
    private WorkFlow workFlow;

    @Autowired
    public mainController(com.example.demo.service.cardService cardService, WorkFlow workFlow, bankService bankService) {
        this.bankService =bankService;
        this.cardService = cardService;
        this.workFlow = workFlow;
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/home/show")
    public @ResponseBody String show(Model model){
        String buff = "";
        buff += workFlow.printBanks(bankService.getAllBanks());
        buff += workFlow.printCards(cardService.getAllCards());
        model.addAttribute("buff", buff);
        return buff;
    }

    @PostMapping("/home/addBank")
    public String add(@RequestParam String name,
                      @RequestParam String address){
        Bank bank = new Bank();
        bank.setName(name);
        bank.setAddress(address);
        bankService.addBank(bank);
        return "home";
    }
    @PostMapping("/home/addCard")
    public String add(@RequestParam int cardNumber,
                      @RequestParam int code, String name){
        Card card = new Card();
        card.setCardNumber(cardNumber);
        card.setCode(code);
        card.bank = bankService.getBankById(bankService.getBankId(name));
        cardService.addCard(card);
        return "home";
    }
    @GetMapping("/home/removeCard")
    public String removeCards(@RequestParam Long id){
        cardService.deleteCardById(id);
        return "home";
    }
    @GetMapping("/home/removeBank")
    public String removeBanks(@RequestParam Long id){
        bankService.deleteBankById(id);
        return "home";
    }
    @GetMapping("/home/getCardBank")
    public @ResponseBody String getCardBank(@RequestParam Long id){
        return "name:" + cardService.getBankByCard(id).getName() + " address:" + cardService.getBankByCard(id).getAddress() + " id:" + cardService.getBankByCard(id).getId();
    }

    @GetMapping("/home/getBankByName")
    public @ResponseBody
    String getBanksByName(){
        return workFlow.printBanks(bankService.filterByName());
    }
    @GetMapping("/home/getBankById")
    public @ResponseBody
    String getBanksById(){
        return workFlow.printBanks(bankService.filterByBankId());
    }
    @GetMapping("/home/getBankByAddress")
    public @ResponseBody
    String getBanksByAddress(){
        return workFlow.printBanks(bankService.filterByAddress());
    }
    @GetMapping("/home/getCardById")
    public @ResponseBody
    String getCardsById(){
        return workFlow.printCards(cardService.filterByCardId());
    }
    @GetMapping("/home/getCardByCode")
    public @ResponseBody
    String getCardsByCode(){
        return workFlow.printCards(cardService.filterByCode());
    }
    @GetMapping("/home/getCardByCardNumber")
    public @ResponseBody
    String getCardsByCardNumber(){
        return workFlow.printCards(cardService.filterByCardNumber());
    }

}

