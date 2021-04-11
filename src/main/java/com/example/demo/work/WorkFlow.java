package com.example.demo.work;

import com.example.demo.components.Bank;
import com.example.demo.components.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkFlow {
    public String printBanks(List<Bank> list){
        String buff = "<h>Banks</h><table border=1>";
        int i = 0;
        for (Bank item: list) {
            buff += "<tr><td>" + item.getName() + "</td><td>" + item.getAddress() + "</td><td><a href='removeBank?id=" + item.getId() + "'>Delete</a></td></tr>";
            i++;
        }
        if (i == 0)
        {
            buff+= "<tr><td>Bank list is empty</td></tr>";
        }
        i = 0;
        buff += "</table>";
        return buff;
    }
    public String printCards(List<Card> list){
        String buff ="";
        int i = 0;
        buff += "<h>Cards</h><table border=1>";
        for (Card item: list) {
            buff += "<tr><td>" + item.getCode() + "</td><td>" + item.getCardNumber() + "</td><td><a href='removeCard?id=" + item.getId() + "'>Delete</a></td></tr>";
            i++;
        }
        if (i == 0)
        {
            buff+= "<tr><td>Card list is empty</td></tr>";
        }
        buff += "</table>";
        return buff;
    }
}
