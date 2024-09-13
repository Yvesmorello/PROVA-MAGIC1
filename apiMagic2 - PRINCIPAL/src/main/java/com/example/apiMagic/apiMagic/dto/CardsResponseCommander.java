package com.example.apiMagic.apiMagic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CardsResponseCommander {

    @JsonProperty("commander")
    private List<DadosCardsCommander> commanders;
    private DadosCardsCommander commander;

    public List<DadosCardsCommander> getCards() {
        return commanders;
    }
    public void setCards(List<DadosCardsCommander> commander) {
        this.commanders = commander;
    }

    public DadosCardsCommander getCard() {
        return commander;
    }

    public void setCard(DadosCardsCommander commander) {
        this.commander = CardsResponseCommander.this.commander;
    }
}
