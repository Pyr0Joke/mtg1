package com.pyrojoke.mtg_decks_ru.controllers;

import com.pyrojoke.mtg_decks_ru.model.Card;
import com.pyrojoke.mtg_decks_ru.model.CardType;
import com.pyrojoke.mtg_decks_ru.model.Deck;
import com.pyrojoke.mtg_decks_ru.services.CardService;
import com.pyrojoke.mtg_decks_ru.services.DeckCardsService;
import com.pyrojoke.mtg_decks_ru.services.DeckService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
    public String viewCards(Map<String, Object> model) {
        Iterable<Card> cards = cardService.findAll();
        model.put("cards", cards);
        return "cards";
    }

    @GetMapping("/decks")
    public String viewDecks(Map<String, Object> model) {
        Iterable<Deck> decks = deckService.findAll();
        model.put("decks", decks);
        return "decks";
    }

    @GetMapping("/findCard")
    public String findCard(@RequestParam String cardname, Map<String, Object> model) {
        Card card = cardService.findByCardName(cardname);
        model.put("card", card);
        return "carddetails";
    }

    @GetMapping("/findDeck")
    public String findDeck(@RequestParam String deckname, Map<String, Object> model) {
        model.put("deck", deckname);
        model.put("cards", deckCardsService.findBydecks(deckService.findByDeckName(deckname)));
        return "deckdetails";
    }

    @RequestMapping("/addCardForm")
    public String addCardsForm() {
        return "addcard";
    }

    @PostMapping("/addCard")
    public String addCard(
            @RequestParam String cardname,
            @RequestParam String description,
            @RequestParam String cardtype,
            @RequestParam String island,
            @RequestParam String manacost) {

        Card card = new Card();
        card.setCardname(cardname);
        card.setDescription(description);
        card.setCardTypes(Collections.singleton(CardType.valueOf(cardtype)));
        card.setLand(false);
        if (island.equals("1"))
            card.setLand(true);
        Map<String, Integer> mana = new HashMap<>();
        char [] chars = manacost.toCharArray();
        StringBuilder temp = new StringBuilder();
        for (int i =0; i<chars.length; i++){
            if(!Character.isAlphabetic(chars[i])){
                temp.append(chars[i]);
            }
            else{
                mana.put(String.valueOf(chars[i]), Integer.parseInt(temp.toString()));
                temp = new StringBuilder();
            }
        }
        if(mana.get("D")!=null)
            card.setDiamond(mana.get("D"));
        if(mana.get("V")!=null)
            card.setVoids(mana.get("V"));
        if(mana.get("R")!=null)
            card.setMountain(mana.get("R"));
        if(mana.get("U")!=null)
            card.setIsland(mana.get("U"));
        if(mana.get("W")!=null)
            card.setPlains(mana.get("W"));
        if(mana.get("B")!=null)
            card.setSwamp(mana.get("B"));
        if(mana.get("G")!=null)
            card.setForest(mana.get("G"));
        cardService.save(card);
        return "index";
    }
}
