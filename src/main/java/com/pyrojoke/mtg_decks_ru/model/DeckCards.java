package com.pyrojoke.mtg_decks_ru.model;

import javax.persistence.*;

@Entity
@Table(name = "deckcards")
public class DeckCards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "count_cards_in_deck")
    private int count;

//    @OneToOne(optional = false, cascade = CascadeType.ALL)
//    @JoinColumn(name = "deck_id")
//    private Deck decks;
//
//    @OneToOne(optional = false, cascade = CascadeType.ALL)
//    @JoinColumn(name = "card_id")
//    private Card cards;

    private boolean main;


    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "deck_id")
    private Deck decks;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id")
    private Card cards;

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Deck getDecks() {
        return decks;
    }

    public void setDecks(Deck decks) {
        this.decks = decks;
    }

    public Card getCards() {
        return cards;
    }

    public void setCards(Card cards) {
        this.cards = cards;
    }
}
