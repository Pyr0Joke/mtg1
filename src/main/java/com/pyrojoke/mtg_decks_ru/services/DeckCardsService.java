package com.pyrojoke.mtg_decks_ru.services;

import com.pyrojoke.mtg_decks_ru.model.Card;
import com.pyrojoke.mtg_decks_ru.model.Deck;
import com.pyrojoke.mtg_decks_ru.model.DeckCards;

import java.util.Set;

public interface DeckCardsService extends CrudService<DeckCards, Long> {
    Set<DeckCards> findBydecks(Deck deck);
    Set<String> findCardNamesByDeckId(long id);
    Set<Long> findCardsIdByDeckId(long id);

}
