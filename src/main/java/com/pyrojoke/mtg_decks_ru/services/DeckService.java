package com.pyrojoke.mtg_decks_ru.services;

import com.pyrojoke.mtg_decks_ru.model.Deck;

public interface DeckService extends CrudService<Deck, Long> {
    Deck findByDeckName(String deckName);
}
