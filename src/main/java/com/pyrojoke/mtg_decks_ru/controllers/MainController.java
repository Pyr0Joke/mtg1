package com.pyrojoke.mtg_decks_ru.controllers;

import com.pyrojoke.mtg_decks_ru.model.Card;
import com.pyrojoke.mtg_decks_ru.model.Deck;
import com.pyrojoke.mtg_decks_ru.model.DeckCards;
import com.pyrojoke.mtg_decks_ru.services.CardService;
import com.pyrojoke.mtg_decks_ru.services.DeckCardsService;
import com.pyrojoke.mtg_decks_ru.services.DeckService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class MainController {

    private final CardService cardService;
    private final DeckService deckService;
    private final DeckCardsService deckCardsService;

    public MainController(CardService cardService, DeckService deckService, DeckCardsService deckCardsService) {
        this.cardService = cardService;
        this.deckService = deckService;
        this.deckCardsService = deckCardsService;
    }


    @GetMapping("/cards")
    public String viewCards(Map<String, Object> model){
        Iterable<Card> cards = cardService.findAll();
        model.put("cards", cards);
        return "cards";
    }

    @GetMapping("/decks")
    public String viewDecks(Map<String, Object> model){
        Iterable<Deck> decks = deckService.findAll();
        model.put("decks", decks);
        return "decks";
    }

    @GetMapping("/findCard")
    public String findCard(@RequestParam String cardname, Map<String, Object> model){
        Card card = cardService.findByCardName(cardname);
        model.put("card", card);
        return "carddetails";
    }

    @GetMapping("/findDeck")
    public String findDeck(@RequestParam String deckname, Map<String, Object> model){
        Deck deck = deckService.findByDeckName(deckname);
        model.put("deck", deck.getDeckname());
        Set<Card> cards = new HashSet<>();
        for (Long i: deckCardsService.findCardsIdByDeckId(deck.getId())){
            cards.add(cardService.findById(i));
        }
        model.put("cards", cards);
        return "deckdetails";
    }
}
