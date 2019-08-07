package com.pyrojoke.mtg_decks_ru;

import com.pyrojoke.mtg_decks_ru.model.*;
import com.pyrojoke.mtg_decks_ru.services.CardService;
import com.pyrojoke.mtg_decks_ru.services.DeckCardsService;
import com.pyrojoke.mtg_decks_ru.services.DeckService;
import com.pyrojoke.mtg_decks_ru.services.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataLoader implements CommandLineRunner {

    private final CardService cardService;
    private final DeckCardsService deckCardsService;
    private final PersonService personService;
    private final DeckService deckService;

    public DataLoader(CardService cardService, DeckCardsService deckCardsService, PersonService personService, DeckService deckService) {
        this.cardService = cardService;
        this.deckCardsService = deckCardsService;
        this.personService = personService;
        this.deckService = deckService;
    }

    @Override
    public void run(String... args) throws Exception {
        Person person = new Person();
        person.setId(1L);
        person.setNickname("PyroJoke");
        personService.save(person);
        Person person1 = new Person();
        person1.setId(2L);
        person1.setNickname("Roma");
        personService.save(person1);
        Deck deck= new Deck();
        deck.setId(1L);
        deck.setDeckname("Tron");
        deck.getPersons().add(person);
        Deck deck2= new Deck();
        deck2.setId(2L);
        deck2.setDeckname("Eldrazi aggro");
        deck2.getPersons().add(person);
        deck2.getPersons().add(person1);
        deckService.save(deck);
        deckService.save(deck2);

        Card card = new Card();
        card.setCardname("Karn");
        card.setDescription("Eto karn");
        card.setCardTypes(Collections.singleton(CardType.PLANESWALKER));
        card.setLand("0");
        card.setVoids(7);
        card.setId(1L);
        card.setLoyality(7);
        cardService.save(card);
        Card card2 = new Card();
        card2.setCardname("Udzhin");
        card2.setDescription("Eto Udzhin");
        card2.setCardTypes(Collections.singleton(CardType.PLANESWALKER));
        card2.setLand("0");
        card2.setVoids(8);
        card2.setId(2L);
        cardService.save(card2);

        Card card3 = new Card();
        card3.setCardname("Тармогойф");
        card3.setDescription("Зеленый сука");
        card3.setCardTypes(Collections.singleton(CardType.CREATURE));
        card3.setLand("0");
        card3.setForest(1);
        card3.setVoids(1);
        card3.setId(3L);
        card3.setStrength(0);
        card3.setHealth(1);
        cardService.save(card3);

        DeckCards deckCards = new DeckCards();
        deckCards.setId(1L);
        deckCards.setCount(4);
        deckCards.setDecks(deck);
        deckCards.setCardname(card.getCardname());
        deckCards.setMain(true);
        DeckCards deckCards2 = new DeckCards();
        deckCards2.setId(2L);
        deckCards2.setCount(2);
        deckCards2.setDecks(deck);
        deckCards2.setCardname(card2.getCardname());
        deckCards2.setMain(true);
        DeckCards deckCards3 = new DeckCards();
        deckCards3.setId(3L);
        deckCards3.setCount(2);
        deckCards3.setDecks(deck2);
        deckCards3.setCardname(card2.getCardname());
        deckCards3.setMain(true);
        deckCardsService.save(deckCards);
        deckCardsService.save(deckCards2);
        deckCardsService.save(deckCards3);
    }
}
