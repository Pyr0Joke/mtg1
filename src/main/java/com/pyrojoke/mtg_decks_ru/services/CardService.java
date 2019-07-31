package com.pyrojoke.mtg_decks_ru.services;

import com.pyrojoke.mtg_decks_ru.model.Card;



public interface CardService extends CrudService<Card, Long> {
    Card findByCardName(String cardname);
}
