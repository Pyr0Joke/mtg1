package com.pyrojoke.mtg_decks_ru.controllers;

import com.pyrojoke.mtg_decks_ru.model.Card;
import com.pyrojoke.mtg_decks_ru.model.CardType;
import com.pyrojoke.mtg_decks_ru.model.Deck;
import com.pyrojoke.mtg_decks_ru.model.DeckCards;
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

    @GetMapping("/findCards")
    public String findCards(@RequestParam String cardname, Map<String, Object> model) {
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
            @RequestParam String manacost,
            @RequestParam String strength,
            @RequestParam String health,
            @RequestParam String loyality,
            Map<String, Object> model) {

        Card card = new Card();
        card.setCardname(cardname);
        card.setDescription(description);
        card.setCardTypes(Collections.singleton(CardType.valueOf(cardtype)));
        card.setLand(island);
//        Map<String, Integer> mana = new HashMap<>();
//        char [] chars = manacost.toCharArray();
//        StringBuilder temp = new StringBuilder();
//        for (int i =0; i<chars.length; i++){
//            if(!Character.isAlphabetic(chars[i])){
//                temp.append(chars[i]);
//            }
//            else{
//                mana.put(String.valueOf(chars[i]), Integer.parseInt(temp.toString()));
//                temp = new StringBuilder();
//            }
//        }
//        if(mana.get("D")!=null)
//            card.setDiamond(mana.get("D"));
//        if(mana.get("V")!=null)
//            card.setVoids(mana.get("V"));
//        if(mana.get("R")!=null)
//            card.setMountain(mana.get("R"));
//        if(mana.get("U")!=null)
//            card.setIsland(mana.get("U"));
//        if(mana.get("W")!=null)
//            card.setPlains(mana.get("W"));
//        if(mana.get("B")!=null)
//            card.setSwamp(mana.get("B"));
//        if(mana.get("G")!=null)
//            card.setForest(mana.get("G"));
        card.setManacost(manacost);
        card.setLoyality(Integer.parseInt(loyality));
        card.setStrength(Integer.parseInt(strength));
        card.setHealth(Integer.parseInt(health));
        cardService.save(card);
        Iterable<Card> cards = cardService.findAll();
        model.put("cards", cards);
        return "cards";
    }

    @RequestMapping("/addDeckForm")
    public String addDeckForm(){
        return "adddeck";
    }

    @PostMapping("/addDeck")
    public String addDeck(@RequestParam String deckname, Map<String, Object> model ){
        Deck deck = new Deck();
        deck.setDeckname(deckname);
        deckService.save(deck);
        Iterable<Deck> decks = deckService.findAll();
        model.put("decks", decks);
        return "decks";
    }

    @RequestMapping("/findCardForm")
    public String findCardsForm(Map<String, Object> model){
        Iterable<Deck> decks = deckService.findAll();
        model.put("decks", decks);
        return "findcardsform";
    }

    @GetMapping("/findCard")
    public String findCard(@RequestParam String cardname,  Map<String, Object> model){
        Card card = cardService.findByCardName(cardname);
        Iterable<Deck> decks = deckService.findAll();
        model.put("decks", decks);
        model.put("card", card);
        return "findcardsform";
    }

    @PostMapping("/addCardToDeck")
    public String addCardToDeck(
            @RequestParam String cardname,
            @RequestParam String deckname,
            @RequestParam String count,
            @RequestParam String ismain,
            Map<String, Object> model){
        Deck deck = deckService.findByDeckName(deckname);
        Set<DeckCards> deckCardsForCheck = deckCardsService.findBydecks(deckService.findByDeckName(deckname));
        boolean main = false;
        if(ismain.equals("1"))
            main = true;
        for(DeckCards cards: deckCardsForCheck){
            if(cards.getCardname().equals(cardname))
            {
                if((cards.getCount()+Integer.parseInt(count)>4))
                {
                    model.put("error","Нельзя положить в колоду больше 4х копий карты");
                    Iterable<Deck> decks = deckService.findAll();
                    model.put("decks", decks);
                    return "findcardsform";
                }
            }
        }

        DeckCards deckCards = new DeckCards();
        deckCards.setDecks(deck);
        deckCards.setCardname(cardname);
        deckCards.setMain(main);
        deckCards.setCount(Integer.parseInt(count));
        deckCardsService.save(deckCards);
        Iterable<Deck> decks = deckService.findAll();
        model.put("decks", decks);
        return "findcardsform";
    }
}

