package com.example.demo.components;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "cards")
@Getter
@Setter
@ToString
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "card_number")
    private int cardNumber;
    @Column(name = "code")
    private int code;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bank_id")
    public Bank bank;
}
