package com.pyrojoke.mtg_decks_ru.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "decks")
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinTable(name = "persons_decks", joinColumns = @JoinColumn(name = "deck_id"), inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<Person> persons = new HashSet<>();

    private String deckname;

    @OneToMany( mappedBy = "decks", fetch = FetchType.LAZY)
    private Set<DeckCards> deckCards = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<DeckCards> getDeckCards() {
        return deckCards;
    }

    public void setDeckCards(Set<DeckCards> deckCards) {
        this.deckCards = deckCards;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public String getDeckname() {
        return deckname;
    }

    public void setDeckname(String deckname) {
        this.deckname = deckname;
    }
}
