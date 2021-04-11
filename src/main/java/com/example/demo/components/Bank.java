package com.example.demo.components;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "banks")
@Getter
@Setter
@ToString
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @OneToMany(mappedBy = "bank")
    private List<Card> cardList;
}
