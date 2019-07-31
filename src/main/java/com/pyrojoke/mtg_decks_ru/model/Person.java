package com.pyrojoke.mtg_decks_ru.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nick_name")
    private String nickname;

    @ManyToMany(fetch = FetchType.LAZY)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinTable(name = "persons_decks", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "deck_id"))
    private Set<Deck> deck = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Set<Deck> getDeck() {
        return deck;
    }

    public void setDeck(Set<Deck> deck) {
        this.deck = deck;
    }
}

