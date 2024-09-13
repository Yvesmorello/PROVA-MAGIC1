package com.example.apiMagic.apiMagic.service;

import com.example.apiMagic.apiMagic.dto.CardsResponse;
import com.example.apiMagic.apiMagic.dto.DadosCards;

public interface IConverteDados {
    CardsResponse obterDados(String json, Class<CardsResponse> classe);
}
