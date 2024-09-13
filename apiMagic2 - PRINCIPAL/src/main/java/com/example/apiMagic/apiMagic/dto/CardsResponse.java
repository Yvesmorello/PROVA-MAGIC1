package com.example.apiMagic.apiMagic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CardsResponse {
    @JsonProperty("cards")
    private List<DadosCards> cards;
    private DadosCards card;

    public List<DadosCards> getCards() {
        return cards;
    }
    public void setCards(List<DadosCards> cards) {
        this.cards = cards;
    }

    public DadosCards getCard() {
        return card;
    }

    public void setCard(DadosCards card) {
        this.card = card;
    }
}
