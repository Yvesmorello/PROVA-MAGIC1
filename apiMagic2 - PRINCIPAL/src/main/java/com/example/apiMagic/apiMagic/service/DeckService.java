package com.example.apiMagic.apiMagic.service;

import com.example.apiMagic.apiMagic.dto.*;
import com.example.apiMagic.apiMagic.model.Cards;
import com.example.apiMagic.apiMagic.model.Commander;
import com.example.apiMagic.apiMagic.repository.CardsRepository;
import com.example.apiMagic.apiMagic.repository.CommanderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DeckService {

    private ConsomeApi consomeApi;
    private ConverteDados converteDados;
    private CardsRepository repository;
    private CommanderRepository commanderRepository;
    private DadosCardsCommander commander;
    private DadosCards cards;

    @Autowired
    public DeckService(ConsomeApi consomeApi, ConverteDados converteDados, CardsRepository repository, CommanderRepository commanderRepository) {
        this.consomeApi = consomeApi;
        this.converteDados = converteDados;
        this.repository = repository;
        this.commanderRepository = commanderRepository;
    }

    public ApiResponse getAllCommanders() {
        var allLegendaryJson = consomeApi.obterDados("https://api.magicthegathering.io/v1/cards?supertypes=legendary&types=creature");
        CardsResponseCommander allLegendaryResponse = converteDados.obterDadosCommander(allLegendaryJson, CardsResponseCommander.class);
        List<DadosCardsCommander> allLegendaryCardsList = allLegendaryResponse.getCards();

        Set<String> uniqueNames = new HashSet<>();
        for (DadosCardsCommander commander : allLegendaryCardsList) {
            uniqueNames.add(commander.name());
        }

        List<String> uniqueNamesList = new ArrayList<>(uniqueNames);

        return new ApiResponse(true, "Cartas lendárias para commander:", uniqueNamesList);
    }

    public ApiResponse fetchAndSaveCommanderByName(String name) {
        try {
            var json = consomeApi.obterDados("https://api.magicthegathering.io/v1/cards?supertypes=legendary&types=creature&name=" + name.replace(" ", "+"));
            CardsResponseCommander cardsResponseCommander = converteDados.obterDadosCommander(json, CardsResponseCommander.class);
            List<DadosCardsCommander> dadosCardsList = cardsResponseCommander.getCards();

            if (dadosCardsList != null && !dadosCardsList.isEmpty()) {
                List<Commander> cardsList = dadosCardsList.stream().map(this::convertToEntityCommander).limit(1).toList();
                commanderRepository.saveAll(cardsList);
                GeraJson.salvarEmJson(cardsList, "deck.json");
                return new ApiResponse(true, "Carta encontrada: ", dadosCardsList.stream().map(this::convertToEntityCommander).toList());
            } else {
                var allLegendaryJson = consomeApi.obterDados("https://api.magicthegathering.io/v1/cards?supertypes=legendary&types=creature");
                CardsResponseCommander allLegendaryResponse = converteDados.obterDadosCommander(allLegendaryJson, CardsResponseCommander.class);
                List<DadosCardsCommander> allLegendaryCardsList = allLegendaryResponse.getCards();

                if (allLegendaryCardsList != null && !allLegendaryCardsList.isEmpty()) {
                    return new ApiResponse(true, "Cartas lendárias para commander:", allLegendaryCardsList.stream().map(DadosCardsCommander::name).toList());
                } else {
                    return new ApiResponse(false, "Nenhuma carta lendária disponível para commander.", null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse(false, "Erro ao buscar ou salvar cards: " + e.getMessage(), null);
        }
    }

    private Cards convertToEntity(DadosCards dadosCards) {
        Cards cards = new Cards();
        cards.setName(dadosCards.name());
        cards.setNames(dadosCards.names());
        cards.setManaCost(dadosCards.manaCost());
        cards.setColors(dadosCards.colors());
        cards.setColorIdentity(dadosCards.colorIdentity());
        cards.setType(dadosCards.type());
        cards.setSupertypes(dadosCards.supertypes());
        cards.setTypes(dadosCards.types());
        cards.setSubtypes(dadosCards.subtypes());
        cards.setRarity(dadosCards.rarity());
        cards.setText(dadosCards.text());
        cards.setNumber(dadosCards.number());
        cards.setPower(dadosCards.power());
        cards.setToughness(dadosCards.toughness());
        cards.setImageUrl(dadosCards.imageUrl());
        return cards;
    }

    private Commander convertToEntityCommander(DadosCardsCommander dadosCardsCommander) {
        Commander commander = new Commander();
        commander.setName(dadosCardsCommander.name());
        commander.setNames(dadosCardsCommander.names());
        commander.setManaCost(dadosCardsCommander.manaCost());
        commander.setColors(dadosCardsCommander.colors());
        commander.setColorIdentity(dadosCardsCommander.colorIdentity());
        commander.setType(dadosCardsCommander.type());
        commander.setSupertypes(dadosCardsCommander.supertypes());
        commander.setTypes(dadosCardsCommander.types());
        commander.setSubtypes(dadosCardsCommander.subtypes());
        commander.setRarity(dadosCardsCommander.rarity());
        commander.setText(dadosCardsCommander.text());
        commander.setNumber(dadosCardsCommander.number());
        commander.setPower(dadosCardsCommander.power());
        commander.setToughness(dadosCardsCommander.toughness());
        commander.setImageUrl(dadosCardsCommander.imageUrl());
        return commander;
    }

    public ApiResponse getDeckByCommanderColor() throws IOException {
        var commanderColor = commanderRepository.findColorFromCommander();
        var json =  consomeApi.obterDados("https://api.magicthegathering.io/v1/cards?colorIdentity=" + commanderColor);
        CardsResponse cardsResponse = converteDados.obterDados(json, CardsResponse.class);
        Set<DadosCards> uniqueCards = new HashSet<>();

        for (DadosCards card : cardsResponse.getCards()) {
            if (uniqueCards.size() >= 100) {
                break;
            }
            uniqueCards.add(card);
        }

        List<Cards> cardsList = uniqueCards.stream().map(this::convertToEntity).toList();
        repository.saveAll(cardsList);

        GeraJson.salvarEmJson(cardsList, "deck.json");

        return new ApiResponse(true, "Deck Criado: ", cardsList);

    }


}
