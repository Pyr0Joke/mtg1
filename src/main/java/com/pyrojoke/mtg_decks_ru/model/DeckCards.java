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

    private boolean main;


    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "deck_id")
    private Deck decks;

    private String cardname;

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

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }
}
