package com.pyrojoke.mtg_decks_ru.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_name")
    private String cardname;

    @Column(name = "description")
    private String description;

    @Column(name = "card_type")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = CardType.class, fetch = FetchType.EAGER)
    private Set<CardType> cardTypes;

    private int swamp;
    private int plains;
    private int island;
    private int forest;
    private int mountain;
    private int voids;
    private int diamond;

    private boolean land;

    @OneToMany( mappedBy = "decks", fetch = FetchType.LAZY)
    private Set<DeckCards> deckCards = new HashSet<>();
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "cards_in_deck", joinColumns = @JoinColumn(name = "card_id"), inverseJoinColumns = @JoinColumn(name = "deck_id"))
//    private Set<Deck> decks = new HashSet<>();

    private String manacost;

    public String getManacost() {
        StringBuilder mana = new StringBuilder();
        if(isLand()){
            return "0";
        }
        if(getDiamond()>0){
            mana.append(getDiamond()+"D ");
        }
        if(getVoids()>0){
            mana.append(getVoids()+"V ");
        }
        if(getMountain()>0){
            mana.append(getMountain()+"R ");
        }
        if(getIsland()>0){
            mana.append(getIsland()+"U ");
        }
        if(getPlains()>0){
            mana.append(getPlains()+"W ");
        }
        if(getSwamp()>0){
            mana.append(getSwamp()+"B ");
        }
        if(getForest()>0){
            mana.append(getForest()+"G ");
        }
        return mana.toString();
    }

    public void setManacost(String manacost) {
        this.manacost = manacost;
    }

    public boolean isLand() {
        return land;
    }

    public void setLand(boolean land) {
        this.land = land;
    }

    public int getSwamp() {
        return swamp;
    }

    public void setSwamp(int swamp) {
        this.swamp = swamp;
        getManacost();
    }

    public int getPlains() {
        return plains;
    }

    public void setPlains(int plains) {
        this.plains = plains;
        getManacost();
    }

    public int getIsland() {
        return island;
    }

    public void setIsland(int island) {
        this.island = island;
        getManacost();
    }

    public int getForest() {
        return forest;
    }

    public void setForest(int forest) {
        this.forest = forest;
        getManacost();
    }

    public int getMountain() {
        return mountain;
    }

    public void setMountain(int mountain) {
        this.mountain = mountain;
        getManacost();
    }

    public int getVoids() {
        return voids;
    }

    public void setVoids(int voids) {
        this.voids = voids;
        getManacost();
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
        getManacost();
    }

    public Set<DeckCards> getDeckCards() {
        return deckCards;
    }

    public void setDeckCards(Set<DeckCards> deckCards) {
        this.deckCards = deckCards;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<CardType> getCardTypes() {
        return cardTypes;
    }

    public void setCardTypes(Set<CardType> cardTypes) {
        this.cardTypes = cardTypes;
    }
}
