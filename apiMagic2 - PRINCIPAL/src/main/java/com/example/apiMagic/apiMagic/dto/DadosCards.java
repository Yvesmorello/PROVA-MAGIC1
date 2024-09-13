package com.example.apiMagic.apiMagic.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosCards(Long _id,
                         @JsonAlias("name") String name,
                         @JsonAlias("names") List<String> names,
                         @JsonAlias("manaCost") String manaCost,
                         @JsonAlias("colors") List<String> colors,
                         @JsonAlias("colorIdentity") List<String> colorIdentity,
                         @JsonAlias("type") String type,
                         @JsonAlias("supertypes") List<String> supertypes,
                         @JsonAlias("types") List<String> types,
                         @JsonAlias("subtypes") List<String> subtypes,
                         @JsonAlias("rarity") String rarity,
                         @JsonAlias("text") String text,
                         @JsonAlias("number") String number,
                         @JsonAlias("power") String power,
                         @JsonAlias("toughness") String toughness,
                         @JsonAlias("imageUrl") String imageUrl) {
}