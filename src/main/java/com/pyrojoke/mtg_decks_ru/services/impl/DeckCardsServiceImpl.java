package com.pyrojoke.mtg_decks_ru.services.impl;

import com.pyrojoke.mtg_decks_ru.model.Card;
import com.pyrojoke.mtg_decks_ru.model.Deck;
import com.pyrojoke.mtg_decks_ru.model.DeckCards;
import com.pyrojoke.mtg_decks_ru.repositories.DeckCardsRepository;
import com.pyrojoke.mtg_decks_ru.services.DeckCardsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class DeckCardsServiceImpl implements DeckCardsService {

    private final DeckCardsRepository deckCardsRepository;

    public DeckCardsServiceImpl(DeckCardsRepository deckCardsRepository) {
        this.deckCardsRepository = deckCardsRepository;
    }

    @Override
    public Set<DeckCards> findAll() {
        Set<DeckCards> deckCards = new HashSet<>();
        deckCardsRepository.findAll().forEach(deckCards::add);
        return deckCards;
    }

    @Override
    public Set<Long> findCardsIdByDeckId(long id) {
        return deckCardsRepository.findCards(id);
    }

    @Override
    public Set<DeckCards> findBydecks(Deck deck) {
        return deckCardsRepository.findBydecks(deck);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<String> findCardNamesByDeckId(long id) {
        return deckCardsRepository.findCardsByDeck(id);
    }

    @Override
    public DeckCards findById(Long aLong) {
        return deckCardsRepository.findById(aLong).orElse(null);
    }

    @Override
    public DeckCards save(DeckCards object) {
        return deckCardsRepository.save(object);
    }

    @Override
    public void delete(DeckCards object) {
        deckCardsRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        deckCardsRepository.deleteById(aLong);
    }
}
