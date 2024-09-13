package com.example.apiMagic.apiMagic.repository;

import com.example.apiMagic.apiMagic.dto.DadosCardsCommander;
import com.example.apiMagic.apiMagic.model.Cards;
import com.example.apiMagic.apiMagic.model.Commander;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommanderRepository extends JpaRepository<Commander, Long>{

    @Query ("SELECT c.colorIdentity FROM Commander c")
    String findColorFromCommander();

}
