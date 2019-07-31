package com.pyrojoke.mtg_decks_ru.repositories;

import com.pyrojoke.mtg_decks_ru.model.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Long> {
    Card findBycardname(String cardname);
}
