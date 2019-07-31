package com.pyrojoke.mtg_decks_ru.services.impl;

import com.pyrojoke.mtg_decks_ru.model.Deck;
import com.pyrojoke.mtg_decks_ru.repositories.DeckRepository;
import com.pyrojoke.mtg_decks_ru.services.DeckService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DeckServiceImpl implements DeckService {

    private final DeckRepository deckRepository;

    public DeckServiceImpl(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    @Override
    public Set<Deck> findAll() {
        Set<Deck> decks = new HashSet<>();
        deckRepository.findAll().forEach(decks::add);
        return decks;
    }

    @Override
    public Deck findByDeckName(String deckName) {
        return deckRepository.findBydeckname(deckName);
    }

    @Override
    public Deck findById(Long aLong) {
        return deckRepository.findById(aLong).orElse(null);
    }

    @Override
    public Deck save(Deck object) {
        return deckRepository.save(object);
    }

    @Override
    public void delete(Deck object) {
        deckRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        deckRepository.deleteById(aLong);
    }
}
