package com.pyrojoke.mtg_decks_ru.repositories;

import com.pyrojoke.mtg_decks_ru.model.Card;
import com.pyrojoke.mtg_decks_ru.model.Deck;
import com.pyrojoke.mtg_decks_ru.model.DeckCards;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface DeckCardsRepository extends CrudRepository<DeckCards, Long> {
    Set<DeckCards> findBydecks(Deck deck);

    @Query(value = "select card_name from cards where id in(select card_id from deckcards where deck_id=:deckid)", nativeQuery = true)
    Set<String> findCardsByDeck(@Param("deckid") long deckid);

    @Query(value = "select card_id from deckcards where deck_id=:deckid", nativeQuery = true)
    Set<Long> findCards(@Param("deckid") long deckid);
}
