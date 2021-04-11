package com.example.demo.service.schedulerService;

import com.example.demo.components.Bank;
import com.example.demo.components.Card;
import com.example.demo.service.bankService;
import com.example.demo.service.cardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@Component
@RequiredArgsConstructor
@ManagedResource(objectName = "MyMBeans:category=MBeans, name=schedulerService")
public class schedulerService implements schedulerInterface {

    @Autowired
    bankService bankService;
    @Autowired
    cardService cardService;

    @Async
    @Scheduled(fixedDelay = 1800000)
    @ManagedOperation(description = "Deletes all content of the directory and writes all data from the databases to it every 30 minutes")
    @Override
    public void createSQLCopy() {
        String path = "src/main/alldb";

        File dir = new File(path);
        File[] files = dir.listFiles();
        for (File file: files) file.delete();

        try {
            File banks = new File(path + "/banks.txt");
            banks.createNewFile();
            try(FileWriter writer = new FileWriter(banks, true);){
                List<Bank> bankList = bankService.getAllBanks();
                for (Bank item : bankList) {
                    writer.write("id "+item.getId()+ " name " + item.getName() + " address" + item.getAddress() + "\n");
                }
                writer.flush();
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }

            File cards = new File(path + "/cards.txt");
            cards.createNewFile();
            try(FileWriter writer = new FileWriter(cards, true);){
                List<Card> cardList = cardService.getAllCards();
                for (Card item : cardList) {
                    writer.write("id "+item.getId()+ " card number " + item.getCardNumber() + " code " + item.getCode() + " banks id " + item.getBank().getId() + "\n");
                }
                writer.flush();
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
