package com.pyrojoke.mtg_decks_ru.repositories;

import com.pyrojoke.mtg_decks_ru.model.Deck;
import org.springframework.data.repository.CrudRepository;

public interface DeckRepository extends CrudRepository<Deck, Long> {
    Deck findBydeckname(String cardname);
}
